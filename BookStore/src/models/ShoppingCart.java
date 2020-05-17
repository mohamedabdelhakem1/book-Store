package models;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	private Map<Book, Integer> books;
	
	public ShoppingCart() {
		books = new HashMap<Book, Integer>();
	}

	public void addBook(Book book, int copies) {
		if(books.containsKey(book)) {
			books.put(book, books.get(book)+1);
		} else {
			books.put(book, 1);
		}
	}
	
	public void removeCopy(Book book) {
		if(books.containsKey(book)) {
			books.put(book, (books.get(book)>0?books.get(book)-1:0));
		}
		if(books.get(book) == 0) {
			books.remove(book);
		}
	}
	
	public void removeAllCopies() {
		books.clear();
	}
	
	public int getNumberOfItems() {
		int sum = 0;
		for(int v: books.values()) {
			sum+=v;
		}
		return sum;
	}
	
	public Map<Book, Integer> getItems(){
		return books;
	}
	public int getQuantity(Book book) {
		return books.get(book);
	}
	
	public boolean hasBook(Book book) {
		return books.containsKey(book);
	}
}
