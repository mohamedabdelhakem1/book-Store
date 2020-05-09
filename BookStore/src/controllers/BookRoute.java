package controllers;

import java.util.List;

import models.Book;
import models.Order;

public class BookRoute {
	
	public Book create() {
		return null;
	}
	public Book update(Book book) {
		return null;
	}
	public Order placeOrder(Book book, int quantity) {
		return null;
	}
	public boolean confirmOrder(int ISBN) {
		return false;
	}
	
	public List<Order> getOrders(List<Integer> ISBNs){
		return null;
	}
	public List<Book> getBooks(Book book){
		return null;
	}
}
