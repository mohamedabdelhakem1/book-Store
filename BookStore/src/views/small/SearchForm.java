package views.small;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.BookStore;
import models.Book;
import models.Publisher;
import views.Home;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SearchForm extends JPanel {
	private JTextField title;
	private JTextField isbn;
	private JTextField authors;
	private JTextField year;
	private JTextField price;
	private JTextField category;
	private JTextField publisher;
	private BookStore engine;
	private JLabel lblTitle;
	private JLabel lblIsbn;
	private JLabel lblCategory;
	private JLabel lblPrice;
	private JLabel lblCategory_1;

	/**
	 * Create the panel.
	 */
	public SearchForm(BookStore engine) {
		this.engine = engine;
		setBackground(Color.WHITE);
		setLayout(null);
		
		title = new JTextField();
		title.setFont(new Font("Poppins", Font.PLAIN, 15));
		
		title.setBounds(140, 20, 200, 40);
		add(title);
		title.setColumns(10);
		
		isbn = new JTextField();
		isbn.setFont(new Font("Poppins", Font.PLAIN, 15));
		isbn.setColumns(10);
		isbn.setBounds(460, 20, 100, 40);
		add(isbn);
		
		authors = new JTextField();
		authors.setFont(new Font("Poppins", Font.PLAIN, 15));
		authors.setColumns(10);
		authors.setBounds(140, 79, 200, 40);
		add(authors);
		
		JLabel lblAuthors = new JLabel("Authors:");
		lblAuthors.setFont(new Font("Poppins", Font.BOLD, 15));
		lblAuthors.setBackground(Color.WHITE);
		lblAuthors.setBounds(20, 78, 100, 40);
		add(lblAuthors);
		
		year = new JTextField();
		year.setFont(new Font("Poppins", Font.PLAIN, 15));
		year.setColumns(10);
		year.setBounds(460, 140, 100, 40);
		add(year);
		
		price = new JTextField();
		price.setFont(new Font("Poppins", Font.PLAIN, 15));
		price.setColumns(10);
		price.setBounds(460, 79, 100, 40);
		add(price);
		
		category = new JTextField();
		category.setFont(new Font("Poppins", Font.PLAIN, 15));
		category.setColumns(10);
		category.setBounds(140, 203, 200, 40);
		add(category);
		
		publisher = new JTextField();
		publisher.setFont(new Font("Poppins", Font.PLAIN, 15));
		publisher.setColumns(10);
		publisher.setBounds(140, 140, 200, 40);
		add(publisher);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setFont(new Font("Poppins", Font.BOLD, 15));
		lblPublisher.setBackground(Color.WHITE);
		lblPublisher.setBounds(20, 139, 100, 40);
		add(lblPublisher);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Poppins", Font.BOLD, 15));
		btnNewButton.setBounds(350, 202, 210, 40);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Book book = new Book();
				book.setCategory((category.getText().isEmpty())?null:category.getText());
				book.setISBN(Integer.valueOf((isbn.getText().isEmpty())?"-1":isbn.getText()));
				book.setPrice(Double.valueOf((price.getText().isEmpty())?"-1":price.getText()));
				book.setPublicationYear(year.getText().isEmpty()? null:new Date(Integer.valueOf(year.getText()) ,0 ,0));
				Publisher p = new Publisher();
				p.setName((publisher.getText().isEmpty())?null:publisher.getText());
				book.setPublisher(p);
				book.setTitle((title.getText().isEmpty())?null:title.getText());
				book.setStock(-1);
				book.setStockMin(-1);
				if(!authors.getText().isEmpty()) {
					String a = authors.getText();
					String[] auths = a.split(",");
					for(String auth: auths) {
						auth.trim();
						if(auth.length() > 0) {
							book.addAuther(auth);
						}
					}
				}
				Home home = new Home(engine);
				home.setBooks(engine.findBook(book));
				home.run();
			}
		});
		add(btnNewButton);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Poppins", Font.BOLD, 15));
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setBounds(20, 20, 100, 40);
		add(lblTitle);
		
		lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Poppins", Font.BOLD, 15));
		lblIsbn.setBackground(Color.WHITE);
		lblIsbn.setBounds(350, 19, 100, 40);
		add(lblIsbn);
		
		lblCategory = new JLabel("Year:");
		lblCategory.setFont(new Font("Poppins", Font.BOLD, 15));
		lblCategory.setBackground(Color.WHITE);
		lblCategory.setBounds(350, 139, 100, 40);
		add(lblCategory);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Poppins", Font.BOLD, 15));
		lblPrice.setBackground(Color.WHITE);
		lblPrice.setBounds(350, 79, 100, 40);
		add(lblPrice);
		
		lblCategory_1 = new JLabel("Category:");
		lblCategory_1.setFont(new Font("Poppins", Font.BOLD, 15));
		lblCategory_1.setBackground(Color.WHITE);
		lblCategory_1.setBounds(20, 202, 100, 40);
		add(lblCategory_1);


	}

}
