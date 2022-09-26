package com.book.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.book.controller.BookRestController;
import com.book.entity.Book;
import com.book.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookRestControllerTest {

	@Mock
	BookService bookService;

	@InjectMocks
	BookRestController bookRestController;

	@Test
	public void testGetBook() {

		Book book = createBookTest();
		String emailId = "abc";
		Integer bookId = 2;
		when(bookService.getBook(bookId)).thenReturn(book);
		assertEquals(new ResponseEntity<>(book, HttpStatus.OK), bookRestController.getBook(emailId, bookId));
	}

	@Test
	public void testGetAllBooks() {
		Book book = createBookTest();
		List<Book> list = new ArrayList<>();
		list.add(book);
		String emailId = "abc";
		when(bookService.getAllBooks()).thenReturn(list);
		assertEquals(list, bookRestController.getAllBooks(emailId));
	}

	@Test
	public void testSearchBooks() {
		Book book = createBookTest();
		List<Book> list = new ArrayList<>();
		list.add(book);
		String category = "MYSTERY";
		String author = "Ankit";
		BigDecimal price = new BigDecimal(25.0);
		String publisher = "NANO";
		when(bookService.searchBooks(category, author, price, publisher)).thenReturn(list);
		assertEquals(new ResponseEntity<>(list, HttpStatus.OK),
				bookRestController.searchBooks(category, author, price, publisher));
	}

	Book createBookTest() {
		Book bookTest = new Book();
		bookTest.setId(1);
		bookTest.setLogo("book.url");
		bookTest.setTitle("book");
		bookTest.setAuthor("Ankit");
		bookTest.setPublisher("NANO");
		bookTest.setContent("content");
		bookTest.setActive(true);
		return bookTest;
	}
}
