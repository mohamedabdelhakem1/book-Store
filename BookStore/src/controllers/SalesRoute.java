package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import models.Book;
import models.Publisher;

public class SalesRoute {
	private DataManager dataManager;

	public SalesRoute() {
		dataManager = DataManager.getInstance();
	}

	// returns a message whether success or failed with the causes
	public String checkout(String creditCard, Date expiryDate,String userName ,Map<Book, Integer> books) {
		Date today = new Date();
		if (expiryDate.before(today)) {
			return "expired creditCard";
		}

		Connection connection = dataManager.getConnection();
		try {
			connection.setAutoCommit(false);
			for(Entry<Book, Integer> b : books.entrySet()) {
				String query = "update book set stock = stock - ? where isbn = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, b.getValue());
				statement.setInt(2, b.getKey().getISBN());
				statement.executeUpdate();
				
				String querySale = "insert into sales values(?,?,?,?)";
				statement = connection.prepareStatement(querySale);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				statement.setTimestamp(1,timestamp);
				statement.setInt(2,b.getValue());
				statement.setString(3, userName);
				statement.setInt(4, b.getKey().getISBN());
				statement.executeUpdate();
			}
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return "success";
	}

	public double totalSales(int days) {
		double totalSales= 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -30);
			long monthAgo = cal.getTimeInMillis();
			Timestamp timestamp = new Timestamp(monthAgo);
			
			Connection connection = dataManager.getConnection();
			String query = "select sum(no_of_copies*book.price) as book_sales "
					+ "from (sales inner join book on book_isbn = isbn) where timestamp  > ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setTimestamp(1, timestamp);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				totalSales += rs.getDouble(1);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return totalSales;
	}

	public List<Pair<String, Integer>> topCustomers(int num) {
		List<Pair<String, Integer>> list = new ArrayList<Pair<String,Integer>>();
		try {
			Connection connection = dataManager.getConnection();
			String query = "select sum(no_of_copies) as most_purchase,username " + 
					"from sales group by username order by  sum(no_of_copies) desc  limit 5;";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Pair<String,Integer> pair =  new Pair<String, Integer>(rs.getString(2), rs.getInt(1));
				list.add(pair);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Pair<Book, Integer>>  topBooks(int num) {
		List<Pair<Book, Integer>> booklist  = new ArrayList<Pair<Book,Integer>>();
		try {
			Connection connection = dataManager.getConnection();
			String query =  "select sum(no_of_copies) as amount_sold"
					+ " ,Book_ISBN,title,pyear,price,category,publisher_name,stock_min,stock from "
					+ "(sales inner join book on Book_ISBN = isbn) group by Book_ISBN order by"
					+ "  sum(no_of_copies) desc  limit 10";;
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getInt(2));
				book.setTitle(rs.getString(3));
				book.setPublicationYear(rs.getDate(4));
				book.setPrice(rs.getInt(5));
				book.setISBN(rs.getInt(6));
				Publisher publisher = new Publisher();
				publisher.setName(rs.getString(7));
				book.setPublisher(publisher);
				book.setStockMin(rs.getInt(8));
				book.setStock(rs.getInt(9));
				Pair<Book, Integer> pair =  new Pair<Book, Integer>(book, rs.getInt(1));
				booklist.add(pair);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return booklist;
	}

}
