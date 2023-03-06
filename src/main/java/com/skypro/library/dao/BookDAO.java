package com.skypro.library.dao;

import com.skypro.library.model.Book;

import java.util.List;

public interface BookDAO {
    void create(Book book);

    void update(Book book);

    void delete(String isbn);

    List<Book> getAll();

    Book get(String isbn);

}