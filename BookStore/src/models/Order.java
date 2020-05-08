package models;

public class Order {
	private int ISBN;
	private int quantity;
	
	Order(int isbn, int quantity) {
	}
	
	public void placeOrder() {
		
	}
	
	public void confirmOrder() {
		
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
