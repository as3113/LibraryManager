package com.prog2.labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.jdbc.result.ResultSetMetaData;

// model class
public class Book {
	// instance variables
	private String sn;
	private String title;
	private String author;
	private String publisher;
	private double price;
	private int qte;
	private int issuedQte;
//	private int availCopy;
	private static LocalDate dateOfPurchase;
	
	// default constructor
	public Book() {}	
	
	// generic constructor
	public Book(String sn, String title, String author, String publisher, double price, int qte, int issuedQte,
				LocalDate dateOfPurchase) {
//				int availCopy, LocalDate dateOfPurchase) {
		this.sn = sn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.qte = qte;
		this.issuedQte = issuedQte;
//		this.availCopy = availCopy;
		Book.dateOfPurchase= LocalDate.now(); // current date
	}

	// constructor for book in the catalog
//	public Book(String sn, String title, String author) {
//		this.sn = sn;
//		this.title = title;
//		this.author = author;
//	}
	
//	// controller: addBook method
//	public static void addBook(Book book) {
//		try {
//			DbConn dbConn = DbConn.getInstance("db_lib");
//			Connection connection = dbConn.getConnection();
//			
//			// query to check if the same book (same sn) exists in the database
//			String checkQuery = "SELECT quantity FROM books WHERE sn = ?";
//			
//			/* creata a prepared statement to execute the parameterized query
//			 * multiple times (reusable) with different parameter values (?) */
//			PreparedStatement checkSt = connection.prepareStatement(checkQuery);
//			
//			// set the value of the 1st parameter (1st ?) in prepared statement
//			checkSt.setString(1, book.getSn());
//			
//			/* execute the query in pepared statement and store ResultSet instance to rSet.
//			 * it contain the rows that match the query.
//			 */
//			ResultSet rSet = checkSt.executeQuery();
//			
//			// if book already exists, update book quantity
//			if (rSet.next()) { 
//				int existQ = rSet.getInt("quantity");
//				int newQ = existQ + book.getQte();
//				
//				String updateQuery = "UPDATE books SET quantity = ? WHERE sn = ?";
//				PreparedStatement updateSt = connection.prepareStatement(updateQuery);
//				updateSt.setInt(1, newQ);
//				updateSt.setString(2, book.getSn());
//				
//				updateSt.executeUpdate();
//				System.out.println("Book quantity updated: " + newQ);
//			} else { // book does not exist, insert the book to the table
//				String query = "INSERT INTO books (sn, title, author, publisher, price, quantity, issued, addedDate)"
//						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
//				PreparedStatement st = connection.prepareStatement(query);
//				st.setString(1, book.getSn());
//				st.setString(2, book.getTitle());
//				st.setString(3, book.getAuthor());
//				st.setString(4, book.getPublisher());
//				st.setDouble(5, book.getPrice());
//				st.setInt(6, book.getQte());
//				st.setInt(7, book.getIssuedQte()); // will be set as 0 in AddBook.java 
//				st.setString(8, dateOfPurchase.toString()); // current date
//		
//				st.executeUpdate();
//				System.out.println("Book added");	
//			}
//			
//			// update the catalog after adding a book
//			AdminMain.catalog = Book.viewCatalog();
//			
//			
//			// close the connection
////			if (connection != null && !connection.isClosed()) {
////				connection.close();
////				System.out.println("Connection is closed...");
////			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	// controller: issueBook method
//	public boolean issueBook(Book b, Student s) {
//		return false;
//		
//	}
	
	// controller: returnBook method

	
//	// viewCatalog method
//	public static Map<String, String> viewCatalog() {
//		// key: sn, value: book's details
//		Map<String, String> catalog = new HashMap<>();
//
//		try {
//			// retrieve data from database and store into HashMap catalog
//			DbConn dbConn = DbConn.getInstance("db_lib");
//			Connection connection = dbConn.getConnection();
//			
//			// query to select the fields from table in database
//			String query = "SELECT sn, title, author, quantity, issued FROM books";
//			
//			// reusable prepared statement to execute the query more than once
//			PreparedStatement st = connection.prepareStatement(query);
//			ResultSet rSet = st.executeQuery();
////			ResultSetMetaData rsmd = (ResultSetMetaData) rSet.getMetaData(); // contain the data retrieve from database
////			AdminMain.modelCatalog = (DefaultTableModel) AdminMain.tableCatalog.getModel();
//			
//			Object[] colName = { "SN", "Title", "Author", " Quantity", "Available Copies", "Available" };
//			AdminMain.modelCatalog.setColumnIdentifiers(colName);
//
//			while(rSet.next()) {
//				String sn = rSet.getString(1);
//				String title = rSet.getString(2);
//				String author = rSet.getString(3);
//				int quantity = rSet.getInt(4);
//				int issued = rSet.getInt(5); 
//				int availCopy = quantity - issued;
//				String available = (availCopy > 0)? "Yes": "No";
//				
//				String bookDetail = sn + title + author + quantity + availCopy + available;
//				catalog.put(sn, bookDetail);
//				
//				String[] row = {sn, title, author, Integer.toString(quantity), Integer.toString(availCopy), available};
//				AdminMain.modelCatalog.addRow(row);
//				
//				// testing in console
//				for (String value: catalog.values()) {
////				for (String value: catalog.keySet()) {	
//					System.out.println(value);
//				}
//			}			
//		} catch (Exception e1) {
//				// TODO: handle exception
//		}
//	return catalog;
//	}
//	
	// for testing
//	public static void main(String[] args) {
//		viewCatalog();
//	}
	
	// viewIssuedBook method
	public static Map<String, String> viewIssuedBooks() {
		return null;
	}

	// getters and setters
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}
	
//	public int getAvailCopy() {
//		return availCopy;
//	}
//
//	public void setAvailCopy(int availCopy) {
//		this.availCopy = availCopy;
//	}

	public int getIssuedQte() {
		return issuedQte;
	}

	public void setIssuedQte(int issuedQte) {
		this.issuedQte = issuedQte;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}	
	
	
}
