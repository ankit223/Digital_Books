import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookListComponent } from './BookList/BookList.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';
import { SearchbooksComponent } from './searchbooks/searchbooks.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { TokenService } from './token.service';




const routes: Routes = [
  { path: 'booklist', component: BookListComponent },
  { path: 'getallbooks', component: GetAllBooksComponent },
  { path: 'searchbooks', component: SearchbooksComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent }
  
  
  
]

@NgModule({
  declarations: [
    AppComponent, BookListComponent, GetAllBooksComponent, SearchbooksComponent, RegisterComponent, LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, FormsModule, HttpClientModule, RouterModule.forRoot(routes)
  ],
  providers: [{

    provide : HTTP_INTERCEPTORS,

    useClass: TokenService,

    multi   : true,

  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
