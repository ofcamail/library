package com.skypro.library.service;

import com.skypro.library.dao.BookDAO;
import com.skypro.library.exceptions.BookException;
import com.skypro.library.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public void create(Book book) {
        validateBook(book);
        checkIsbnOnRepeatability(book);
        bookDAO.create(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        validateBook(book);
        bookDAO.update(book);
    }

    @Override
    @Transactional
    public void delete(String isbn) {
        get(isbn);
        bookDAO.delete(isbn);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    @Transactional
    public Book get(String isbn) {
        Book book = bookDAO.get(isbn);
        if (book == null) {
            throw new BookException("Book with isbn = " + isbn + " doesn't exist");
        }
        return bookDAO.get(isbn);
    }

    private void validateBookFieldsForNull(Book book) {
        if (book.getIsbn() == null
                || book.getAuthor() == null
                || book.getTitle() == null
                || book.getYearOfPublication() == null
                || book.getAuthor().isEmpty() || book.getAuthor().isBlank()
                || book.getTitle().isEmpty() || book.getTitle().isBlank()) {
            throw new BookException("All fields of the book must be filled in");
        }
    }

    private void validateYear(Book book) {
        if (book.getYearOfPublication() < 0) {
            throw new BookException("The year of publication cannot be negative");
        }
    }

    private void validateIsbn(Book book) {
        String isbn = book.getIsbn();
        String temp = isbn.replace("-", "");
        if (temp.length() != 13) {
            throw new BookException("You entered an invalid isbn");
        }

        char[] arrChar = temp.substring(0, 12).toCharArray();
        int[] arrInt = new int[temp.length() - 1];

        int sum = 0;
        for (int i = 0; i < arrChar.length; i++) {
            arrInt[i] = Character.getNumericValue(arrChar[i]);
            if (i % 2 != 0) {
                sum += arrInt[i] *= 1;
            } else sum += arrInt[i] *= 3;
        }
        int result = sum % 10;
        if (!isbn.endsWith(Integer.toString(result))) {
            throw new BookException("You entered an invalid isbn");
        }
    }

    private void validateBook(Book book) {
        validateBookFieldsForNull(book);
        validateYear(book);
        validateIsbn(book);
    }

    private void checkIsbnOnRepeatability(Book book) {
        boolean isRepeat = getAll().stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()));
        if (isRepeat) {
            throw new BookException("The book with isbn " + book.getIsbn() + " already exists in the library");
        }
    }
}