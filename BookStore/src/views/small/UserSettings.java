package views.small;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.BookStore;
import models.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class UserSettings extends JPanel {
	private JTextField userName;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JPasswordField password;
	private JTextField address;
	private JTextField phone;
	private BookStore engine;

	/**
	 * Create the panel.
	 */
	public UserSettings(BookStore engine) {
		this.engine = engine;
		setBackground(Color.WHITE);
		setLayout(null);
		
		userName = new JTextField();
		userName.setText(engine.getUser().getUsername());
		userName.setFont(new Font("Poppins", Font.PLAIN, 15));
		userName.setBounds(20, 11, 170, 40);
		add(userName);
		userName.setColumns(10);
		
		firstName = new JTextField();
		firstName.setText(engine.getUser().getFirstName());
		firstName.setFont(new Font("Poppins", Font.PLAIN, 15));
		firstName.setColumns(10);
		firstName.setBounds(204, 11, 170, 40);
		add(firstName);
		
		lastName = new JTextField();
		lastName.setText(engine.getUser().getLastName());
		lastName.setFont(new Font("Poppins", Font.PLAIN, 15));
		lastName.setColumns(10);
		lastName.setBounds(388, 11, 170, 40);
		add(lastName);
		
		email = new JTextField();
		email.setText(engine.getUser().getEmail());
		email.setFont(new Font("Poppins", Font.PLAIN, 15));
		email.setColumns(10);
		email.setBounds(20, 68, 354, 40);
		add(email);
		
		password = new JPasswordField();
		password.setBounds(388, 68, 170, 40);
		password.setText("Password");
		add(password);
		
		address = new JTextField();
		address.setText(engine.getUser().getShippingAddress());
		address.setFont(new Font("Poppins", Font.PLAIN, 15));
		address.setColumns(10);
		address.setBounds(20, 126, 354, 40);
		add(address);
		
		phone = new JTextField();
		phone.setText(engine.getUser().getPhoneNumber());
		phone.setFont(new Font("Poppins", Font.PLAIN, 15));
		phone.setColumns(10);
		phone.setBounds(388, 126, 170, 40);
		add(phone);
		
		JButton save = new JButton("Save");
		save.setBackground(Color.WHITE);
		save.setFont(new Font("Poppins", Font.BOLD, 15));
		save.setBounds(204, 184, 170, 40);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.getUser().setEmail(email.getText());
				engine.getUser().setFirstName(firstName.getText());
				engine.getUser().setLastName(lastName.getText());
				engine.getUser().setPhoneNumber(phone.getText());
				engine.getUser().setShippingAddress(address.getText());
				engine.getUser().setUsername(userName.getText());
				String pw = null;
				if(password.getText() != "Password") {
					pw = password.getText();
				}
				engine.updateUser(pw);				
			}
		});
		add(save);

	}
}
