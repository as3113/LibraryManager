package com.prog2.labs;
// Student class

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student implements Person{
	// instance variables
	private int stId;
	private String name;
	private String contact;
	
	
	public Student() {}

	public Student(int stId, String name, String contact) {
		super();
		this.stId = stId;
		this.name = name;
		this.contact = contact;
	}
	
	
	// searchBookByTitle method
	public List<Book> searchBookByTitle (String title){
	    Map<String, String> bookStringMap = getCatalog();
	    List<Book> matchingBooks = bookStringMap.values()
	            .stream()
	            .map(value -> {
	                String[] values = value.split(",");
	                // need to improve the Book Constructor in viewCatalog()
	                return new Book(values[0], values[1], values[2], values[3], Double.parseDouble(values[4]), 
            				Integer.parseInt(values[5]), Integer.parseInt(values[6]), 
            				LocalDate.parse(values[7]));
	            })
	            .filter(book -> book.getTitle().equals(title))
	            .collect(Collectors.toList());
	    return matchingBooks;
	}
	
	// searchBookByName method
	public List<Book> searchBookByName (String name){
		Map<String, String> bookStringMap = getCatalog();
	    List<Book> matchingBooks = bookStringMap.values()
	            .stream()
	            .map(value -> {
	                String[] values = value.split(",");
	                return new Book(values[0], values[1], values[2], values[3], Double.parseDouble(values[4]), 
            				Integer.parseInt(values[5]), Integer.parseInt(values[6]), 
            				LocalDate.parse(values[7]));
//	                		Integer.parseInt(values[7]), LocalDate.parse(values[8]));
	            })
	            .filter(book -> book.getAuthor().equals(name))
	            .collect(Collectors.toList());
	    return matchingBooks;
	}
	
	// serahcBookByPublisher method
	public List<Book> searchBookByPublisher (String publisher){
		Map<String, String> bookStringMap = getCatalog();
	    List<Book> matchingBooks = bookStringMap.values()
	            .stream()
	            .map(value -> {
	                String[] values = value.split(",");
	                return new Book(values[0], values[1], values[2], values[3], Double.parseDouble(values[4]), 
            				Integer.parseInt(values[5]), Integer.parseInt(values[6]), 
            				LocalDate.parse(values[7]));
//	                		Integer.parseInt(values[7]), LocalDate.parse(values[8]));
	            })
	            .filter(book -> book.getPublisher().equals(publisher))
	            .collect(Collectors.toList());
	    return matchingBooks;
	}
	
	
	// viewCatalog method
	public  Map<String, String> viewCatalog () {
		// key: sn, value: book's details
		Map<String, String> catalog = new HashMap<>();
		
		try {
			// retreive data from database and store into HashMap catalog
			DbConn dbConn = DbConn.getInstance("db_lib");
			Connection connection = dbConn.getConnection();
			// query to select the fields from table in database
			String query = "SELECT * FROM books";
			// reusable prepared statement to execute the query more than once
			PreparedStatement st = connection.prepareStatement(query);
			ResultSet rSet = st.executeQuery();
					
			while (rSet.next()) {
				String sn = rSet.getString("sn");
				String title = rSet.getString("title");
				String author = rSet.getString("author");
				String publisher = rSet.getString("publisher");
				int quantity = rSet.getInt("quantity");
				int issued = rSet.getInt("issued");
//				int availCopy = quantity - issued;
				String available = (quantity > 0)? "Yes": "No";
				
				String bookDetail = sn + "," + title + "," + author + "," + publisher + "," + available;
				catalog.put(sn, bookDetail);		
				
				String[] row = {sn, title, author, publisher, available};
				StuMain.modelSearchBook.addRow(row);

			}
			// close the connection after operation
//					if (connection != null && !connection.isClosed()) {
//						connection.close();
//					System.out.println("Connection is closed...");
//					}
			
		} catch (Exception e) {
		}
		
		// print catalog on console
//		for (String value: catalog.values()) {
//		System.out.println(value);
//	}
		
		return catalog;
		
	}

	public Map<String, String> viewMyBooks () {
		Map<String, String> myBooks = new HashMap<>();
		
		try {
			// retreive data from database and store into HashMap catalog
			DbConn dbConn = DbConn.getInstance("db_lib");
			Connection connection = dbConn.getConnection();
			// query to select the fields from table in database
			String query = 
					"SELECT books.sn, books.title, books.author, issuedBooks.id, issuedBooks.issueDate "
					+ "FROM issuedBooks "
					+ "INNER JOIN books ON issuedBooks.sn = books.sn "
					+ "WHERE issuedBooks.stID = ?";
			// reusable prepared statement to execute the query more than once
			PreparedStatement st = connection.prepareStatement(query);
			
			// set the value of the 1st parameter (1st ?) in prepared statement
			st.setInt(1, Integer.parseInt(AdminLogin.username)); // test with stuID 1, Adam Smith
			
			ResultSet rSet = st.executeQuery();
					
			while (rSet.next()) {
				int id = rSet.getInt("id");
				String sn = rSet.getString("sn");
				String title = rSet.getString("title");
				String author = rSet.getString("author");
				Date issueDate = rSet.getDate("issueDate");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// set the expired date
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(issueDate);
				calendar.add(Calendar.MONTH, 1);
				Date expiredDate = calendar.getTime();
				
				// value of myBooks map
				String bookDetail = String.valueOf(id) + "," + sn + "," + title + "," + author + "," + dateFormat.format(issueDate);
				myBooks.put(sn, bookDetail);		
				
				String[] row = {String.valueOf(id), sn, title, author, dateFormat.format(issueDate), dateFormat.format(expiredDate)};
				StuMain.modelMyBook.addRow(row);
				
			}
		
			
		} catch (Exception e) {
		}
		// print myBooks in console
//		for (String value: myBooks.values()) {
//		System.out.println(value);
//	}
		
		return myBooks;
	}
	
	public Map<String, String> getCatalog() {
		// key: sn, value: book's details
		Map<String, String> catalog = new HashMap<>();
		
		try {
			// retreive data from database and store into HashMap catalog
			DbConn dbConn = DbConn.getInstance("db_lib");
			Connection connection = dbConn.getConnection();
			// query to select the fields from table in database
			String query = "SELECT * FROM books";
			// reusable prepared statement to execute the query more than once
			PreparedStatement st = connection.prepareStatement(query);
			ResultSet rSet = st.executeQuery();
					
			while (rSet.next()) {
				String sn = rSet.getString("sn").toUpperCase();
				String title = rSet.getString("title").toLowerCase();
				String author = rSet.getString("author").toLowerCase();
				String publisher = rSet.getString("publisher").toLowerCase();
				double price = rSet.getDouble("price");
				int quantity = rSet.getInt("quantity");
				int issued = rSet.getInt("issued");
//				int testIssued = 
				Date addedDate = rSet.getDate("addedDate");
//				int availCopy = quantity - issued;
				String available = (quantity > 0)? "Y": "N";
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String bookDetail = sn + "," + title + "," + author + "," + publisher + "," 
									+ String.format("%.2f", price) + "," + String.valueOf(quantity) + ","
									+ String.valueOf(issued) + "," + dateFormat.format(addedDate) + ","
									+ available;
				catalog.put(sn, bookDetail);		
			}			
		} catch (Exception e) {
		}
		
		// print catalog in console
//		for (String value: catalog.values()) {
//		System.out.println(value);
//	}
//	System.out.println();
		
		return catalog;
	}
	
	// borrow method
	public boolean issueBook (Book book, Student student) {
		
		try {
			DbConn dbConn = DbConn.getInstance("db_lib");
			Connection connection = dbConn.getConnection();
			
			// query to check if the same book (same sn) exists in the database
			String selectQuery = "SELECT quantity, issued FROM books WHERE sn = ?";
			
			/* creata a prepared statement to execute the parameterized query
			 * multiple times (reusable) with different parameter values (?) */
			PreparedStatement checkSt = connection.prepareStatement(selectQuery);
			
			// set the value of the 1st parameter (1st ?) in prepared statement
			checkSt.setString(1, book.getSn());
			
			/* execute the query in pepared statement and store ResultSet instance to rSet.
			 * it contain the rows that match the query.
			 */
			ResultSet rSet = checkSt.executeQuery();
			
			// if book already exists, update book quantity
			while (rSet.next()) { 
//				String sn = rSet.getString("sn");
				int quantity = rSet.getInt("quantity");
				int issued = rSet.getInt("issued");
				
				// update books table
				String updateQuery = "UPDATE books SET quantity = ?, issued = ? WHERE sn = ?";
				PreparedStatement updateSt = connection.prepareStatement(updateQuery);
				updateSt.setInt(1, quantity - 1);
				updateSt.setInt(2, issued + 1);
				updateSt.setString(3, book.getSn());
				
				updateSt.executeUpdate();
			} 		
			
			System.out.println("Book is borrowed successfully.");
			
			// add issued book to issuedBooks table
			String insertQuery = "INSERT INTO issuedBooks (sn, stID, stName, stContact)"
					+ "VALUES (?, ?, ?, ?)"; 
			PreparedStatement st = connection.prepareStatement(insertQuery);
			st.setString(1, book.getSn());
			st.setInt(2, student.getStId());
			st.setString(3, student.getName());
			st.setString(4, student.getContact());
			
			st.executeUpdate();
			System.out.println("Book is added to issuedBooks table.");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
	public Map<Integer, String> getStudentMap () {
		// key: sn, value: book's details
		Map<Integer, String> stuMap = new HashMap<>();
		
		try {
			// retrieve data from database and store into HashMap catalog
			DbConn dbConn = DbConn.getInstance("db_lib");
			Connection connection = dbConn.getConnection();
			
			// query to select the fields from table in database
			String query = "SELECT studentId, name, contact FROM students";
//			String query = "SELECT * FROM students";
			
			// reusable prepared statement to execute the query more than once
			PreparedStatement st = connection.prepareStatement(query);
			ResultSet rSet = st.executeQuery();
//					ResultSetMetaData rsmd = (ResultSetMetaData) rSet.getMetaData(); // contain the data retrieve from database
//					AdminMain.modelCatalog = (DefaultTableModel) AdminMain.tableCatalog.getModel();
			
//			Object[] colName = { "ID", "Name", "Contact" };
//			AdminMain.modelStudent.setColumnIdentifiers(colName);

			while(rSet.next()) {
				int stuId = rSet.getInt("studentId");
				String name = rSet.getString("name");
				String contact = rSet.getString("contact");
				
				String stuDetail = stuId + "," + name + "," + contact;
				stuMap.put(stuId, stuDetail);
				
				

			}		
			System.out.println(stuMap);
		} catch (Exception e1) {
				// TODO: handle exception
		}
		
		// testing in console
//		for (String value: stuMap.values()) {
//////				for (String value: catalog.keySet()) {	
//			System.out.println(value);
//		}
		
	return stuMap;
	}
	
	// toReturn method
	public boolean returnBook (String bookSN, String studID, String issueID) {
		try {
			// retreive data from database and store into HashMap catalog
			DbConn dbConn = DbConn.getInstance("db_lib");
			Connection connection = dbConn.getConnection();
			// query to select the fields from table in database
			String query = "DELETE FROM issuedBooks WHERE id = ? AND stID = ?";
			
			// reusable prepared statement to execute the query more than once
			PreparedStatement st = connection.prepareStatement(query);
			
			st.setInt(1, Integer.parseInt(issueID));
			st.setInt(2, Integer.parseInt(studID));
			st.executeUpdate();
			
			 String query2 = "select * from books where sn = ?";
			 PreparedStatement st2 = connection.prepareStatement(query2);
			 st2.setString(1, bookSN);
			 ResultSet rSet = st2.executeQuery();
			 
			while (rSet.next()) { 
				// String bookSerNum = rSet.getString("sn");
				int currQ = rSet.getInt("quantity");
				System.out.println(currQ);
				int issuedQ = rSet.getInt("issued");
				System.out.println(issuedQ);

				String updateQuery = "UPDATE books SET quantity = ?, issued = ? WHERE sn = ?";
				PreparedStatement updateSt = connection.prepareStatement(updateQuery);
				updateSt.setInt(1, currQ + 1);
				updateSt.setInt(2, issuedQ - 1);
				updateSt.setString(3, bookSN);
				updateSt.executeUpdate();
			}	
		} catch (Exception e) {
		}
		// print catalog on console
//		for (String value: catalog.values()) {
//		System.out.println(value);
//	}
//	System.out.println();
				
		System.out.println(bookSN);
		System.out.println(studID);
		
		return false;
		
	}

public String welcomeStudent() {
		
		try {
			DbConn dbConn = DbConn.getInstance("db_lib");
			Connection connection = dbConn.getConnection();
			
			// query to check if the same book (same sn) exists in the database
			String checkQuery = "SELECT name FROM students WHERE studentId = ?";
			
			/* creata a prepared statement to execute the parameterized query
			 * multiple times (reusable) with different parameter values (?) */
			PreparedStatement checkSt = connection.prepareStatement(checkQuery);
			
			// set the value of the 1st parameter (1st ?) in prepared statement
			checkSt.setInt(1,Integer.parseInt(AdminLogin.username));
			
			/* execute the query in pepared statement and store ResultSet instance to rSet.
			 * it contain the rows that match the query.
			 */
			ResultSet rSet = checkSt.executeQuery();
			
			name = "";
			// if book already exists, update book quantity
			while (rSet.next()) { 
				name = rSet.getString("name");
				
				System.out.println("Student name: " + name);
				if (AdminLogin.rbFrench.isSelected()) {
					StuMain.lblWelcome.setText("Bienvenue, " + name + "!");
				} else {
					StuMain.lblWelcome.setText("Welcome, " + name + "!");
				}
			
			}
			
			// close the connection
//			if (connection != null && !connection.isClosed()) {
//				connection.close();
//				System.out.println("Connection is closed...");
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	
	public int getStId() {
		return stId;
	}

	public void setStId(int stId) {
		this.stId = stId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
