package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.User;

public class UserRoute {
	private static DataManager dataManager;

	public UserRoute() {
		dataManager = DataManager.getInstance();
	}

	/* user functions */
	public User auth(String username, String password) {
		User user = null;
		String query = "select * from users where username = ? and password = ?";
		try {
			Connection connection = dataManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();
			ResultSetMetaData mt = rs.getMetaData();
			int col = mt.getColumnCount();
			while (rs.next()) {
				user = new User();
				for (int i = 1; i <= col; i++) {
					String value = rs.getString(i);
					String label = mt.getColumnLabel(i);
					if (label.equals("userName")) {
						user.setUsername(value);
					} else if (label.equals("fname")) {
						user.setFirstName(value);
					} else if (label.equals("Lname")) {
						user.setLastName(value);
					} else if (label.equals("email")) {
						user.setEmail(value);
					} else if (label.equals("phoneNum")) {
						user.setPhoneNumber(value);
					} else if (label.equals("role")) {
						user.setRole(value.equals("1"));
					} else if (label.equals("shipAddress")) {
						user.setShippingAddress(value);
					}
				}
			}
		} catch (SQLException e) {
			user = null;
			Logger lgr = Logger.getLogger(UserRoute.class.getName());
			lgr.log(Level.SEVERE, "Failed to authenticate", e);

		}

		return user;
	}

	public User create(User user, String password) {
		Connection connection = dataManager.getConnection();
		String query = "insert into users values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, password);
			statement.setString(6, user.getShippingAddress());
			statement.setString(7, user.getPhoneNumber());
			statement.setBoolean(8, user.getRole());
			statement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		return user;
	}

	public User update(User user, String password) {
		Connection connection = dataManager.getConnection();
		String query = "update users set username=?,fname=?,Lname=?,email=?,password=?,shipAddress=?,phoneNum=?,role=? where username =?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, password);
			statement.setString(6, user.getShippingAddress());
			statement.setString(7, user.getPhoneNumber());
			statement.setBoolean(8, user.getRole());
			statement.setString(9, user.getUsername());
			statement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		return user;
	}

	public User makeManager(String username) {

		Connection connection = dataManager.getConnection();
		String query = "update users set role= true where username =?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

}
