package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.BookStore;
import models.Book;
import views.small.tiles.CartPanel;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Cart extends JFrame {

	private JPanel contentPane;
	private BookStore engine;
	private JPanel cartItems;
	private JTextField txtExpDate;
	private JTextField txtCardNumber;

	/**
	 * Launch the application.
	 */
	public void run() {
		this.setVisible(true);
	}
	public void close() {
		this.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	
	public void setCart() {
		for(Book b: engine.getUser().getCart().getItems().keySet()) {
			CartPanel cp = new CartPanel(b, engine, this);
			cartItems.add(cp);
		}
	}
	
	public Cart(BookStore engine) {
		this.engine = engine;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(110, 70, 400, 1);
		contentPane.add(separator);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		logoutBtn.setBackground(Color.WHITE);
		logoutBtn.setBounds(435, 15, 100, 40);
		logoutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.logout();
				Login login = new Login(engine);
				login.run();
				close();
			}
		});
		contentPane.add(logoutBtn);
		
		JButton homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		homeBtn.setBackground(Color.WHITE);
		homeBtn.setBounds(205, 15, 100, 40);
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Home home = new Home(engine);
				home.run();
				close();
			}
		});
		contentPane.add(homeBtn);
		
		JButton settingsBtn = new JButton("Settings");
		settingsBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		settingsBtn.setBackground(Color.WHITE);
		settingsBtn.setBounds(320, 15, 100, 40);
		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings settings = new Settings(engine);
				settings.run();
				close();
			}
		});
		contentPane.add(settingsBtn);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Search search = new Search(engine);
				search.run();
				close();
			}
		});
		btnSearch.setFont(new Font("Poppins", Font.BOLD, 15));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(90, 15, 100, 40);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(66, 90, 500, 306);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(new GridLayout(1, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("title");
		lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 15));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Publisher\r\n");
		lblNewLabel_1.setFont(new Font("Poppins", Font.BOLD, 15));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Poppins", Font.BOLD, 15));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("add to cart");
		lblNewLabel_3.setFont(new Font("Poppins", Font.BOLD, 15));
		panel.add(lblNewLabel_3);
		
		cartItems = new JPanel();
		cartItems.setBackground(Color.WHITE);
		scrollPane.setViewportView(cartItems);
		cartItems.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(engine.checkout(txtExpDate.getText(), txtCardNumber.getText())) {
					cartItems.removeAll();
					Home home = new Home(engine);
					home.setVisible(true);
					close();
				}
				
			}
		});
		btnCheckout.setFont(new Font("Poppins", Font.BOLD, 15));
		btnCheckout.setBackground(Color.WHITE);
		btnCheckout.setBounds(416, 407, 150, 40);
		contentPane.add(btnCheckout);
		
		txtExpDate = new JTextField();
		txtExpDate.setToolTipText("User Name");
		txtExpDate.setText("Exp. Date");
		txtExpDate.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		txtExpDate.setColumns(10);
		txtExpDate.setBounds(270, 408, 133, 40);
		contentPane.add(txtExpDate);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setToolTipText("Card");
		txtCardNumber.setText("Card Number");
		txtCardNumber.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		txtCardNumber.setColumns(10);
		txtCardNumber.setBounds(66, 407, 194, 40);
		contentPane.add(txtCardNumber);
	}
	public void delete(CartPanel cp) {
		cartItems.remove(cp);
	}
}
