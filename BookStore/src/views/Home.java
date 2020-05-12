package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import controllers.BookStore;
import models.Book;
import models.Publisher;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import views.small.BookDetails;
import views.small.SearchForm;
import views.small.tiles.BookPanel;

import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Home extends JFrame {

	private JPanel contentPane;
	private List<Book> books;
	private JPanel booksPanel;
	private BookStore engine;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public void run() {
		this.setVisible(true);
	}
	public void close() {
		this.setVisible(false);
	}
	
	public void setBooks(List<Book> books) {
		if(books == null) {
//			return;
			books = engine.findBook(null);
		}
		booksPanel.removeAll();
		for(Book b:books) {
			BookPanel bp = new BookPanel(b, engine);
			bp.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					BookDetails bd = new BookDetails(engine, b);
					setScrollPane(bd);
				}
			});
			booksPanel.add(bp);
		}

	}
	public void setScrollPane(BookDetails bd) {
		scrollPane.setViewportView(bd);
	}

	/**
	 * Create the frame.
	 */
	public Home(BookStore engine) {
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
		logoutBtn.setBackground(Color.WHITE);
		logoutBtn.setFont(new Font("Poppins", Font.BOLD, 15));
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
		
		JButton cartBtn = new JButton("Cart");
		cartBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		cartBtn.setBackground(Color.WHITE);
		cartBtn.setBounds(205, 15, 100, 40);
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
		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings settings = new Settings(engine);
				settings.run();
				close();
			}
		});
		settingsBtn.setFont(new Font("Poppins", Font.BOLD, 15));
		settingsBtn.setBackground(Color.WHITE);
		settingsBtn.setBounds(320, 15, 100, 40);
		contentPane.add(settingsBtn);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Poppins", Font.BOLD, 15));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(90, 15, 100, 40);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Search search = new Search(engine);
				search.run();
				close();
			}
		});
		contentPane.add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 350));
		scrollPane.setBounds(66, 90, 500, 350);
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
		
		booksPanel = new JPanel();
		booksPanel.setBackground(Color.WHITE);
		scrollPane.setViewportView(booksPanel);
		booksPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setBooks(null);

	}
}
