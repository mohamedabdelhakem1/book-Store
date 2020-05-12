package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.BookStore;
import views.small.SearchForm;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search extends JFrame {

	private JPanel contentPane;
	private BookStore engine;

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
	public Search(BookStore engine) {
		this.engine= engine; 
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
		separator.setBounds(120, 66, 400, 1);
		contentPane.add(separator);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		logoutBtn.setBackground(Color.WHITE);
		logoutBtn.setBounds(445, 11, 100, 40);
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
		
		JButton cartBtn = new JButton("Cart");
		cartBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		cartBtn.setBackground(Color.WHITE);
		cartBtn.setBounds(215, 11, 100, 40);
		cartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cart cart = new Cart(engine);
				cart.run();
				close();
			}
		});
		contentPane.add(cartBtn);
		
		JButton settingsBtn = new JButton("Settings");
		settingsBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		settingsBtn.setBackground(Color.WHITE);
		settingsBtn.setBounds(330, 11, 100, 40);
		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings settings = new Settings(engine);
				settings.run();
				close();
			}
		});
		contentPane.add(settingsBtn);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Poppins", Font.BOLD, 15));
		btnHome.setBackground(Color.WHITE);
		btnHome.setBounds(100, 11, 100, 40);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home(engine);
				home.run();
				close();
			}
		});
		contentPane.add(btnHome);
		
		SearchForm searchForm = new SearchForm(engine);
		searchForm.setBounds(25, 90, 580, 350);
		contentPane.add(searchForm);
	}

}
