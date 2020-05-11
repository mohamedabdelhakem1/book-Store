package views.small;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ScrollPaneConstants;

import controllers.BookStore;
import models.Book;
import models.Order;
import views.small.tiles.OrderTile;

public class OrdersPanel extends JPanel {
	private JTextField txtIsbn;
	private JTextField txtQuantity;
	private BookStore engine;

	/**
	 * Create the panel.
	 */
	public OrdersPanel(BookStore engine) {
		this.engine = engine;
		setBackground(Color.WHITE);
		setLayout(null);
		
		txtIsbn = new JTextField();
		txtIsbn.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtIsbn.setText("ISBN");
		txtIsbn.setBounds(186, 12, 100, 40);
		add(txtIsbn);
		txtIsbn.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Place Order:");
		lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 15));
		lblNewLabel.setBounds(26, 11, 150, 40);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Place Order");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Poppins", Font.PLAIN, 15));
		btnNewButton.setBounds(406, 11, 150, 40);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Book book = engine.getBook(Integer.valueOf(txtIsbn.getText()));
				engine.placeOrder(book, Integer.valueOf(txtQuantity.getText()));
			}
		});
		add(btnNewButton);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("Quantity");
		txtQuantity.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(296, 12, 100, 40);
		add(txtQuantity);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 70, 540, 300);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		scrollPane.setColumnHeaderView(panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblBook = new JLabel("Book ISBN");
		lblBook.setBackground(Color.WHITE);
		lblBook.setFont(new Font("Poppins", Font.BOLD, 15));
		panel_1.add(lblBook);
		
		JLabel lblQuantity = new JLabel("Quantity Ordered");
		lblQuantity.setFont(new Font("Poppins", Font.BOLD, 15));
		lblQuantity.setBackground(Color.WHITE);
		panel_1.add(lblQuantity);
		
		JLabel lblConfirm = new JLabel("Confirm");
		lblConfirm.setFont(new Font("Poppins", Font.BOLD, 15));
		lblConfirm.setBackground(Color.WHITE);
		panel_1.add(lblConfirm);
		orders(panel);

	}
	
	private void orders(JPanel panel) {
		List<Order> orders = engine.getOrders();
		if(orders == null) return;
		for(Order o: orders) {
			OrderTile ot = new OrderTile(o, engine);
			panel.add(ot);
		}
	}

}
