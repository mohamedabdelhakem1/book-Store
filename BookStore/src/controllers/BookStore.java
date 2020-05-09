package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Book;
import models.Order;
import models.User;

public class BookStore {
	private User user;
	private Connection con;
	private Statement st;
	private static String url = "jdbc:mysql://localhost:3306/company?useSSL=false";
    private static String db_user = "root";
    private static String password = "123456789";
    private static String query = "SELECT * from employee";
    
	BookStore(){
		try {
			con = DriverManager.getConnection(url, db_user, password);
	        Statement st = con.createStatement();
	    } catch (SQLException ex) {
	        Logger lgr = Logger.getLogger(BookStore.class.getName());
	        lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    }
		
	}
	public void signUp(String userName,String password,
			String email, String fname, String lastname,String shippingAddress,
			String phoneNum) {
		user = new User();
		user.setEmail(email);
		user.setFirstName(fname);
		user.setLastName(lastname);
		user.setPhoneNumber(phoneNum);
		user.setShippingAddress(shippingAddress);
		user.signup(password);
		
	}
	public void login(String userName,String password) {
		user = new User();
	}
	
	public boolean addNewBook(Book book) {
		if(!user.isManager()) {
			//Denied
			return false;
		}
		//add the book
		return true;
	}
	
	public boolean updateBook(Book book) {
		if(!user.isManager()) {
			//Denied
			return false;
		}
		//if book exists
		//negative stock is denied
		//add the book
		return true;
	}
	
	public boolean placeOrder(Book book, int quantity) {
		if(!user.isManager()) {
			//Denied
			return false;
		}
		return true;
	}
	
	public boolean confirmOrder(Order order) {
		if(!user.isManager()) {
			//Denied
			return false;
		}
		return true;
	}
	
	public Book findBook(int ISBN) {
		return null;
	}
	public Book findBook(String title) {
		return null;
	}
}
