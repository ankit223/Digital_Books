import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import Book from '../entity/Book';

@Component({
  selector: 'app-searchbooks',
  templateUrl: './searchbooks.component.html',
  styleUrls: ['./searchbooks.component.scss']
})
export class SearchbooksComponent implements OnInit {
  
  books: any = [];
  book: Book = new Book('Title', 'Image', 'COMIC', 20, 'John', 'Publisher_name', 'Book_content','2022-09-12', true);
  
  categoryList: any = ['MYSTERY','COMIC','HORROR','DETECTIVE','ACTION','THRILLER','SPORTS','FICTION'];
  errorMessage: any = "";
  message: any = "";
  constructor(public bookService: BookService) { }

  ngOnInit(): void {
  }
 
  searchBooks(){
    
    const observable = this.bookService.searchBooks(this.book.category, this.book.author, this.book.price, this.book.publisher);
    observable.subscribe((books)=>{
      this.books = books;
      this.message = "";
      if(this.books.length == 0){
        this.message = "No search results found. Please verify the details and search";
        this.books = [];
      }
    },
    (error)=>{
      if(error.status == 400){
        this.errorMessage ="Check the details again"
      }
      this.message = "No search results found.";
      
    })
  }
      
}