package controllers;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Book;
import models.Order;
import models.Publisher;

public class BookRoute {
	private DataManager dataManager;

	public BookRoute() {
		dataManager = DataManager.getInstance();
	}

	public Book create(Book book) {
		Connection connection = dataManager.getConnection();
		String query = "insert into book values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, book.getISBN());
			statement.setString(2, book.getTitle());
			statement.setDate(3, (Date) book.getPublicationYear());

			statement.setDouble(4, book.getPrice());
			statement.setString(5, book.getCategory());

			statement.setString(6, book.getPublisher().getName());
			statement.setInt(7, book.getStockMin());
			statement.setInt(8, book.getStock());
			statement.executeUpdate();
		} catch (SQLException e) {
			return null;
		}
		return book;
	}

	public Book update(Book book, int ISBN) {
		Connection connection = dataManager.getConnection();
		String query = "update book set isbn=?,title=?,pyear=?,price=?,category=?,publisher_name=?,stock_min=?,stock=? where isbn =? ";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, book.getISBN());
			statement.setString(2, book.getTitle());
			statement.setDate(3, (Date) book.getPublicationYear());
			statement.setDouble(4, book.getPrice());
			statement.setString(5, book.getCategory());
			statement.setString(6, book.getPublisher().getName());
			statement.setInt(7, book.getStockMin());
			statement.setInt(8, book.getStock());
			statement.setInt(9, ISBN);
			statement.executeUpdate();
		} catch (SQLException e) {
			return null;
		}
		return book;
	}

	public Order placeOrder(Book book, int quantity) {
		Connection connection = dataManager.getConnection();
		String query = "insert into orders values(?,?) on duplicate key update quantity = quantity + ?";
		Order order = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, quantity);
			statement.setInt(2, book.getISBN());
			statement.setInt(3, quantity);
			statement.executeUpdate();
			order = new Order();
			order.setISBN(book.getISBN());
			order.setQuantity(quantity);
		} catch (SQLException e) {
			return null;
		}
		return order;
	}

	public boolean confirmOrder(int ISBN) {
		Connection connection = dataManager.getConnection();
		String query = "delete from orders where book_isbn = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, ISBN);
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 0)
				throw new SQLException();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public List<Order> getOrders(List<Integer> ISBNs) {
		Connection connection = dataManager.getConnection();
		List<Order> orders = new ArrayList<Order>();
		if (ISBNs == null) {
			String query = "select * from orders";
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					Order order = new Order();
					order.setQuantity(rs.getInt(1));
					order.setISBN(rs.getInt(2));
					orders.add(order);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			try {
				String query = "select * from orders where book_isbn in (?)";
				PreparedStatement statement = connection.prepareStatement(query);
				Object[] elements = new Object[ISBNs.size()];
				for (int i = 0; i < ISBNs.size(); i++) {
					elements[i] = ISBNs.get(i);
				}
				Array array = connection.createArrayOf("INT", elements);
				statement.setArray(1, array);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					Order order = new Order();
					order.setQuantity(rs.getInt(1));
					order.setISBN(rs.getInt(2));
					orders.add(order);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orders;
	}

	public List<Book> getBooks(Book book) {
		List<Book> books = new ArrayList<Book>();
		StringBuilder queryBuilder = new StringBuilder(
				"select * from (book inner join publisher on book.publisher_name = publisher.name) where ");
		int first = 0;
		List<Object> paramList = new ArrayList<Object>();
		if (book != null) {
			if (book.getISBN() != -1) {
				queryBuilder.append(" isbn = ? ");
				paramList.add(book.getISBN());
				first++;
			}
			if (book.getTitle() != null) {
				if (first > 0)
					queryBuilder.append("and");
				queryBuilder.append(" title = ? ");
				paramList.add(book.getTitle());
				first++;
			}
			if (book.getPublicationYear() != null) {
				if (first > 0)
					queryBuilder.append("and");
				queryBuilder.append(" pyear = ? ");
				paramList.add(book.getPublicationYear());
				first++;
			}
			if (book.getPrice() != -1) {
				if (first > 0)
					queryBuilder.append("and");
				queryBuilder.append(" price = ? ");
				paramList.add(book.getPrice());
				first++;
			}
			if (book.getCategory() != null) {
				if (first > 0)
					queryBuilder.append("and");
				queryBuilder.append(" category = ? ");
				paramList.add(book.getCategory());
				first++;
			}
			if (book.getPublisher().getName() != null) {
				if (first > 0)
					queryBuilder.append("and");
				queryBuilder.append(" publisher_name = ? ");
				paramList.add(book.getPublisher().getName());
				first++;
			}
			if (book.getStockMin() != -1) {
				if (first > 0)
					queryBuilder.append("and");
				queryBuilder.append(" stock_min = ? ");
				paramList.add(book.getStockMin());
				first++;
			}
			if (book.getStock() != -1) {
				if (first > 0)
					queryBuilder.append("and");
				queryBuilder.append(" stock = ? ");
				paramList.add(book.getStock());
				first++;
			}
		} 
		if(first == 0) {
			queryBuilder = new StringBuilder(
					"select * from (book inner join publisher on book.publisher_name = publisher.name)");
		}
		try {
			Connection connection = dataManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
			int i = 1;
			for (Object obj : paramList) {
				preparedStatement.setObject(i, obj);
				System.out.println(obj.toString());
				i++;
			}
			System.out.println(queryBuilder.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Book bk = new Book();
				bk.setISBN(rs.getInt(1));
				bk.setTitle(rs.getString(2));
				bk.setPublicationYear(rs.getDate(3));
				bk.setPrice(rs.getInt(4));
				bk.setCategory(rs.getString(5));
				Publisher publisher = new Publisher();
				publisher.setName(rs.getString(6));
				bk.setStockMin(rs.getInt(7));
				bk.setStock(rs.getInt(8));
				publisher.setAddress(rs.getString(10));
				publisher.setPhoneNumber(rs.getString(11));
				bk.setPublisher(publisher);
				books.add(bk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}
}
