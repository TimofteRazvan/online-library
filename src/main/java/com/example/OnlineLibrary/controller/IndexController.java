package com.example.OnlineLibrary.controller;

import com.example.OnlineLibrary.entity.Book;
import com.example.OnlineLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String listHome() {
        return "index";
    }

    @GetMapping("/search")
    public String searchAll(Book book, @RequestParam(value = "q", required = false) Model model, String q) {
        List<Book> books;
        if(q==null) {
            books = bookService.findAllBooks();
        }
        else {
            books = bookService.findBooksByKeyword(q);
        }
        model.addAttribute("search", books);
        return "search";
    }
}
