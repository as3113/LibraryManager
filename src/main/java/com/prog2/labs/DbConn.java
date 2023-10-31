package com.prog2.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// singleton class 
public class DbConn {
	// instance variables
	private static DbConn instance = null; // private attribute of the class type that refers to the single instance
	private Connection connection;
	
	// declare the url link, username and password of mysql 
	private static String server = "localhost"; // server name may be different on different computer
	private static final String USER = "root";  // username may be different on different computer
	private static final String PASS = ""; // no password for easier testing
	    
	// private constructor of the class: restrict instance creation outside the class 
    private DbConn(String dbName) {
    	
    	// link excluding the database name
    	String url1 = "jdbc:mysql://" + server + "/";  // or "jdbc:mysql://localhost:3306/"
    	
    	// link including the  database name
    	String url2 = "jdbc:mysql://" + server + "/" + dbName;  // or "jdbc:mysql://localhost:3306/" + dbName
    	
    	try {
	    	// connect to the mysql without specifying a database
	    	connection = DriverManager.getConnection(url1, USER, PASS);
	    	Statement stmt = connection.createStatement();
	    	
	    	// check if the database exists    	
	    	ResultSet rSet = connection.getMetaData().getCatalogs(); // get catalogs in the connected database
	    	boolean dbExists = false;
	    	
	    	while (rSet.next()) {
	    		String dbName2 = rSet.getString(1); 
	    		// true if there is a database with the same name
	    		if (dbName2.equalsIgnoreCase(dbName)) { // use equalsIgnoreCase, coz MySql converts all table names to lowercase on storage and lookup
	    			dbExists = true;
	    			break;
	    		}
	    	}
	    	rSet.close();
	    	
	    	if (!dbExists) {
	    		// create the database if it does not exist
	    		String query = "CREATE DATABASE " + dbName;
	    		String useDB = "USE " + dbName;
	    		String stuTable = "CREATE TABLE students ("
	    				+ "studentId INT PRIMARY KEY,"
	    				+ "name VARCHAR(50),"
	    				+ "contact VARCHAR(20))";
	    		String stuInsert = "INSERT INTO students (studentId, name, contact) values " 
	    				+ "(1, 'Adam Smith', '5141001000'), (2, 'Jolin Lynn', '5141002000'),(3, 'Bob Hunt', '5141003000'),"
	    				+ "(4, 'Jolyn Lynn', '5141004000'), (5, 'Simon Pegg', '5141005000'),(6, 'Tom Jerry', '5141006000')";
	    		String booksTable = "CREATE TABLE books ("
	    				+ "	sn VARCHAR(255) PRIMARY KEY,"
	    				+ " title VARCHAR(255) NOT NULL,"
	    				+ " author VARCHAR(255) NOT NULL,"
	    				+ " publisher VARCHAR(255) NOT NULL,"
	    				+ " price DECIMAL(6, 2) NOT NULL,"
	    				+ " quantity INT,"
	    				+ " issued INT DEFAULT 0,"
	    				+ " addedDate DATE DEFAULT(CURRENT_DATE)"
	    				+ ")";
	    		String booksInsert = "INSERT INTO books (sn, title, author, publisher, price, quantity, issued) values" 
	    				+ "('AS1', 'Aerospace', 'Aero', 'AeroH', 100.00, 5, 0), ('AG1', 'Agriculture', 'Agriculture', 'AGH', 50.00, 3, 0),"
	    				+ "('CS1', 'Java', 'Java', 'JavaH', 105.00, 10, 0)";
	    		String issueBkTable = "CREATE TABLE issuedBooks ("
	    				+ "	id INT PRIMARY KEY AUTO_INCREMENT,"
	    				+ " sn VARCHAR(255),"
	    				+ " stID INT,"
	    				+ " stName VARCHAR(50),"
	    				+ " stContact VARCHAR(20),"
	    				+ " issueDate DATE DEFAULT(CURRENT_DATE),"
	    				+ " FOREIGN KEY (sn) REFERENCES books(sn),"
	    				+ " FOREIGN KEY (stID) REFERENCES students(studentID)"
	    				+ ")";
	    		String issueInsert = "INSERT INTO issuedBooks (sn, stId, stName, stContact) values" 
	    				+ "('CS1', 1, 'Adam Smith', '5141001000'), ('AS1', 2, 'Jolin Lynn', '5141002000')";
	    		stmt.executeUpdate(query);
	    		stmt.executeUpdate(useDB);
	    		stmt.executeUpdate(stuTable);
	    		stmt.executeUpdate(stuInsert);
	    		stmt.executeUpdate(booksTable);
	    		stmt.executeUpdate(booksInsert);
	    		stmt.executeUpdate(issueBkTable);
	    		stmt.executeUpdate(issueInsert);
	    		System.out.println("Database created successfully...");
	    	} else {
	    		System.out.println("Database already exists...");
	    	}
	    	    	
	    	// connect to the database
	    	connection = DriverManager.getConnection(url2, USER, PASS);
	    	System.out.println("Connected to database " + dbName + "...");
	    	
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());;
    	}
    }
    
    // public static method: create and access the created instance
    public static DbConn getInstance(String dbName) {
    	// condition: only create instance when it does not exist
    	if (instance == null) {
    		instance = new DbConn(dbName);
    	}
    	return instance; // singleton instance
    }
    
    public Connection getConnection() {
    	return connection;
    }
}
