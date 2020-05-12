package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.BookStore;
import views.small.BookDetails;
import views.small.OrdersPanel;
import views.small.UserSettings;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class Settings extends JFrame {

	private JPanel contentPane;
	private BookStore engine;
	private JTextField txtUserName;
	private JTextField textField;

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
	public Settings(BookStore engine) {
		this.engine = engine;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 85, 634, 461);
		scrollPane.setMinimumSize(new Dimension(634, 461));
		scrollPane.setPreferredSize(new Dimension(634, 461));
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
//		panel.setBounds(0, 0, 650, 1000);
		panel.setSize(634, 1000);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {580};
		gbl_panel.rowHeights = new int[] {240, 1, 100, 1, 390, 1, 60};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		UserSettings userSettings = new UserSettings(engine);
		GridBagConstraints gbc_userSettings = new GridBagConstraints();
		gbc_userSettings.fill = GridBagConstraints.BOTH;
		gbc_userSettings.insets = new Insets(0, 0, 5, 0);
		gbc_userSettings.gridx = 0;
		gbc_userSettings.gridy = 0;
		panel.add(userSettings, gbc_userSettings);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		panel.add(separator, gbc_separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(engine.getUser().isManager());
		
		JButton btnNewButton = new JButton("Promote");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtUserName.getText();
				engine.makeManager(userName);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Poppins", Font.PLAIN, 15));
		btnNewButton.setBounds(430, 0, 150, 40);
		panel_1.add(btnNewButton);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtUserName.setText("User Name");
		txtUserName.setBounds(192, 1, 228, 40);
		panel_1.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Promote User:");
		lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 0, 150, 40);
		panel_1.add(lblNewLabel);
		
		JLabel lblBookIsbn = new JLabel("Book ISBN:");
		lblBookIsbn.setFont(new Font("Poppins", Font.BOLD, 15));
		lblBookIsbn.setBounds(10, 49, 150, 40);
		panel_1.add(lblBookIsbn);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setFont(new Font("Poppins", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(192, 49, 68, 40);
		panel_1.add(textField);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home(engine);
				BookDetails bd = new BookDetails(engine, engine.getBook(Integer.valueOf(textField.getText())) ,true);
				home.setScrollPane(bd);
				home.run();
				close();
			}
		});
		btnEdit.setFont(new Font("Poppins", Font.PLAIN, 15));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(270, 49, 150, 40);
		panel_1.add(btnEdit);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home(engine);
				BookDetails bd = new BookDetails(engine, null,false);
				home.setScrollPane(bd);
				home.run();
				close();
			}
		});
		btnAddNew.setFont(new Font("Poppins", Font.PLAIN, 15));
		btnAddNew.setBackground(Color.WHITE);
		btnAddNew.setBounds(430, 49, 150, 40);
		panel_1.add(btnAddNew);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		panel.add(separator_1, gbc_separator_1);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		OrdersPanel opanel = new OrdersPanel(engine);
		GridBagConstraints gbc_opanel = new GridBagConstraints();
		gbc_opanel.insets = new Insets(0, 0, 5, 0);
		gbc_opanel.fill = GridBagConstraints.BOTH;
		gbc_opanel.gridx = 0;
		gbc_opanel.gridy = 4;
		opanel.setVisible(engine.getUser().isManager());
		panel.add(opanel, gbc_opanel);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_1_1 = new GridBagConstraints();
		gbc_separator_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1_1.gridx = 0;
		gbc_separator_1_1.gridy = 5;
		panel.add(separator_1_1, gbc_separator_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 6;
		panel_2.setVisible(engine.getUser().isManager());
		panel.add(panel_2, gbc_panel_2);
		
		JButton statistics = new JButton("Statistics");
		statistics.setBounds(215, 10, 200, 40);
		statistics.setFont(new Font("Poppins", Font.BOLD, 15));
		statistics.setBackground(Color.WHITE);
		panel_2.add(statistics);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(110, 66, 400, 1);
		contentPane.add(separator_2);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		logoutBtn.setBackground(Color.WHITE);
		logoutBtn.setBounds(435, 11, 100, 40);
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
		cartBtn.setBounds(205, 11, 100, 40);
		cartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cart cart = new Cart(engine);
				cart.run();
				close();
			}
		});
		contentPane.add(cartBtn);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Poppins", Font.BOLD, 15));
		btnHome.setBackground(Color.WHITE);
		btnHome.setBounds(320, 11, 100, 40);
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Home home = new Home(engine);
				home.run();
				close();
			}
		});
		contentPane.add(btnHome);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Poppins", Font.BOLD, 15));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(90, 11, 100, 40);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Search search = new Search(engine);
				search.run();
				close();
			}
		});
		contentPane.add(btnSearch);
	}
	
	
	

}
