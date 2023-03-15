package com.codin9ninja.book.ibrary.api.service;

import com.codin9ninja.book.ibrary.api.entity.Book;
import com.codin9ninja.book.ibrary.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getABook(Integer id){
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book){
        return  bookRepository.save(book);
    }

    public Book updateBook(Book book){
        Book book1 = bookRepository.findById(book.getId()).orElse(null);
        book1.setBookName(book.getBookName());
        book1.setAuthorName(book.getAuthorName());
        book1.setPublisher(book.getPublisher());
        return bookRepository.save(book1);
    }

    public void deleteBookById(Integer id){
        bookRepository.deleteById(id);
    }

}
