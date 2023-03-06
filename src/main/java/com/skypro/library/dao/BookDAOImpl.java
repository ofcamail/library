package com.skypro.library.dao;

import com.skypro.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final String SQL_FIND_BOOK = "select * from book where isbn = ?";
    private final String SQL_UPDATE_BOOK = "update book set title = ?, author = ?, year_of_publication  = ? where isbn = ?";
    private final String SQL_INSERT_BOOK = "insert into book VALUES(?,?,?,?)";
    private final String SQL_GET_ALL = "select * from book";
    private final String SQL_DELETE_BOOK = "delete from book where isbn = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(SQL_INSERT_BOOK,
                book.getTitle(),
                book.getAuthor(),
                book.getYearOfPublication(),
                book.getIsbn());
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update(SQL_UPDATE_BOOK,
                book.getTitle(),
                book.getAuthor(),
                book.getYearOfPublication(),
                book.getIsbn());
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(SQL_DELETE_BOOK, isbn);
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book get(String isbn) {
        return jdbcTemplate.query(SQL_FIND_BOOK, new Object[]{isbn}, new BeanPropertyRowMapper<>(Book.class)).stream().findFirst().orElse(null);
    }


}