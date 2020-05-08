package models;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	private Map<Book, Integer> books;
	
	public ShoppingCart() {
		books = new HashMap<Book, Integer>();
	}

	public void addBook(Book book, int copies) {
		
	}
	
	public void removeCopy(Book book) {
		
	}
	
	public void removeAllCopies(Book book) {
		
	}
	
	public int getNumberOfItems() {
		return 0;
	}
	
	public Map<Book, Integer> getItems(){
		return books;
	}
}
