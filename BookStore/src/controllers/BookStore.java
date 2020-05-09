package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Book;
import models.Order;
import models.User;

public class BookStore {
	private User user;
	private UserRoute userRoute;
	private BookRoute bookRoute;
    
	BookStore(){
		userRoute = new UserRoute();
		bookRoute = new BookRoute();
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
		userRoute.create(user);
	}
	public void login(String userName,String password) {
		user = userRoute.auth(userName, password);
	}
	
	public void makeManager(String userName) {
		if(!user.isManager()) {
			//Denied
			return;
		}
		userRoute.makeManager(userName);
	}
	
	public void updateUser() {
		userRoute.update(user);
	}
	
	
	
	public boolean addNewBook(Book book) {
		if(!user.isManager()) {
			//Denied
			return false;
		}
		bookRoute.create(book);
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
		bookRoute.update(book);
		return true;
	}
	
	public boolean placeOrder(Book book, int quantity) {
		if(!user.isManager()) {
			//Denied
			return false;
		}
		bookRoute.placeOrder(book, quantity);
		return true;
	}
	
	public boolean confirmOrder(Order order) {
		if(!user.isManager()) {
			//Denied
			return false;
		}
		bookRoute.confirmOrder(order.getISBN());
		return true;
	}
	
	public List<Book> findBook(Book book) {
		return bookRoute.getBooks(book);
	}
}
