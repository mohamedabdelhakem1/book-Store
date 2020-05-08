package controllers;

import java.util.List;
import java.util.Map;

import models.Book;
import models.User;

public class SalesRoute {
	
	public boolean checkout(String creditCard, Map<Book, Integer> books) {
		return false;
	}
	
	public double totalSales(int days) {
		return 0;
	}
	public List<User> topCustomers(int num){
		return null;
	}
	public List<Book> topBooks(int num){
		return null;
	}

}
