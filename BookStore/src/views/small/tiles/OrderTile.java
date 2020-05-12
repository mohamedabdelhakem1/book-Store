package views.small.tiles;

import javax.swing.JPanel;

import controllers.BookStore;
import models.Order;
import views.small.OrdersPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class OrderTile extends JPanel {
	private Order order;
	private BookStore engine;
	private OrdersPanel op;

	/**
	 * Create the panel.
	 */
	public OrderTile(Order order, BookStore engine, OrdersPanel ordersPanel) {
		this.op = ordersPanel;
		this.engine = engine;
		setBackground(Color.WHITE);
		this.order = order;
		setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblIsbn = new JLabel(String.valueOf(order.getISBN()));
		lblIsbn.setFont(new Font("Poppins", Font.BOLD, 15));
		lblIsbn.setBackground(Color.WHITE);
		add(lblIsbn);
		
		JLabel lblQuantity = new JLabel(String.valueOf(order.getQuantity()));
		lblQuantity.setFont(new Font("Poppins", Font.BOLD, 15));
		lblQuantity.setBackground(Color.WHITE);
		add(lblQuantity);
		
		JButton btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.setFont(new Font("Poppins", Font.PLAIN, 15));
		btnConfirmOrder.setBackground(Color.WHITE);
		btnConfirmOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.confirmOrder(order);
				op.orders();
			}
		});
		add(btnConfirmOrder);
	}

}
