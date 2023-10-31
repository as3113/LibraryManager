package com.prog2.labs;

public class PersonFactory {
	public static Person getPerson(String username) {
//		if(username.equals("lib")) {
		if(AdminLogin.rdbtnLibrarian.isSelected()) {
			return new Librarian();
		}
		if(AdminLogin.rdbtnStudent.isSelected()){
			return new Student();
		}
		return null;
	}
}
