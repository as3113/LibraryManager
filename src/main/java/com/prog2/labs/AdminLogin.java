package com.prog2.labs;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;



public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField passwordField;
	public JButton btnLogin;
	private JLabel lblNMessage;
	private static String dbName = "db_lib"; 
	static String username;
	private static String password;
	private static DbConn dbConnection;
	public static JRadioButton rdbtnLibrarian;
	public static JRadioButton rdbtnStudent;
	public static JRadioButton rdbtnEnglish;
	public static JRadioButton rbFrench;
	public static JRadioButton rdbtnKonkani;
	public static ResourceBundle messages;
	static AdminLogin frame;
	public Map<Integer, String> stuMap;
	public static Locale selectedLan;
	private JLabel lblLanguage;
	private JLabel lblUserType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					AdminLogin frame = new AdminLogin();
					frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Locale.setDefault(new Locale("en", "US"));
				ResourceBundle messages1 = ResourceBundle.getBundle("com.prog2.labs.messages");

				frame.setTitle(messages1.getString("LibraryPortal"));
			}
		});

		setTitle("LibraryPortal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JLabel lblTitle = new JLabel("Login");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(22, 11, 298, 39);
		contentPane.add(lblTitle);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUser.setBounds(163, 71, 172, 14);
		contentPane.add(lblUser);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(163, 91, 152, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPass.setBounds(163, 122, 179, 14);
		contentPane.add(lblPass);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 141, 152, 20);
		contentPane.add(passwordField);
		
		lblNMessage= new JLabel("");
		lblNMessage.setForeground(new Color(255, 0, 0));
		lblNMessage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNMessage.setBounds(163, 172, 163, 14);
		contentPane.add(lblNMessage);
		
		rdbtnLibrarian = new JRadioButton("Librarian");
		rdbtnLibrarian.setBounds(22, 187, 109, 23);
		contentPane.add(rdbtnLibrarian);
		
		rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setBounds(22, 213, 103, 23);
		contentPane.add(rdbtnStudent);
		
		
		// login button
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get instance of the created connection
				dbConnection = DbConn.getInstance(dbName);
				
				// retrieve values of username and password entered by the user
				username = tfUsername.getText();
				password = String.valueOf(passwordField.getPassword());				
				
				// check if the fields are empty
				if (username.trim().equals("") || password.trim().equals("")) { 
//					lblNMessage.setText("Please enter your username and password.");	
					JOptionPane.showMessageDialog(null, "Enter your username and password.", "Empty field(s)", 2);
					tfUsername.requestFocus();
				} else {  // if the fields are not empty
					// librarian: check if username and password are correct
					if (AdminLogin.rdbtnLibrarian.isSelected() && username.equals("lib") && password.equals("123")) {		
//					Connection conn = null;
						AdminMain m = new AdminMain(); // open the AdminMain window
						m.setVisible(true);
						Connection connection = dbConnection.getConnection(); // connect to the database
						frame.dispose(); // close AdminLogin window after librarian login successfully
					} else if (AdminLogin.rdbtnStudent.isSelected() && password.equals("123")) {
//						else if (rdbtnStudent.isSelected() && username.equals("stu") && password.equals("123")) {
						// student: check if username and password are correct
						StuMain s = new StuMain();  // open the StuMain window
						s.setVisible(true);
	//					Connection conn = null;						
						Connection connection = dbConnection.getConnection(); // connect to the database	
						frame.dispose(); // close AdminLogin window after student login successfully
					} else { // if the username and password do not match
	//					lblNMessage.setText("Username and password do not match.");
						JOptionPane.showMessageDialog(null, "Username and password do not match.", "Unmatched field(s)", 2);
						tfUsername.setText("");
						passwordField.setText("");
						tfUsername.requestFocus();
					}	
				}
			}
		});
		btnLogin.setBounds(216, 186, 99, 23);
		contentPane.add(btnLogin);
		
		rdbtnEnglish = new JRadioButton("English");
		rbFrench = new JRadioButton("French");
		rdbtnKonkani = new JRadioButton("Konkani");
		rbFrench.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("fr", "FR"));
				
				
				ResourceBundle messages = ResourceBundle.getBundle("com.prog2.labs.messages_fr");
				System.out.println(messages.getString("Librarian")); 
//				for (String key : messages.keySet()) {
//		            UIManager.put(key, messages.getString(key));
//		        }				
//		        SwingUtilities.updateComponentTreeUI(frame);
				
				frame.setTitle(messages.getString("LibraryPortal"));
				lblLanguage.setText(messages.getString("Language"));
				lblUserType.setText(messages.getString("User.Type"));
				lblUser.setText(messages.getString("Username"));
				lblPass.setText(messages.getString("Password"));
				lblTitle.setText(messages.getString("Login"));
				rdbtnLibrarian.setText(messages.getString("Librarian"));
				rdbtnStudent.setText(messages.getString("Student"));
				rbFrench.setText(messages.getString("French"));
				rdbtnKonkani.setText(messages.getString("Konkani"));
				btnLogin.setText(messages.getString("Login"));
				rdbtnEnglish.setText(messages.getString("English"));
				
		    }
		});
		rbFrench.setBounds(22, 83, 83, 23);
		contentPane.add(rbFrench);
		

//		
		
		rdbtnKonkani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Locale.setDefault(new Locale("kok", "IN"));
				Locale.setDefault(new Locale("kok", "IN"));
				ResourceBundle messages = ResourceBundle.getBundle("com.prog2.labs.messages_kok");
				System.out.println(messages.getString("Librarian")); 
						
				
				frame.setTitle(messages.getString("LibraryPortal"));
				lblLanguage.setText(messages.getString("Language"));
				lblUserType.setText(messages.getString("User.Type"));
				lblUser.setText(messages.getString("Username"));
				lblPass.setText(messages.getString("Password"));
				lblTitle.setText(messages.getString("Login"));
				rdbtnLibrarian.setText(messages.getString("Librarian"));
				rdbtnStudent.setText(messages.getString("Student"));
				rbFrench.setText(messages.getString("French"));
				btnLogin.setText(messages.getString("Login"));
				rdbtnKonkani.setText(messages.getString("Konkani"));
				rdbtnEnglish.setText(messages.getString("English"));
				
				Font konkaniFont = new Font("Nirmala UI", Font.PLAIN, 14); // Change "Nirmala UI" to a font that supports Konkani characters
				frame.setFont(konkaniFont);
				lblLanguage.setFont(konkaniFont);
				lblUserType.setFont(konkaniFont);
				lblUser.setFont(konkaniFont);
				lblPass.setFont(konkaniFont);
				lblTitle.setFont(konkaniFont);
				rdbtnLibrarian.setFont(konkaniFont);
				rdbtnStudent.setFont(konkaniFont);
				rbFrench.setFont(konkaniFont);
				rdbtnKonkani.setFont(konkaniFont);
				rdbtnEnglish.setFont(konkaniFont);
				btnLogin.setFont(konkaniFont);
				
			}
		});
		rdbtnKonkani.setBounds(22, 109, 83, 23);
		contentPane.add(rdbtnKonkani);
		
		
		rdbtnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en", "US"));
				
				ResourceBundle messages = ResourceBundle.getBundle("com.prog2.labs.messages");
				System.out.println(messages.getString("Librarian")); 
						
				
				frame.setTitle(messages.getString("LibraryPortal"));
				lblLanguage.setText(messages.getString("Language"));
				lblUserType.setText(messages.getString("User.Type"));
				lblUser.setText(messages.getString("Username"));
				lblPass.setText(messages.getString("Password"));
				lblTitle.setText(messages.getString("Login"));
				rdbtnLibrarian.setText(messages.getString("Librarian"));
				rdbtnStudent.setText(messages.getString("Student"));
				rbFrench.setText(messages.getString("French"));
				btnLogin.setText(messages.getString("Login"));
				rdbtnKonkani.setText(messages.getString("Konkani"));
				rdbtnEnglish.setText(messages.getString("English"));
			}
		});
		rdbtnEnglish.setBounds(22, 135, 83, 23);
		contentPane.add(rdbtnEnglish);
		
		ButtonGroup languageGroup = new ButtonGroup();
		languageGroup.add(rbFrench);
		languageGroup.add(rdbtnKonkani);
		languageGroup.add(rdbtnEnglish);
		
		ButtonGroup userTypeGroup = new ButtonGroup();
		userTypeGroup.add(rdbtnLibrarian);
		userTypeGroup.add(rdbtnStudent);
		
		lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLanguage.setBounds(25, 55, 64, 30);
		contentPane.add(lblLanguage);
		
		lblUserType = new JLabel("User Type");
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserType.setBounds(22, 165, 105, 21);
		contentPane.add(lblUserType);
		
		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setBounds(137, 61, 2, 185);
		contentPane.add(separator);

		

		

	}
}


