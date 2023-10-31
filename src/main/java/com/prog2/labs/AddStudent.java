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

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTextField tfStuId;
	private JTextField tfStuName;
	private JTextField tfStuContact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
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
	public AddStudent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddStu = new JLabel("Add Student");
		lblAddStu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddStu.setBounds(21, 11, 124, 34);
		contentPane.add(lblAddStu);
		
		JLabel lblStuId = new JLabel("Student ID");
		lblStuId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStuId.setBounds(21, 56, 93, 14);
		contentPane.add(lblStuId);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStudentName.setBounds(21, 83, 93, 14);
		contentPane.add(lblStudentName);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContact.setBounds(21, 110, 93, 14);
		contentPane.add(lblContact);
		
		tfStuId = new JTextField();
		tfStuId.setColumns(10);
		tfStuId.setBounds(134, 54, 192, 20);
		contentPane.add(tfStuId);
		
		tfStuName = new JTextField();
		tfStuName.setColumns(10);
		tfStuName.setBounds(134, 81, 192, 20);
		contentPane.add(tfStuName);
		
		tfStuContact = new JTextField();
		tfStuContact.setColumns(10);
		tfStuContact.setBounds(134, 108, 192, 20);
		contentPane.add(tfStuContact);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int studentId = Integer.parseInt(tfStuId.getText());
				String studentName = tfStuName.getText();
				String studentContact = tfStuContact.getText();
				
				Student newStudent = new Student(studentId, studentName, studentContact);
				
				LibrarianController.addStudent(newStudent);
			}
		});
		btnAdd.setBounds(135, 150, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(237, 150, 89, 23);
		contentPane.add(btnCancel);
	}

}
