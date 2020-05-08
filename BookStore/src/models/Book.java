package models;

import java.util.Date;
import java.util.List;

public class Book {
	private String title;
	private int ISBN;
	private List<String> authors;
	private Date publicationYear;
	private double price;
	private String category;
	private Publisher publisher;
	private int stockMin;
	private int stock;
	
	Book(int ISBN){
		//todo: load book from DB
	}
	
	Book(){
		
	}
	
	public String getTitle() {
		return title;
	}

	public int getISBN() {
		return ISBN;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public Date getPublicationYear() {
		return publicationYear;
	}

	public double getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public int getStock() {
		return stock;
	}

	public int getStockMin() {
		return stockMin;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPublicationYear(Date publicationYear) {
		this.publicationYear = publicationYear;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	public void addAuther(String name) {
		
	}
}
