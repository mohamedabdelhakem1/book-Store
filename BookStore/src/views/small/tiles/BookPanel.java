package views.small.tiles;

import javax.swing.JPanel;

import controllers.BookStore;
import models.Book;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class BookPanel extends JPanel {
	private Book book;
	private BookStore engine;

	/**
	 * Create the panel.
	 */
	public BookPanel(Book book, BookStore engine) {
		this.book = book;
		this.engine = engine;
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel(book.getTitle());
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(book.getPublisher().getName());
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 15));
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(String.valueOf(book.getPrice()));
		lblNewLabel_2.setFont(new Font("Poppins", Font.PLAIN, 15));
		add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Add to Cart");
		btnNewButton.setFont(new Font("Poppins", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.getUser().getCart().addBook(book, 1);
			}
		});
		add(btnNewButton);

	}

}
