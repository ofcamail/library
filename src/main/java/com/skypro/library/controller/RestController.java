package com.skypro.library.controller;

import com.skypro.library.model.Book;
import com.skypro.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private BookService bookService;

    public RestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public Book createBook(@RequestBody Book book) {
        bookService.create(book);
        return book;
    }

    @PatchMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        bookService.update(book);
        return book;
    }

    @DeleteMapping("/book")
    public String deleteBook(@RequestParam String isbn) {
        bookService.delete(isbn);
        return "Book " + isbn + " removed";
    }

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }
}