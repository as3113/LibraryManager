package com.prog2.labs;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField tfABBkId;
	private JTextField tfABBkName;
	private JTextField tfABPublisher;
	private JTextField tfABAuthor;
	private JTextField tfABBkPrice;
	private JTextField tfABQte;
//	private Book newBook;
//	private AdminMain adminMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getValidationFailureMessage(
			JTextField bookSN, 
			JTextField bookName, 
			JTextField author,
			JTextField publisher, 
			JTextField price, 
			JTextField quantity) {
		
		if (AdminLogin.rbFrench.isSelected()) {
			if (bookSN.getText().equals("")) {
				return "SN du Livre requis";
			}		
			if (bookName.getText().equals("")) {
				return "Nom du Livre requis";
			}
			
			if (author.getText().equals("")) {
				return "Auteur requis";
			}
		
			if (publisher.getText().equals("")) {
				return "Éditeur requis";
			}
			try {
				Double.parseDouble(price.getText());
			} catch(NumberFormatException n){
				return "Prix Valide requis";
			}
			try {
				Integer.parseInt(quantity.getText());
			} catch(NumberFormatException n){
				return "Quantité Valide requis";
			}
			return null;
		} else {
		
		if (bookSN.getText().equals("")) {
			return "Book SN required";
		}		
		if (bookName.getText().equals("")) {
			return "Book name required";
		}
		
		if (author.getText().equals("")) {
			return "Author required";
		}
	
		if (publisher.getText().equals("")) {
			return "Publisher required";
		}
		try {
			Double.parseDouble(price.getText());
		} catch(NumberFormatException n){
			return "Valid price required";
		}
		try {
			Integer.parseInt(quantity.getText());
		} catch(NumberFormatException n){
			return "Valid quantity required";
		}
		return null;
		}
	}

	public void clearFields() {
		
		for (int i = 0; i < contentPane.getComponentCount(); i++) {
			Component c = contentPane.getComponent(i);
			if(c instanceof JTextField) {
				JTextField textField = (JTextField) c;
				textField.setText("");
			}
		}
	}
	
	
	// check if the fields are entered correctly 
	
	/**
	 * Create the frame.
	 */
	public AddBook() {
		
		LibrarianController controller = new LibrarianController(new Book(), null, null, null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // EXITONCLOSE
		setBounds(100, 100, 368, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddBook = new JLabel("Add Book");
		lblAddBook.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddBook.setBounds(21, 21, 124, 34);
		contentPane.add(lblAddBook);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookId.setBounds(21, 66, 93, 14);
		contentPane.add(lblBookId);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookName.setBounds(21, 93, 93, 14);
		contentPane.add(lblBookName);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPublisher.setBounds(21, 149, 93, 14);
		contentPane.add(lblPublisher);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAuthor.setBounds(21, 120, 93, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrice.setBounds(21, 176, 93, 14);
		contentPane.add(lblPrice);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(21, 266, 305, 24);
		contentPane.add(lblError);
		
		tfABBkId = new JTextField();
		tfABBkId.setColumns(10);
		tfABBkId.setBounds(134, 64, 192, 20);
		contentPane.add(tfABBkId);
		
		tfABBkName = new JTextField();
		tfABBkName.setColumns(10);
		tfABBkName.setBounds(134, 91, 192, 20);
		contentPane.add(tfABBkName);
		
		tfABPublisher = new JTextField();
		tfABPublisher.setColumns(10);
		tfABPublisher.setBounds(134, 145, 192, 20);
		contentPane.add(tfABPublisher);
		
		tfABAuthor = new JTextField();
		tfABAuthor.setColumns(10);
		tfABAuthor.setBounds(134, 118, 192, 20);
		contentPane.add(tfABAuthor);
		
		tfABBkPrice = new JTextField();
		tfABBkPrice.setColumns(10);
		tfABBkPrice.setBounds(134, 174, 192, 20);
		contentPane.add(tfABBkPrice);
		
		JButton btnAddBk = new JButton("Add Book");
		btnAddBk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String error = getValidationFailureMessage(
						tfABBkId, tfABBkName, tfABAuthor, tfABPublisher, 
						tfABBkPrice, tfABQte);
				if (error != null) {
					lblError.setText(error);
					return;
				} else {
					String sn = tfABBkId.getText();
					String title = tfABBkName.getText();
					String author = tfABAuthor.getText();
					String publisher = tfABPublisher.getText();
					Double price = Double.parseDouble(tfABBkPrice.getText());
					int qte = Integer.parseInt(tfABQte.getText());
					int issued = 0;
	//				int availCopy = qte - issued;
					LocalDate dateOfPurchase = LocalDate.now();
									
					// create a Book instance (set issued quantity to zero (7th arg))
					Book newBook = new Book(sn, title, author, publisher, price, qte, issued, dateOfPurchase);
					// (controller.setBookSN(sn), title, author, publisher, price, qte, 0, dateOfPurchase);
					
					// add newbook to Book HashMap
					LibrarianController.addBook(newBook, "books");				
					clearFields();
					
					if (AdminLogin.rbFrench.isSelected()) {
						lblError.setText("Livre Ajouté");
					} else {
						lblError.setText("Book added successfully");
					}
					// condition: if book  is added successfully, then close the window
					// otherwise, pop up message: not successfully... and refocus cursor
				}
			}
		});
		btnAddBk.setBounds(121, 232, 105, 23);
		contentPane.add(btnAddBk);
		
		// close window when Cancel is clicked
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(236, 232, 90, 23);
		contentPane.add(btnCancel);
		
		tfABQte = new JTextField();
		tfABQte.setColumns(10);
		tfABQte.setBounds(134, 201, 192, 20);
		contentPane.add(tfABQte);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity.setBounds(21, 203, 93, 14);
		contentPane.add(lblQuantity);
		
		if (AdminLogin.rbFrench.isSelected()) {
			ResourceBundle messages = ResourceBundle.getBundle("com.prog2.labs.messages_fr");
			lblAddBook.setText(messages.getString("Add.Book"));
			lblBookId.setText(messages.getString("Book.SN"));
			lblBookName.setText(messages.getString("Book.Name"));
			lblAuthor.setText(messages.getString("Author"));
			lblPublisher.setText(messages.getString("Publisher"));
			lblPrice.setText(messages.getString("Price"));
			lblQuantity.setText(messages.getString("Quantity"));
			btnAddBk.setText(messages.getString("Add.Book"));
			btnCancel.setText(messages.getString("Cancel"));
		}
	}
}
