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

public class DeleteBook extends JFrame {

	private JPanel contentPane;
	private JTextField tfDBId;
	private JTextField tfDBName;
	private JTextField tfDBQte;
	private JTextField tfDBAuthor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBook frame = new DeleteBook();
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
	public DeleteBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 376, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDelBook = new JLabel("Delete Book");
		lblDelBook.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDelBook.setBounds(21, 22, 124, 34);
		contentPane.add(lblDelBook);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookId.setBounds(21, 67, 93, 14);
		contentPane.add(lblBookId);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookName.setBounds(21, 94, 93, 14);
		contentPane.add(lblBookName);
		
		JLabel lblQte = new JLabel("Quantity");
		lblQte.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQte.setBounds(21, 150, 93, 14);
		contentPane.add(lblQte);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAuthor.setBounds(21, 121, 93, 14);
		contentPane.add(lblAuthor);
		
		tfDBId = new JTextField();
		tfDBId.setColumns(10);
		tfDBId.setBounds(134, 65, 192, 20);
		contentPane.add(tfDBId);
		
		tfDBName = new JTextField();
		tfDBName.setColumns(10);
		tfDBName.setBounds(134, 92, 192, 20);
		contentPane.add(tfDBName);
		
		tfDBQte = new JTextField();
		tfDBQte.setColumns(10);
		tfDBQte.setBounds(134, 146, 192, 20);
		contentPane.add(tfDBQte);
		
		tfDBAuthor = new JTextField();
		tfDBAuthor.setColumns(10);
		tfDBAuthor.setBounds(134, 119, 192, 20);
		contentPane.add(tfDBAuthor);
		
		JButton btnDelBk = new JButton("Delete Book");
		btnDelBk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnDelBk.setBounds(134, 187, 105, 23);
		contentPane.add(btnDelBk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(249, 187, 77, 23);
		contentPane.add(btnCancel);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(21, 187, 103, 23);
		contentPane.add(btnHome);
	}
}
