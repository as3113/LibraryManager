package com.prog2.labs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	public ReturnBook() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStuId = new JLabel("Student ID");
		lblStuId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStuId.setBounds(22, 56, 93, 14);
		contentPane.add(lblStuId);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookId.setBounds(22, 85, 93, 14);
		contentPane.add(lblBookId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(135, 54, 192, 20);
		contentPane.add(textField);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(135, 83, 192, 20);
		contentPane.add(textField_3);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibrarianController controller = new LibrarianController();
				// get the studID and bookSN from textfields
				// get stuString from stuList using studID, get bookString from issuedBooks using bookSN
				// make student and book object only if those strings exist 
//				controller.returnBook( "textFieldGetBookSN", "textFieldGetStudID");
			}
		});
		btnReturn.setBounds(135, 114, 89, 23);
		contentPane.add(btnReturn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(237, 114, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblRetBook = new JLabel("Return Book");
		lblRetBook.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRetBook.setBounds(21, 11, 124, 34);
		contentPane.add(lblRetBook);
	}

}
