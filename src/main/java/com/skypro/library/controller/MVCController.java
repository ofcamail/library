package com.skypro.library.controller;


import com.skypro.library.model.Book;
import com.skypro.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/web")
public class MVCController {
    BookService bookService;

    public MVCController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "dashboard";
    }

}