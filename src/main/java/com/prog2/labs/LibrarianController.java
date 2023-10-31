package com.prog2.labs;

import java.awt.TexturePaint;
import java.util.List;
import java.util.Map;

public class LibrarianController {
	
	private static Book modelB; // model
	private static Student modelS; 
	private static Librarian modelL;
	private AdminMain view;
	
	public static void main(String[] args) {
//		Book book
//		LibrarianController controller = new LibrarianController(null, null);
	}
	public LibrarianController () {
		LibrarianController.modelL = (Librarian) PersonFactory.getPerson(AdminLogin.username);
	}
	
	public LibrarianController(Book modelB, Student modelS, Librarian modelL, AdminMain view) {
        this.modelB = modelB;
        this.modelS = modelS;
        this.modelL = modelL;
        this.view = view;
     }
	
	public static void addBook(Book model, String tableName) {
		Librarian.addBook(model, tableName);
	}
	
	public static void addStudent(Student student) {
		Librarian.addStudent(student);
	}
	// TO DO
	public boolean issueBook(Book book , Student s){
		return modelL.issueBook(book, s);
	}
	// TO DO
	public boolean returnBook(String bookSN, String studID, String issueId) {
		return modelL.returnBook(bookSN, studID, issueId);
	}
	// TO DO
	public static Map<String, String> viewIssuedBooks(){
		return Librarian.viewIssuedBooks();
	}
	
	public static Map<Integer, String> viewStudent() {
		return Librarian.viewStudent();
	}
	
	public Map<String, String> getCatalog(){
		return modelL.getCatalog();
	}
	
	public Map<Integer, String> getStudentMap() {
		return modelL.getStudentMap();
	}

	public List<Book> searchBookByTitle(String title) {
		return modelL.searchBookByTitle(title);
	}
	
	public List<Book> searchBookByName(String name) {
		return modelL.searchBookByName(name);
	}
	
	public List<Book> searchBookByPublisher(String publisher) {
		return modelL.searchBookByPublisher(publisher);
	}
	
	public void setBookTitle(String title){
        modelB.setTitle(title);
    }
	
	public void setBookSN(String sn){
        modelB.setSn(sn);
    }
	
	public void setBookAuthor(String author){
        modelB.setAuthor(author);
    }
	
	public void setBookPublisher(String publisher){
        modelB.setPublisher(publisher);
    }
	
	public void setBookPrice(double price){
        modelB.setPrice(price);
    }
	
	public Map<String, String> viewCatalog() {
		return modelL.viewCatalog();
	}

	
}
