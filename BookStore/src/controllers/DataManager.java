package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Book;
import models.Order;
import models.User;

public class DataManager {
	private static DataManager dataManager;
	private String url = "jdbc:mysql://localhost:3306/company?useSSL=false";
	private String user = "root";
	private String password = "123456789";
	private static Connection connection;
	

	Connection con = DriverManager.getConnection(url, user, password);
	private DataManager() {
	}

	public static DataManager getInstance() {
		if (dataManager == null)
			dataManager = new DataManager();
		return dataManager;
	}
	public void execute(string ) {

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query)
	        try () {
	        	ResultSetMetaData mt = rs.getMetaData();
	        	int col = mt.getColumnCount();
	        	while(rs.next()) {
	        		for(int i = 1; i <= col ; i++) {
	        			String colval = rs.getString(i);
	        			System.out.print(colval + " ");
	        		}
	        		System.out.println();
	        	}

	        } catch (SQLException ex) {
	        
	            Logger lgr = Logger.getLogger(Main.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        } 
		
		
	}

}
