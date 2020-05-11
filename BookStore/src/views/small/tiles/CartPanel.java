package views.small.tiles;

import javax.swing.JPanel;

import controllers.BookStore;
import models.Book;
import views.Cart;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class CartPanel extends JPanel {
	private Book book;
	private BookStore engine;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_1;
	private Cart cart;
	/**
	 * Create the panel.
	 */
	private void removeCopy() {
		engine.getUser().getCart().removeCopy(book);
		lblNewLabel_1.setText(String.valueOf(engine.getUser().getCart().getQuantity(book)));
		lblNewLabel_3.setText(String.valueOf(book.getPrice()*engine.getUser().getCart().getQuantity(book)));
		if(!engine.getUser().getCart().hasBook(book)) {
			cart.delete(this);
		}
	}
	public CartPanel(Book book, BookStore engine, Cart cart) {
		this.book = book;
		this.engine = engine;
		this.cart = cart;
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel(book.getTitle());
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(String.valueOf(engine.getUser().getCart().getQuantity(book)));
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 15));
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(String.valueOf(book.getPrice()));
		lblNewLabel_2.setFont(new Font("Poppins", Font.PLAIN, 15));
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel(String.valueOf(book.getPrice()*engine.getUser().getCart().getQuantity(book)));
		lblNewLabel_3.setFont(new Font("Poppins", Font.PLAIN, 15));
		add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Remove a copy");
		btnNewButton.setFont(new Font("Poppins", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCopy();
				
				
			}
		});
		add(btnNewButton);

	}

}
