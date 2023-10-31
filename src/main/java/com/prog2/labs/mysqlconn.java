package com.prog2.labs;

//Java Database Connectivity (JDBC)

/*
*  1. import packages --> java.sql
*  2. load and register the driver --> com.mysql.cj.jdbc.Driver
*  3. create connection --> Connection interface
*  4. create a statement --> Statement interface
*  5. execute the query
*  6. process the results
*  7. close
*/
//import java.sql.*; // also can import all 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// create a connection between the project and database
public class mysqlconn {
	// declare the url link, username and password of mysql 
	private static String server = "localhost";
    private static final String USER = "root";
    private static final String PASS = ""; // no password
//    private static String dbName = "db_lib"; 
    
    // testing 1
    public static Connection dbConnection(String dbName) {
    	Connection connection = null;
    	
    	// partial link excluding the database name
    	String url1 = "jdbc:mysql://" + server + "/";  // or "jdbc:mysql://localhost:3306/"
    	
    	// full link including the  database name
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
	    		if (dbName2.equalsIgnoreCase(dbName)) {
	    			dbExists = true;
	    			break;
	    		}
	    	}
	    	rSet.close();
	    	
	    	if (!dbExists) {
	    		// create the database
	    		String query = "CREATE DATABASE " + dbName;
	    		stmt.executeUpdate(query);
	    		System.out.println("Database created successfully...");
	    	} else {
	    		System.out.println("Database already exists...");
	    	}
	    	    	
	    	// connect to the database
	    	connection = DriverManager.getConnection(url2, USER, PASS);
	    	System.out.println("Connected to database " + dbName + "...");
    
    	} catch (SQLException e) {
    	
    	}
    	return connection;
    }

    // testing 2: create a function to create and return the connection
//	public static Connection getConnection() {
//		Connection connection = null;
//		
//	    try { // create object of statement class (required for execution of queries)		      
//	    	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // Setting initial credentials for database connectivity
//	        Statement stmt = conn.createStatement();
//	        
//    		connection = conn;
//	    	String sql1 = "CREATE DATABASE IF NOT EXISTS " + dbName + ";"; // create database
//    		stmt.executeUpdate(sql1); // execute the update operation to create the database students
////	    		System.out.println("Database created successfully.");  
//    		
//	    	String sql2 = "USE " + dbName + ";"; // use database
//	    	stmt.executeQuery(sql2); // execute the update operation to create the database students
//	    	System.out.println("Connecting...");
//	    	System.out.println("Using " + dbName + " database."); 
//	    } catch (SQLException e) {
////	    	System.out.println("Database already exists.");
////	       e.printStackTrace();  	  
//	    }
//	    return connection;
//    }
    
}

