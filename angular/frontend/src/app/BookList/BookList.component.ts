import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import Book from '../entity/Book';


@Component({
  selector: 'app-BookList',
  templateUrl: './BookList.component.html',
  styleUrls: ['./BookList.component.scss']
})
export class BookListComponent implements OnInit {

  
  book: Book = new Book('Title', 'Image', 'COMIC', 20, 'John', 'Publisher_name', 'Book_content','2022-09-12', true);
  
  categoryList: any = ['MYSTERY','COMIC','HORROR','DETECTIVE','ACTION','THRILLER','SPORTS','FICTION'];
  selectCategory = '' ;
  books: any = [];
  successMessage: any = "";
  errorMessage: any = "";
  isSuccessful = false;

  constructor(public bookService: BookService) { }


  ngOnInit(): void {

  }


  save() {
    console.log('clicked');
    const observable = this.bookService.addBook(this.book);
    observable.subscribe((response) => {
      if(Number.isFinite(Number(response))){
        this.successMessage = "Book is saved successfully";
        this.errorMessage = "";
      }
      else{
        this.errorMessage = JSON.stringify(response);
        this.successMessage = "";
      }
    },
    (error)=>{
      if(error.status == 400){
        this.errorMessage= "Check the format"
      }
      if(error.status == 409){
        this.errorMessage = "Book title is already used. Please use different title to save the book";
      }
      else{
        this.errorMessage = "Error occured Please check the details";
      }
      this.successMessage = "";
    })
  }



}

