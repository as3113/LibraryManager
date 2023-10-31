package com.prog2.labs;

import java.util.List;
import java.util.Map;

public interface Person {
	boolean issueBook(Book b, Student s);
	boolean returnBook(String bookSN, String studID, String issueID);
	Map<String, String> viewCatalog();
	Map<Integer, String> getStudentMap();
	List<Book> searchBookByTitle(String s);
	List<Book> searchBookByName(String s);
	List<Book> searchBookByPublisher(String s);

}
