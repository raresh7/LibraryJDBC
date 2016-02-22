package entities;

import java.time.LocalDate;

public class Transaction {
	public static int nextId;
	private int id;
	private User user;
	private Book book;
	private LocalDate dateOfBorrow;
	private LocalDate expectedDateOfReturn;
	private LocalDate dateOfReturn;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LocalDate  getDateOfBorrow() {
		return dateOfBorrow;
	}
	public void setDateOfBorrow(LocalDate  dateOfBorrow) {
		this.dateOfBorrow = dateOfBorrow;
	}
	public LocalDate  getExpectedDateOfReturn() {
		return expectedDateOfReturn;
	}
	public void setExpectedDateOfReturn(LocalDate  expectedDateofReturn) {
		this.expectedDateOfReturn = expectedDateofReturn;
	}
	public LocalDate  getDateOfReturn() {
		return dateOfReturn;
	}
	public void setDateOfReturn(LocalDate  dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Transaction(User user, Book book, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn, int id){
		this.user = user;
		this.book = book;
		this.dateOfBorrow = dateOfBorrow;
		this.expectedDateOfReturn = expectedDateOfReturn;
		this.id = id;
		nextId = id+1;
	}
}
