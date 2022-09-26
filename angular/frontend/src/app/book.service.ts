import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Book from './entity/Book';
import { TokenStorageService } from './token-storage.service';



const REST_API = 'http://localhost:8000';
@Injectable({
  providedIn: 'root'
})

export class BookService {
 
  constructor(public client: HttpClient, private token: TokenStorageService) { }

  addBook(book: Book) {
    const authorId = 'John'
    return this.client.post(REST_API + "/author/" + authorId + "/books", book);
  }

  searchBooks(category: String, author: String, price: number, publisher: String) {
    return this.client.get(REST_API + "/books/search?category=" + category +
      "&author=" + author + "&price=" + price + "&publisher=" + publisher);
  }
  
  getAllBooks() {
    const emailId = 'abc';
    return this.client.get(REST_API + "/readers/" + emailId + "/books");
  }
  getBook(bookId: number) {
    const emailId = 'abc';
    return this.client.get(REST_API + "/readers/" + emailId + "/books" + bookId);
  }
  


}