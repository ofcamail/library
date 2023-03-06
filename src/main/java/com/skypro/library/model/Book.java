package com.skypro.library.model;

import org.springframework.data.relational.core.sql.In;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private Integer yearOfPublication;
    private String isbn;

    public Book() {
    }

    public Book(String title, String author, Integer yearOfPublication, String isbn) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(yearOfPublication, book.yearOfPublication) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, yearOfPublication, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}