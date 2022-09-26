package com.book.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	
	public Book addBooks(Book book) {
		return bookRepository.save(book);
	}

	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	
	public List<Book> searchBooks(String category, String author, BigDecimal price, String publisher) {
		List<Book> list = new ArrayList<>();
		Boolean flag =false;
		
		List<Book> books = bookRepository.findAll();
		if(!books.isEmpty()) {
			if(!StringUtils.isBlank(category) && !StringUtils.isBlank(author) && (price.compareTo(BigDecimal.ZERO)>0) && !StringUtils.isBlank(publisher)) {
				list = books.stream().
				filter(book -> ((book.getActive() == Boolean.TRUE) && book.getCategory().toString().equalsIgnoreCase(category) && 
						book.getAuthor().equalsIgnoreCase(author) && (book.getPrice().compareTo(price) == 0) 
						&& book.getPublisher().equalsIgnoreCase(publisher))).collect(Collectors.toList());
			}
			else {
				list = new ArrayList<>(books);
				
				if(!StringUtils.isBlank(author)) {
					flag =true;
					list = list.stream().
							filter(book -> (book.getActive() == Boolean.TRUE) && (book.getAuthor().equalsIgnoreCase(author))).collect(Collectors.toList());
				}
				if(!StringUtils.isBlank(category)) {
					flag =true;
					list = list.stream().
							filter(book -> (book.getActive() == Boolean.TRUE) && (book.getCategory().toString().equalsIgnoreCase(category))).collect(Collectors.toList());
				}
				if(price.compareTo(BigDecimal.ZERO)>0) {
					flag =true;
					list = list.stream().
							filter(book -> (book.getActive() == Boolean.TRUE) && (book.getPrice().compareTo(price) == 0)).collect(Collectors.toList());
				}
				if(!StringUtils.isBlank(publisher)) {
					flag =true;
					list = list.stream().
							filter(book -> (book.getActive() == Boolean.TRUE) && (book.getPublisher().equalsIgnoreCase(publisher))).collect(Collectors.toList());
				}
				if(flag.equals(Boolean.FALSE)) {
					list = new ArrayList<>();
				}
			}
		}
		return list;
	}
	
	
	public Book getBook(Integer bookId) {
		return bookRepository.findById(bookId).get();
	}

}
