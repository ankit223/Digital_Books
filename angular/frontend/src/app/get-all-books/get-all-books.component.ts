import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import Book from '../entity/Book';

@Component({
  selector: 'app-get-all-books',
  templateUrl: './get-all-books.component.html',
  styleUrls: ['./get-all-books.component.scss']
})
export class GetAllBooksComponent implements OnInit {
  
  
  books:any=[];
  
  constructor(public bookService: BookService) { }


  ngOnInit(): void {

    this.getAllBooks();
  }
  getAllBooks(){
    const observable = this.bookService.getAllBooks();
  observable.subscribe((books) => {
    console.log(books);
    this.books = books;
  })
  }
  getBook(bookId : number){
    const observable = this.bookService.getBook(bookId);
    observable.subscribe(response => {
     this.getAllBooks();
   })
  }
  

}
