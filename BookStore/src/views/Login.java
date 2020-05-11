package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.BookStore;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField loginUserName;
	private JPasswordField loginPassword;
	private JTextField signupFName;
	private JTextField signupSName;
	private JTextField signuoEmail;
	private JTextField signupUserName;
	private JPasswordField signupPassword;
	private JButton signupBtn;
	private BookStore engine;
	private Login me;
	private JTextField signupAddress;
	private JTextField signupPhoneNumber;

	/**
	 * Launch the application.
	 */
	public void run() {
		this.setVisible(true);
	}
	public void close() {
		this.setVisible(false);
	}
	public void gotoHome() {
		Home home = new Home(engine);
		home.run();
		this.close();
	}

	/**
	 * Create the frame.
	 */
	public Login(BookStore engine) {
		this.engine = engine;
		this.me = this;
	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BookStore");
		lblNewLabel.setFont(new Font("Poppins Black", Font.BOLD, 25));
		lblNewLabel.setBounds(252, 25, 146, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Poppins Medium", Font.PLAIN, 23));
		lblLogin.setBounds(94, 161, 62, 30);
		contentPane.add(lblLogin);
		
		JLabel lblSignup = new JLabel("Signup");
		lblSignup.setFont(new Font("Poppins Medium", Font.PLAIN, 23));
		lblSignup.setBounds(410, 86, 82, 30);
		contentPane.add(lblSignup);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.BLACK);
		separator.setBounds(250, 130, 1, 280);
		contentPane.add(separator);
		
		loginUserName = new JTextField();
		loginUserName.setToolTipText("User Name");
		loginUserName.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		loginUserName.setText("User Name");
		loginUserName.setBounds(50, 210, 150, 40);
		contentPane.add(loginUserName);
		loginUserName.setColumns(10);
		
		loginPassword = new JPasswordField();
		loginPassword.setToolTipText("Password");
		loginPassword.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		loginPassword.setBounds(50, 262, 150, 40);
		loginPassword.setText("password");
		contentPane.add(loginPassword);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		loginBtn.setForeground(Color.BLACK);
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setToolTipText("Login");
		loginBtn.setBounds(75, 312, 100, 40);
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = loginUserName.getText();
				String password = loginPassword.getText();
				if(engine.login(username, password)) {
					gotoHome();
				}
			}
		});
		contentPane.add(loginBtn);
		
		signupFName = new JTextField();
		signupFName.setToolTipText("First Name");
		signupFName.setText("First Name");
		signupFName.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		signupFName.setColumns(10);
		signupFName.setBounds(311, 136, 130, 40);
		contentPane.add(signupFName);
		
		signupSName = new JTextField();
		signupSName.setToolTipText("Second Name");
		signupSName.setText("Second Name");
		signupSName.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		signupSName.setColumns(10);
		signupSName.setBounds(461, 136, 130, 40);
		contentPane.add(signupSName);
		
		signuoEmail = new JTextField();
		signuoEmail.setToolTipText("Email");
		signuoEmail.setText("Email");
		signuoEmail.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		signuoEmail.setColumns(10);
		signuoEmail.setBounds(311, 188, 280, 40);
		contentPane.add(signuoEmail);
		
		signupUserName = new JTextField();
		signupUserName.setToolTipText("User Name");
		signupUserName.setText("User Name");
		signupUserName.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		signupUserName.setColumns(10);
		signupUserName.setBounds(311, 238, 130, 40);
		contentPane.add(signupUserName);
		
		signupPassword = new JPasswordField();
		signupPassword.setToolTipText("Password");
		signupPassword.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		signupPassword.setBounds(461, 239, 130, 40);
		signupPassword.setText("password");
		contentPane.add(signupPassword);
		
		signupAddress = new JTextField();
		signupAddress.setToolTipText("Address");
		signupAddress.setText("Address");
		signupAddress.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		signupAddress.setColumns(10);
		signupAddress.setBounds(311, 289, 280, 40);
		contentPane.add(signupAddress);
		
		signupPhoneNumber = new JTextField();
		signupPhoneNumber.setToolTipText("Phone Number");
		signupPhoneNumber.setText("Phone Number");
		signupPhoneNumber.setFont(new Font("Poppins Light", Font.PLAIN, 15));
		signupPhoneNumber.setColumns(10);
		signupPhoneNumber.setBounds(311, 341, 280, 40);
		contentPane.add(signupPhoneNumber);
		
		signupBtn = new JButton("Signup");
		signupBtn.setToolTipText("Signup");
		signupBtn.setForeground(Color.BLACK);
		signupBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		signupBtn.setBackground(Color.WHITE);
		signupBtn.setBounds(410, 392, 100, 40);
		signupBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(engine.signUp(signupUserName.getText(),
						signupPassword.getText(), 
						signuoEmail.getText(), 
						signupFName.getText(), 
						signupSName.getText(), 
						signupAddress.getText(), 
						signupPhoneNumber.getText())) {
					gotoHome();
				}
			}
		});
		contentPane.add(signupBtn);

	}
}
