package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
	private SalesRoute salesRoute;
    
	public BookStore(){
		userRoute = new UserRoute();
		bookRoute = new BookRoute();
		salesRoute = new SalesRoute();
	}
	public boolean signUp(String userName,String password,
			String email, String fname, String lastname,String shippingAddress,
			String phoneNum) {
		user = new User();
		user.setUsername(userName);
		user.setEmail(email);
		user.setFirstName(fname);
		user.setLastName(lastname);
		user.setPhoneNumber(phoneNum);
		user.setShippingAddress(shippingAddress);
		User created = userRoute.create(user,password);
		this.user = created;
//		return true;
		return user != null;
		
	}
	public boolean login(String userName,String password) {
		user = userRoute.auth(userName, password);
		return user != null;
	}
	
	public void logout() {
		user = null;
	}
	
	public void makeManager(String userName) {
		if(!user.isManager()) {
			return;
		}
		userRoute.makeManager(userName);
	}
	
	public void updateUser(String password) {
		userRoute.update(user, password);
	}
	
	public User getUser() {
		return this.user;
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
		bookRoute.update(book, book.getISBN());
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
	public List<Order> getOrders(){
		return bookRoute.getOrders(null);
	}
	
	public List<Book> findBook(Book book) {
		return bookRoute.getBooks(book);
	}
	public Book getBook(int ISBN) {
		Book b = new Book();
		b.setISBN(ISBN);
		return bookRoute.getBooks(b).get(0);
	}
	
	public boolean checkout(String eDate, String cardNO) {
		String[] strs = eDate.split("-");
		return salesRoute.checkout(cardNO,new Date( Integer.valueOf(strs[1]),Integer.valueOf(strs[0]),0),user.getUsername(), user.getCart().getItems()).equals("success");
	}
}
