package com.codin9ninja.book.ibrary.api.controller;

import com.codin9ninja.book.ibrary.api.entity.Book;
import com.codin9ninja.book.ibrary.api.exceptions.ResourceNotFoundException;
import com.codin9ninja.book.ibrary.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> allBooks(){
        System.out.println("you've successfully logged in using your google account!...");
        return bookService.getAllBooks();
    }

    @GetMapping("/")
    public Principal userWelcome(Principal principal){
        System.out.println("you have successfully logged in using your google account");
        return principal;
    }
    @GetMapping("/{id}")
    public Optional<Book> retrieveBook(@PathVariable Integer id){
        if (!bookService.getABook(id).isPresent()){
            throw new ResourceNotFoundException("id: " + id + " not found");
        }else return bookService.getABook(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> allBooks(@RequestBody Book book){
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateABook(@RequestBody Book book){

        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void  deleteBook(@PathVariable Integer id){
        if (!bookService.getABook(id).isPresent()){
            throw new ResourceNotFoundException("id: " + id + " not found");
        }
        else bookService.deleteBookById(id);
    }

}
