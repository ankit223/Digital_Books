package com.book.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Book;
import com.book.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin
public class BookRestController extends BaseController {

	@Autowired
	BookService bookService; // dependency

	// GetMapping (reader can read book)
	@GetMapping("/readers/{emailId}/books/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable String emailId, @PathVariable Integer bookId) {
		ResponseEntity<Book> responseEntity;
		Book book = bookService.getBook(bookId);
		log.debug("get the book");
		if (book != null) {
			responseEntity = new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// GetMapping (reader can find all purchased books)
	@GetMapping("/readers/{emailId}/books")
	public List<Book> getAllBooks(@PathVariable String emailId) {
		log.debug("getAllBooks called");
		return bookService.getAllBooks();
	}

	// GetMapping (reader can search books)
	// ("books/search/category/author/price/publisher")

	@GetMapping("/books/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam String category, @RequestParam String author,
			@RequestParam BigDecimal price, @RequestParam String publisher) {
		List<Book> list = bookService.searchBooks(category, author, price, publisher);
		ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		return responseEntity;
	}

	// PostMapping (author can create book)

	@PostMapping("/author/{authorId}/books")
	@ResponseStatus(code = HttpStatus.CREATED)
	ResponseEntity<Integer> addBook(@PathVariable String authorId, @Valid @RequestBody Book book) {
		book.setAuthor(authorId);
		bookService.addBooks(book);
		log.debug("add the books");
		int bookId = book.getId();
		ResponseEntity<Integer> responseEntity = new ResponseEntity<>(bookId, HttpStatus.CREATED);
		return responseEntity;
	}
	

}
