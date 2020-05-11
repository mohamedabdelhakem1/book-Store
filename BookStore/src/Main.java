import javax.swing.*;

import controllers.BookStore;
import views.Home;
import views.Login;

public class Main {

	public static void main(String[] args) {
		BookStore engine = new BookStore();
		engine.signUp("muhammads97", "mmooss97", "mmooss@gmail.com", "Muhamad", "Salah", "betash", "01205147358");
		Home home = new Home(engine);
		home.run();
//		System.out.println(engine.getUser().getUsername());
//		Login login = new Login(engine);
//		login.run();
//		Connection connection = null;
//		
//	        String url = "jdbc:mysql://localhost:3306/company?useSSL=false";
//	        String user = "root";
//	        String password = "123456789";
//	        String query = "SELECT * from employee";
//
//	        try (Connection con = DriverManager.getConnection(url, user, password);
//	            Statement st = con.createStatement();
//	            ResultSet rs = st.executeQuery(query)) {
//	        	ResultSetMetaData mt = rs.getMetaData();
//	        	int col = mt.getColumnCount();
//	        	while(rs.next()) {
//	        		for(int i = 1; i <= col ; i++) {
//	        			String colval = rs.getString(i);
//	        			System.out.print(colval + " ");
//	        		}
//	        		System.out.println();
//	        	}
//
//	        } catch (SQLException ex) {
//	        
//	            Logger lgr = Logger.getLogger(Main.class.getName());
//	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
//	        } 
//		
		
	}

}
