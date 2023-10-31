package com.prog2.labs;

import java.util.List;
import java.util.Map;

public class StudentController {
	private Student model;
	// Book modelB;
	private StuMain view;
	
	public StudentController() {
		this.model = (Student) PersonFactory.getPerson(AdminLogin.username);
//		this.view = new StuMain();
	}
	
	public StudentController(Student model, StuMain view) {
		this.model = model;
		this.view = view;
	}
	
	public Map<String, String> viewCatalog() {
		return model.viewCatalog();
	}
	
	public Map<String, String> viewMyBooks() {
		return model.viewMyBooks();
	}
	
	public Map<String, String> getCatalog() {
		return model.getCatalog();
	}
	
	public Map<Integer, String> getStudentMap(){
		return model.getStudentMap();
	}
	
	public boolean returnBook(String bookSN, String studID, String issueID) {
		return model.returnBook(bookSN, studID, issueID);
	}
	public List<Book> searchBookByTitle (String title){
		return model.searchBookByTitle(title);
	}
	public List<Book> searchBookByName (String name){
		return model.searchBookByName(name);
	}
	public List<Book> searchBookByPublisher (String publisher){
		return model.searchBookByPublisher(publisher);
	}
	public boolean issueBook (Book b, Student s) {
		return model.issueBook(b, s);
	}

	public String welcomeStudent() {
		return model.welcomeStudent();
	}
}
