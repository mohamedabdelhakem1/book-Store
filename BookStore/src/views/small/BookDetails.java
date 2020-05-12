package views.small;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.BookStore;
import models.Book;
import models.Publisher;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class BookDetails extends JPanel {
	private JTextField title;
	private JTextField isbn;
	private JTextField authors;
	private JTextField year;
	private JTextField price;
	private JTextField category;
	private JTextField publisher;
	private JTextField threshold;
	private JTextField textField;
	private Book book;
	private BookStore engine;

	/**
	 * Create the panel.
	 */
	public BookDetails(BookStore engine, Book book) {
		this.book = book;
		this.engine = engine;
		setBackground(Color.WHITE);
		setLayout(null);
		
		title = new JTextField();
		title.setEnabled(engine.getUser().isManager());
		title.setFont(new Font("Poppins", Font.PLAIN, 15));
		
		title.setBounds(20, 20, 343, 40);
		add(title);
		title.setColumns(10);
		
		isbn = new JTextField();
		isbn.setFont(new Font("Poppins", Font.PLAIN, 15));
		isbn.setEnabled(engine.getUser().isManager());
		isbn.setColumns(10);
		isbn.setBounds(373, 20, 115, 40);
		add(isbn);
		
		authors = new JTextField();
		authors.setFont(new Font("Poppins", Font.PLAIN, 15));
		authors.setEnabled(engine.getUser().isManager());
		authors.setColumns(10);
		authors.setBounds(140, 79, 348, 40);
		add(authors);
		
		JLabel lblAuthors = new JLabel("Authors:");
		lblAuthors.setFont(new Font("Poppins", Font.BOLD, 15));
		lblAuthors.setBackground(Color.WHITE);
		lblAuthors.setBounds(20, 78, 100, 40);
		add(lblAuthors);
		
		year = new JTextField();
		year.setFont(new Font("Poppins", Font.PLAIN, 15));
		year.setEnabled(engine.getUser().isManager());
		year.setColumns(10);
		year.setBounds(20, 201, 138, 40);
		add(year);
		
		price = new JTextField();
		price.setFont(new Font("Poppins", Font.PLAIN, 15));
		price.setEnabled(engine.getUser().isManager());
		price.setColumns(10);
		price.setBounds(184, 201, 138, 40);
		add(price);
		
		category = new JTextField();
		category.setFont(new Font("Poppins", Font.PLAIN, 15));
		category.setEnabled(engine.getUser().isManager());
		category.setColumns(10);
		category.setBounds(350, 201, 138, 40);
		add(category);
		
		publisher = new JTextField();
		publisher.setFont(new Font("Poppins", Font.PLAIN, 15));
		publisher.setEnabled(engine.getUser().isManager());
		publisher.setColumns(10);
		publisher.setBounds(140, 140, 348, 40);
		add(publisher);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setFont(new Font("Poppins", Font.BOLD, 15));
		lblPublisher.setBackground(Color.WHITE);
		lblPublisher.setBounds(20, 139, 100, 40);
		add(lblPublisher);
		
		threshold = new JTextField();
		threshold.setFont(new Font("Poppins", Font.PLAIN, 15));
		threshold.setEnabled(engine.getUser().isManager());
		threshold.setColumns(10);
		threshold.setBounds(112, 252, 46, 40);
		add(threshold);
		
		JLabel lblThreshold = new JLabel("Threshold:");
		lblThreshold.setFont(new Font("Poppins", Font.BOLD, 15));
		lblThreshold.setBackground(Color.WHITE);
		lblThreshold.setBounds(20, 252, 89, 40);
		add(lblThreshold);
		
		textField = new JTextField();
		textField.setFont(new Font("Poppins", Font.PLAIN, 15));
		textField.setEnabled(engine.getUser().isManager());
		textField.setColumns(10);
		textField.setBounds(276, 252, 46, 40);
		add(textField);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("Poppins", Font.BOLD, 15));
		lblStock.setBackground(Color.WHITE);
		lblStock.setBounds(184, 252, 100, 40);
		add(lblStock);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setEnabled(engine.getUser().isManager());
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Poppins", Font.BOLD, 15));
		btnNewButton.setBounds(350, 252, 138, 40);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(category.getText()!=null||category.getText()!="") {
					book.setCategory(category.getText());
				}
				if(isbn.getText()!=null||isbn.getText()!="") {
					book.setISBN(Integer.valueOf(isbn.getText()));
				}
				if(price.getText()!=null||price.getText()!="") {
					book.setPrice(Double.valueOf(price.getText()));
				}
				if(year.getText()!=null||year.getText()!="") {
					book.setPublicationYear(new Date(year.getText()));
				}
				if(publisher.getText()!=null||publisher.getText()!="") {
					Publisher p = new Publisher();
					p.setName(publisher.getText());
					book.setPublisher(p);
				}
				if(title.getText()!=null||title.getText()!="") {
					book.setTitle(title.getText());
				}
				if(authors.getText()!=null||authors.getText()!="") {
					book.removeAuthors();
					String a = authors.getText();
					String[] auths = a.split(",");
					for(String auth: auths) {
						auth.trim();
						if(auth.length() > 0) {
							book.addAuther(auth);
						}
					}
				}
				if(threshold.getText()!=null||threshold.getText()!="") {
					book.setStockMin(Integer.valueOf(threshold.getText()));
				}
				if(textField.getText()!=null||textField.getText()!="") {
					book.setStock(Integer.valueOf(textField.getText()));
				}
				engine.updateBook(book);
			}
		});
		add(btnNewButton);
		
		if(book != null) {
			title.setText(book.getTitle());
			isbn.setText(String.valueOf(book.getISBN()));
			year.setText(book.getPublicationYear().toString());
			price.setText(String.valueOf(book.getPrice()));
			publisher.setText(book.getPublisher().getName());
			threshold.setText(String.valueOf(book.getStockMin()));
			textField.setText(String.valueOf(book.getStock()));
			String auth = "";
			for(String a: book.getAuthors()){
				auth += a + ", ";
			}
			authors.setText(auth);
		} else {
			title.setText("Title");
			isbn.setText("ISBN");
			year.setText("Publication Year");
			price.setText("Price");
			publisher.setText("Publisher");
			threshold.setText("Threshold");
			textField.setText("Stock");
			category.setText("Category");
		}

	}

}
