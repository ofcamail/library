package com.skypro.library.service;

import com.skypro.library.model.Book;

import java.util.List;

public interface BookService {

    void create(Book book);

    void update(Book book);

    void delete(String isbn);

    List<Book> getAll();

    Book get(String isbn);

}