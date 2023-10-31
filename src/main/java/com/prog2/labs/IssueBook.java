package com.prog2.labs;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class IssueBook extends JFrame {

	private JPanel contentPane;
	private JTextField tfStuId;
	private JTextField tfBookSN;
	public Map<String, String> catalog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getValidationFailureMessage(
			JTextField stuID, 
			JTextField bookSN) {
		
		if (AdminLogin.rbFrench.isSelected()) {
			try {
				Integer.parseInt(stuID.getText());
			} catch(NumberFormatException n){
				return "ID Étudiant Valide Requis";
			}
			if (bookSN.getText().equals("")) {
				return "NS Valide Requis";
			}
			return null;
		} else {
		
			try {
				Integer.parseInt(stuID.getText());
			} catch(NumberFormatException n){
				return "Valid student ID required";
			}
			if (bookSN.getText().equals("")) {
				return "Book SN required";
			}
			return null;
		}
	}
	
	public void clearFields() {
		
		for (int i = 0; i < contentPane.getComponentCount(); i++) {
			Component c = contentPane.getComponent(i);
			if (c instanceof JTextField) {
				JTextField textField = (JTextField) c;
				textField.setText("");
			}
		}
	}
	
	/**
	 * Create the frame.
	 */
	public IssueBook() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIssueBook = new JLabel("Issue Book");
		lblIssueBook.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIssueBook.setBounds(21, 11, 124, 34);
		contentPane.add(lblIssueBook);
		
		JLabel lblStuId = new JLabel("Student ID");
		lblStuId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStuId.setBounds(21, 56, 93, 14);
		contentPane.add(lblStuId);
		
		JLabel lblBookSN = new JLabel("Book SN");
		lblBookSN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookSN.setBounds(21, 85, 93, 14);
		contentPane.add(lblBookSN);
		
		tfStuId = new JTextField();
		tfStuId.setBounds(134, 54, 192, 20);
		contentPane.add(tfStuId);
		tfStuId.setColumns(10);
		
		tfBookSN = new JTextField();
		tfBookSN.setColumns(10);
		tfBookSN.setBounds(134, 81, 192, 20);
		contentPane.add(tfBookSN);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(21, 146, 300, 20);
		contentPane.add(lblError);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String errorString = getValidationFailureMessage(tfStuId, tfBookSN);
				if(errorString != null) {
					lblError.setText(errorString);
					return;
				}
				else {
					LibrarianController controller2 = new LibrarianController();
					catalog = controller2.getCatalog();
					int stuId = Integer.parseInt(tfStuId.getText());
	//				String stuName = tfStuName.getText();
	//				String StuContact = tfStudentContact.getText();
	 				String bookSN = tfBookSN.getText().toUpperCase();
	//				String bookName = tfBookName.getText();
					
					// note: add condition for matching student's value
					if (controller2.getStudentMap().containsKey(stuId)) {
						String book = catalog.get(bookSN);
							System.out.println();
							System.out.println("check: " + book); // check
						String studentString = controller2.getStudentMap().get(stuId);
							System.out.println("check: " + studentString); // check
							System.out.println("check available: " + book.substring(book.length() - 1)); // check
							System.out.println();
						if(book.substring(book.length() - 1).equals("Y")) {
							String[] bookData = book.split(",");
							String[] studentData = studentString.split(",");
							Student tempStudent = new Student(Integer.parseInt(studentData[0]), studentData[1], studentData[2]);
							Book tempBook = new Book(bookData[0], bookData[1], bookData[2], bookData[3], Double.parseDouble(bookData[4]), 
														Integer.parseInt(bookData[5]), Integer.parseInt(bookData[6]), 
														LocalDate.parse(bookData[7]));
	//													Integer.parseInt(bookData[7]), LocalDate.parse(bookData[8]));	
							controller2.issueBook(tempBook, tempStudent);
							clearFields();
							
							if (AdminLogin.rbFrench.isSelected()) {
								JOptionPane.showMessageDialog(null, "Livre Prêté avec Succès.", "", 2);
							} else JOptionPane.showMessageDialog(null, "Book is issued successfully.", "", 2);
						} else {
							if (AdminLogin.rbFrench.isSelected()) {
								JOptionPane.showMessageDialog(null, "Livre Non-Disponible.", "", 2);
							} else {
								JOptionPane.showMessageDialog(null, "Book is not available currently.", "", 2);
							}
						}
					} else {
						if (AdminLogin.rbFrench.isSelected()) {
							JOptionPane.showMessageDialog(null, "L'Étudiant n'est pas Membre.", "", 2);
						} else {
							JOptionPane.showMessageDialog(null, "Student is not a member.", "", 2);
						}
					}
						
					
				// catalog = controller.viewCatalog();
//				System.out.println(catalog.toString());
//				for (String value: catalog.values()) {
//					System.out.println(value);
//				}
				}
			}
				
		});
		btnIssueBook.setBounds(135, 112, 99, 23);
		contentPane.add(btnIssueBook);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(239, 112, 87, 23);
		contentPane.add(btnCancel);
		
		if (AdminLogin.rbFrench.isSelected()) {
			ResourceBundle messages = ResourceBundle.getBundle("com.prog2.labs.messages_fr");
			lblIssueBook.setText(messages.getString("Issue.Book"));
			lblBookSN.setText(messages.getString("Book.SN"));
			lblStuId.setText(messages.getString("Student.ID"));
			btnIssueBook.setText(messages.getString("Issue.Book"));
			btnCancel.setText(messages.getString("Cancel"));
		}

	}
}
