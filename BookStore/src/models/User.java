package models;

import java.sql.Statement;

public class User {
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String shippingAddress;
	private String phoneNumber;
	private boolean role;
	private ShoppingCart cart;

	public User(){
		cart = new ShoppingCart();
		role = false;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public boolean	getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public ShoppingCart getCart() {
		return cart;
	}
	
	public boolean isManager() {
		return role;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
