package com.book.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.book.entity.Book;
import com.book.entity.Category;
import com.book.repository.BookRepository;
import com.book.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	BookRepository bookRepository;

	@InjectMocks
	BookService bookService;

	@Test
	public void testGetBook() {
		Integer bookId = 1;
		Book book1 = createBookTest();
		Optional<Book> book = Optional.of(createBookTest());
		when(bookRepository.findById(bookId)).thenReturn(book);
		assertEquals(book1, bookService.getBook(bookId));
	}

	@Test
	public void testGetAllBooks() {

	}

	@Test
	public void testSearchBooks() {
		List<Book> list = new ArrayList<>();
		Book book = createBookTest();
		String category = "MYSTERY";
		String author = "Ankit";
		BigDecimal price = new BigDecimal(25);
		String publisher = "NANO";
		list.add(book);
		when(bookRepository.findAll()).thenReturn(list);
		assertEquals(list, bookService.searchBooks(category, author, price, publisher));
	}

	@Test
	void testSaveBook() {
		Book book = createBookTest();
		when(bookRepository.save(book)).thenReturn(book);
		bookService.addBooks(book);
		assertEquals(1, book.getId());
	}

	Book createBookTest() {
		Book bookTest = new Book();
		bookTest.setId(1);
		bookTest.setLogo("book.url");
		bookTest.setTitle("book");
		bookTest.setAuthor("Ankit");
		bookTest.setCategory(Category.MYSTERY);
		bookTest.setPublisher("NANO");
		bookTest.setContent("content");
		bookTest.setActive(true);
		return bookTest;
	}
}
