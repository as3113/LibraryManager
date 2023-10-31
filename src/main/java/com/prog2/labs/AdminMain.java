package com.prog2.labs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class AdminMain extends JFrame {

	private JPanel contentPane;
	private JTextField tfCatalogBookName;
	private JTextField tfCatalogAuthor;
	static JTable tableCatalog;
	static DefaultTableModel modelCatalog;
	private boolean isCatalogDisplayed = false;
	public Map<String, String> catalog;
	public Map<String, String> issuedBooks;
	public Map<Integer, String> studentMap;
	
	private JTable tableIssued;
	static DefaultTableModel modelIssued;
	static DefaultTableModel modelStudent;
	private static JTable tableStudent;
	private JTextField tfIssuedBookSN;
	private JTextField tfIssuedStuId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String getFormattedDate() {
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
        return formattedDate;
    }
	
	private String getFormattedDay() {
        LocalDate currentDate = LocalDate.now();
        String formattedDay = currentDate.format(DateTimeFormatter.ofPattern("EEEE"));
        return formattedDay;
    }
	
	/**
	 * Create the frame.
	 */
	public AdminMain() {
		LibrarianController controller = new LibrarianController();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if (!isCatalogDisplayed) {
					controller.viewCatalog(); // call viewCatalog controller 
					LibrarianController.viewIssuedBooks();
					LibrarianController.viewStudent();
					isCatalogDisplayed = true;
				}
//				ResourceBundle messages1 = ResourceBundle.getBundle("com.prog2.labs.messages");
//				frame.setTitle(messages1.getString("LibraryPortal"));
			}
		});
		setTitle("LibriScope");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1059, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDate = new JLabel(getFormattedDate());
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblDate, BorderLayout.NORTH);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(10, 21, 161, 37);
		contentPane.add(lblDate);
		
		JLabel lblDay = new JLabel(getFormattedDay());
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDay.setBounds(0, 44, 180, 37);
		contentPane.add(lblDay);
		
		JLabel lblBook = new JLabel("Books");
		lblBook.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBook.setBounds(60, 233, 81, 25);
		contentPane.add(lblBook);
		
	
		
		JLabel lblStu = new JLabel("Students");
		lblStu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStu.setBounds(50, 348, 104, 25);
		contentPane.add(lblStu);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(179, 11, 854, 439);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Books Catalog", null, panel, null);
		panel.setLayout(null);
		
		
		
		tfCatalogBookName = new JTextField();
		tfCatalogBookName.setBounds(99, 18, 373, 20);
		panel.add(tfCatalogBookName);
		tfCatalogBookName.setColumns(10);
		
		
		// disable tfCatalogAuthor when typing in tfCatalogBookName
		tfCatalogBookName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				tfCatalogAuthor.setEnabled(false);  // disable tfCatalogAuthor
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				tfCatalogAuthor.setEnabled(false); // disable tfCatalogAuthor
				if (tfCatalogBookName.getText().isEmpty()) {
					tfCatalogAuthor.setEnabled(true); // enable tfCatalogAuthor if tfCatalogBookName is empty
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});  
		
		tfCatalogAuthor = new JTextField();
		tfCatalogAuthor.setColumns(10);
		tfCatalogAuthor.setBounds(99, 51, 373, 20);
		panel.add(tfCatalogAuthor);
		
		// disable tfCatalogBookName when typing in tfCatalogAuthor
		tfCatalogAuthor.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				tfCatalogBookName.setEnabled(false);  // disable tfCatalogBookName
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				tfCatalogBookName.setEnabled(false); // disable tfCatalogBookName
				if (tfCatalogAuthor.getText().isEmpty()) {
					tfCatalogBookName.setEnabled(true); // enable tfCatalogBookName if tfCatalogAuthor is empty
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		}); 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 829, 311);
		panel.add(scrollPane);
		
		tableCatalog = new JTable();
		modelCatalog = new DefaultTableModel();
//		Object[] columnCatalog = { "Book ID", "Book Name", "Author", "Available" };
//		modelCatalog.setColumnIdentifiers(columnCatalog);
		
		tableCatalog.setModel(modelCatalog);
		scrollPane.setViewportView(tableCatalog);
		
		JButton btnCatalogSearch = new JButton("Search Book");
		btnCatalogSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				String title = tfCatalogBookName.getText().trim();
				String author = tfCatalogAuthor.getText().trim();
				try {
					// retreive data from database and store into HashMap catalog
					DbConn dbConn = DbConn.getInstance("db_lib");
					Connection connection = dbConn.getConnection();
					
					if (!title.isEmpty()) {
					// query to select the fields from table in database
					String query = "SELECT * FROM books WHERE title = ?";
					PreparedStatement st = connection.prepareStatement(query);
					st.setString(1, title);
					ResultSet rSet = st.executeQuery();
					if(!rSet.next()) {
						modelCatalog.setRowCount(0);
						return;
					}
					while (rSet.next()) {
						String sn = rSet.getString("sn");
						String bookTitle = rSet.getString("title");
						String bookAuthor = rSet.getString("author");
						String publisher = rSet.getString("publisher");
						double price = rSet.getDouble("price");
						int quantity = rSet.getInt("quantity");
						int issued = rSet.getInt("issued");
						Date addedDate = rSet.getDate("addedDate");
						
						String available = (quantity > 0)? "Y": "N";
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String bookDetail = sn + "," + bookTitle + "," + bookAuthor + "," + publisher + "," 
											+ String.format("%.2f",price) + "," + String.valueOf(quantity) + ","
											+ String.valueOf(issued) + "," + dateFormat.format(addedDate) + ","
											+ available;	
						
						// print on jtable from the database
						Object[] colName = { "SN", "Title", "Author", "Publisher", "Price ($)", "Quantity", "Issued Copies", 
											"Added Date", "Available"};
						AdminMain.modelCatalog.setColumnIdentifiers(colName);
						modelCatalog.setRowCount(0); // clear pre-existing rows
						String[] row = {sn, bookTitle, bookAuthor, publisher, String.format("%.2f",price), String.valueOf(quantity), 
								String.valueOf(issued) , dateFormat.format(addedDate), available};
						AdminMain.modelCatalog.addRow(row);
						}
					
					}
					if (!author.isEmpty()) {
						String query = "SELECT * FROM books WHERE author = ?";
						PreparedStatement st = connection.prepareStatement(query);
						st.setString(1, author);
						ResultSet rSet = st.executeQuery();
						if(!rSet.next()) {
							modelCatalog.setRowCount(0);
							return;
						}
						while (rSet.next()) {
							String sn = rSet.getString("sn");
							String bookTitle = rSet.getString("title");
							String bookAuthor = rSet.getString("author");
							String publisher = rSet.getString("publisher");
							double price = rSet.getDouble("price");
							int quantity = rSet.getInt("quantity");
							int issued = rSet.getInt("issued");
							Date addedDate = rSet.getDate("addedDate");
							
							String available = (quantity > 0)? "Y": "N";
							
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String bookDetail = sn + "," + bookTitle + "," + bookAuthor  + "," + publisher + "," 
												+ String.format("%.2f",price) + "," + String.valueOf(quantity) + ","
												+ String.valueOf(issued) + "," + dateFormat.format(addedDate) + ","
												+ available;	
							
							// print on jtable from the database
							Object[] colName = { "SN", "Title", "Author", "Publisher", "Price ($)", "Quantity", "Issued Copies", 
												"Added Date", "Available"};
							AdminMain.modelCatalog.setColumnIdentifiers(colName);
							modelCatalog.setRowCount(0); // clear pre-existing rows
							String[] row = {sn, bookTitle, bookAuthor , publisher, String.format("%.2f",price), String.valueOf(quantity), 
									String.valueOf(issued) , dateFormat.format(addedDate), available};
							AdminMain.modelCatalog.addRow(row);
					}

					}			
				} catch (Exception e1) {
				}
	
			}
		});
		btnCatalogSearch.setBounds(482, 18, 153, 53);
		panel.add(btnCatalogSearch);
		
		JButton btnRefreshCatalog = new JButton("Refresh Catalog");
		btnRefreshCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isCatalogDisplayed) {
					// empty the pre-existing JTable
					AdminMain.modelCatalog.setRowCount(0);
					// get the catalog from DB
					LibrarianController controller3 = new LibrarianController();
					catalog = controller3.viewCatalog();
					
					return;
				}
			}
		});
		btnRefreshCatalog.setBounds(649, 17, 176, 54);
		panel.add(btnRefreshCatalog);
		
		JLabel lblCatalogBookName = new JLabel("Book Name");
		lblCatalogBookName.setBounds(17, 21, 86, 14);
		panel.add(lblCatalogBookName);
		
		JLabel lblCatalogAuthor = new JLabel("Author");
		lblCatalogAuthor.setBounds(17, 54, 110, 14);
		panel.add(lblCatalogAuthor);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Issued Books Details", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 91, 829, 309);
		panel_1.add(scrollPane_1);
		
		tableIssued = new JTable();
		modelIssued = new DefaultTableModel();
//		Object[] columnIssued = { "Student ID", "Student Name", "Book ID", "Book Name", "Issue Date" };
//		modelIssued.setColumnIdentifiers(columnIssued);
		tableIssued.setModel(modelIssued);
		scrollPane_1.setViewportView(tableIssued);
		
		JButton btnRefreshIssuedList = new JButton("Refresh Book List");
		btnRefreshIssuedList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isCatalogDisplayed) {
					// empty the pre-existing JTable
					AdminMain.modelIssued.setRowCount(0);
					// get the catalog from DB
//					LibrarianController controller3 = new LibrarianController();
					issuedBooks = LibrarianController.viewIssuedBooks();
					
					return;// clear the current rows in the table model
				}
			}
		});
		btnRefreshIssuedList.setBounds(638, 15, 201, 23);
		panel_1.add(btnRefreshIssuedList);
		
		JButton btnIssuedReturnBook = new JButton("Return Book");
		btnIssuedReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// index of selected row
				int selectedRow = tableIssued.getSelectedRow();
				
				if (selectedRow != -1) {
					DefaultTableModel model = (DefaultTableModel) tableIssued.getModel();
					
					/* get values of columns before remove the row, otherwise get error on row index,
					 * because when a row is deleted from the model, the remaining rows index are shifted up by one index,
					 * so the selected row index no longer points to the same row after remove it.
					*/
					String bookSN = model.getValueAt(selectedRow, 1).toString();
					String stuID = model.getValueAt(selectedRow, 2).toString();
//					System.out.println(bookSN);
//					System.out.println(stuID);
					
					// delete selected row after get values of columns
					model.removeRow(selectedRow);
					model.fireTableDataChanged();
					
					// pass arguments to returnBook method
					controller.returnBook(bookSN, stuID, "");
				}
				
			}
		});

		btnIssuedReturnBook.setBounds(638, 46, 201, 23);
		panel_1.add(btnIssuedReturnBook);
		
		tfIssuedBookSN = new JTextField();
		tfIssuedBookSN.setColumns(10);
		tfIssuedBookSN.setBounds(103, 16, 302, 20);
		panel_1.add(tfIssuedBookSN);
		
		tfIssuedStuId = new JTextField();
		tfIssuedStuId.setColumns(10);
		tfIssuedStuId.setBounds(103, 49, 302, 20);
		panel_1.add(tfIssuedStuId);
		
		JLabel lblBookSn = new JLabel("Book SN");
		lblBookSn.setBounds(21, 19, 86, 14);
		panel_1.add(lblBookSn);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(21, 52, 110, 14);
		panel_1.add(lblStudentId);
		
		JButton btnSearch = new JButton("Search Issued Books");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookSN = tfIssuedBookSN.getText().trim();
				String studID = tfIssuedStuId.getText().trim();
				try {
					// retreive data from database and store into HashMap catalog
					DbConn dbConn = DbConn.getInstance("db_lib");
					Connection connection = dbConn.getConnection();
					if (!bookSN.isEmpty() || !studID.isEmpty()) {
						String query = "SELECT * FROM issuedBooks WHERE sn = ? AND stID = ?";
						PreparedStatement st = connection.prepareStatement(query);
						if(bookSN.isEmpty()) {
							st.setString(1, "");
						}
						else {
							st.setString(1, bookSN);
						}
						if(studID.isEmpty()) {
							st.setInt(2, Integer.parseInt("-1"));
						} else {
							st.setInt(2, Integer.parseInt(studID));
						}
						
						ResultSet rSet = st.executeQuery();	
						
//						if(!rSet.next()) {
//							modelIssued.setRowCount(0); // clear pre-existing rows
//						}
						
						AdminMain.modelIssued.setRowCount(0); // clear pre-existing rows
						while (rSet.next()) {
								int id = rSet.getInt("id");
								String sn = rSet.getString("sn");
								int stuID = rSet.getInt("stID");
								String studName = rSet.getString("stName");
								String studContact = rSet.getString("stContact");
								Date issuedDate = rSet.getDate("issueDate");
															
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//							String bookDetail = id + + "," + bookTitle + "," + bookAuthor  + "," + publisher + "," 
	//												+ String.format("%.2f",price) + "," + String.valueOf(quantity) + ","
	//												+ String.valueOf(issued) + "," + dateFormat.format(addedDate) + ","
	//												+ available;	
								if (AdminLogin.rbFrench.isSelected()) {
									Object[] colNameIssued = { "ID du Émis", "NS du Livre", "ID d'Étudiant", "Nom d'étudiant", "Coordonnées d'étudiant", "Date d'émission"};
									AdminMain.modelIssued.setColumnIdentifiers(colNameIssued);
								} else {
								// print on jtable from the database
									Object[] colName = { "Issued ID", "Book SN", "Student ID", "Student Name", 
													 "Student Contact", "Issue Date"};
									AdminMain.modelIssued.setColumnIdentifiers(colName);
								}

								String[] row = {String.valueOf(id), sn, String.valueOf(stuID), studName, 
												studContact, dateFormat.format(issuedDate)};
								AdminMain.modelIssued.addRow(row);
						} 
//								else {
//							modelIssued.setRowCount(0); // clear pre-existing rows
//						}
					// reusable prepared statement to execute the query more than once

					}			
				} catch (Exception e1) {
				}
			}
		});
		btnSearch.setBounds(415, 15, 213, 54);
		panel_1.add(btnSearch);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Students List", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 829, 342);
		panel_2.add(scrollPane_2);
		
		tableStudent = new JTable();
		modelStudent = new DefaultTableModel();
		Object[] columnStudent = { "Student ID", "Student Name", "Book ID", "Book Name", "Issue Date" };
		modelStudent.setColumnIdentifiers(columnStudent);
		tableStudent.setModel(modelStudent);
		scrollPane_2.setViewportView(tableStudent);
		
		JButton btnRefreshStudentList = new JButton("Refresh Students List");
		btnRefreshStudentList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isCatalogDisplayed) {
					// empty the pre-existing JTable
					AdminMain.modelStudent.setRowCount(0);
					// get the catalog from DB
					studentMap = LibrarianController.viewStudent();
					
					return;// clear the current rows in the table model
				}
			}
		});
		btnRefreshStudentList.setBounds(561, 364, 278, 36);
		panel_2.add(btnRefreshStudentList);
		
		// open AddBook window when Add Book is clicked
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook addBook = new AddBook();
				addBook.setVisible(true);
			}
		});
		btnAddBook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddBook.setBounds(24, 269, 134, 23);
		contentPane.add(btnAddBook);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IssueBook issueBook = new IssueBook();
				issueBook.setVisible(true);
			}
		});
		btnIssueBook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIssueBook.setBounds(24, 303, 134, 23);
		contentPane.add(btnIssueBook);
		
		JButton btnAddStu = new JButton("Add Student");
		btnAddStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent addStudent = new AddStudent();
				addStudent.setVisible(true);
			}
		});
		btnAddStu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddStu.setBounds(24, 386, 135, 23);
		contentPane.add(btnAddStu);
		
		// book logo
		ImageIcon icon = new ImageIcon("book_logo4.jpg");
		Image image = icon.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT); 
		ImageIcon scaledIcon = new ImageIcon(image);
		JLabel lblBookLogo = new JLabel(scaledIcon);
		getContentPane().add(lblBook, BorderLayout.CENTER);
		lblBookLogo.setBounds(24, 92, 135, 125);
		contentPane.add(lblBookLogo);
		

		
		// Add a ChangeListener to the JTabbedPane: refresh the jtables when click on the tab
		tabbedPane.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        // Get the index of the currently selected tab
		        int index = tabbedPane.getSelectedIndex();
		        
		        // Refresh the contents of the JTable in the selected tab
		        if (index == 0) {
					if (isCatalogDisplayed) {
						// empty the pre-existing JTable
						AdminMain.modelCatalog.setRowCount(0);
						// get the catalog from DB
						LibrarianController controller3 = new LibrarianController();
						catalog = controller3.viewCatalog();
						
						return;
					}
		        } else if (index == 1) {
		        	if (isCatalogDisplayed) {
						// empty the pre-existing JTable
						AdminMain.modelIssued.setRowCount(0);
						// get the catalog from DB
//						LibrarianController controller3 = new LibrarianController();
						issuedBooks = LibrarianController.viewIssuedBooks();
						
						return;// clear the current rows in the table model
					}
		        } else {
		        	if (isCatalogDisplayed) {
						// empty the pre-existing JTable
						AdminMain.modelStudent.setRowCount(0);
						// get the catalog from DB
						studentMap = LibrarianController.viewStudent();
						
						return;// clear the current rows in the table model
					}
		        }
		    }
		});
		
//		if (Locale.FRANCE != null) {
		if (AdminLogin.rbFrench.isSelected()) {
			ResourceBundle messages = ResourceBundle.getBundle("com.prog2.labs.messages_fr");
			lblBook.setText(messages.getString("Books"));
			lblStu.setText(messages.getString("Student"));
			btnAddBook.setText(messages.getString("Add.Book"));
			btnAddStu.setText(messages.getString("Add.Student"));
			btnIssueBook.setText(messages.getString("Issue.Book"));
			tabbedPane.setTitleAt(0, messages.getString("Books.Catalog"));
			tabbedPane.setTitleAt(1, messages.getString("Issued.Books.Details"));
			tabbedPane.setTitleAt(2, messages.getString("Student.List"));
			lblCatalogBookName.setText(messages.getString("Book.Name"));
			lblCatalogAuthor.setText(messages.getString("Author"));
			btnCatalogSearch.setText(messages.getString("Search.Book"));
			btnRefreshCatalog.setText(messages.getString("Refresh.Catalog"));
//			tableCatalog.getColumnModel().getColumn(0).setCellRenderer((new DefaultTableCellRenderer() {
//			    public Component getTableCellRendererComponent(JTable table, Object value,
//			            boolean isSelected, boolean hasFocus, int row, int column) {
//			        // set the text of the cell to uppercase
//			        value = ((String) value).toUpperCase();
//			        return super.getTableCellRendererComponent(tableCatalog, value, isSelected, hasFocus, row, column);
//			    }
//			})
//			);
			lblBookSn.setText(messages.getString("Book.SN"));
			lblStudentId.setText(messages.getString("Student.ID"));
			btnRefreshIssuedList.setText(messages.getString("Refresh.Book.List"));
			btnIssuedReturnBook.setText(messages.getString("Return.Book"));
			btnSearch.setText(messages.getString("Search.Issued.Books"));
			btnRefreshStudentList.setText(messages.getString("Refresh.Students.List"));
			
			Object[] colNameCatalog = { "NS", "Titre", "Auteur", "Éditeur", "Prix ($)", "Quantité", "Copies Émises", 
					"Date d'Ajout", "Disponible"};
			AdminMain.modelCatalog.setColumnIdentifiers(colNameCatalog);
			Object[] colNameIssued = { "ID du Émis", "NS du Livre", "ID d'Étudiant", "Nom d'étudiant", "Coordonnées d'étudiant", "Date d'émission"};
			AdminMain.modelIssued.setColumnIdentifiers(colNameIssued);
			Object[] colNameStu = { "ID", "Nom", "Coordonnées"};
			AdminMain.modelStudent.setColumnIdentifiers(colNameStu);
		} else {
			Object[] colNameCatalog = { "SN", "Title", "Author", "Publisher", "Price ($)", "Quantity", "Issued Copies", 
					"Added Date", "Available"};
			AdminMain.modelCatalog.setColumnIdentifiers(colNameCatalog);
			Object[] colNameIssued = { "Issue ID", "Book SN", "Student ID", "Student Name", "Student Contact", "Issue Date"};
			AdminMain.modelIssued.setColumnIdentifiers(colNameIssued);
			Object[] colNameStu = { "ID", "Name", "Contact"};
			AdminMain.modelStudent.setColumnIdentifiers(colNameStu);
		}
	}
}
