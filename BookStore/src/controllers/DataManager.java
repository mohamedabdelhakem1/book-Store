package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataManager {
	private static DataManager dataManager;
	private static String url = "jdbc:mysql://localhost:3306/bookstore?useSSL=false";
	private static String user = "root";
	private static String password = "123456789";
	private static Connection connection;

	private DataManager() {
	}

	public static DataManager getInstance() {
		if (dataManager == null) {
			dataManager = new DataManager();
			try {
				connection = DriverManager.getConnection(url, user, password);
				removeExpiredSales();
			} catch (SQLException e) {
				Logger lgr = Logger.getLogger(DataManager.class.getName());
				lgr.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return dataManager;
	}
	public Connection getConnection() {
		return connection;
	}
	public static void removeExpiredSales() {
		String query = "delete FROM sales WHERE sales.timestamp < (now()- INTERVAL 3 MONTH)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet execute(String query) throws SQLException {
		ResultSet rs = null;
		Statement statement = connection.createStatement();
		rs = statement.executeQuery(query);
		return rs;
	}

}
