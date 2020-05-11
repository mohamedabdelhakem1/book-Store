import javax.swing.*;

import controllers.BookStore;
import views.Home;
import views.Login;

public class Main {

	public static void main(String[] args) {
		BookStore engine = new BookStore();
		Login login = new Login(engine);
		login.run();

	}

}
