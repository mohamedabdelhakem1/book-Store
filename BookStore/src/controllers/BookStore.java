package controllers;

import models.Book;
import models.Order;
import models.User;

public class BookStore {
private User user;
	public BookStore(){

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
		user = new User(userName, password);
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
