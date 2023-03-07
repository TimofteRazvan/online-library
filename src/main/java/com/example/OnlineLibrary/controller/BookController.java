package com.example.OnlineLibrary.controller;

import com.example.OnlineLibrary.entity.Book;
import com.example.OnlineLibrary.service.AuthorService;
import com.example.OnlineLibrary.service.BookService;
import com.example.OnlineLibrary.service.CategoryService;
import com.example.OnlineLibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;


    @GetMapping(value = "/books", produces="application/json")
    public String findAllBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        // Add list books to model object "books"
        model.addAttribute("books", books);
        // Return the HTML
        return "books";
    }

    @GetMapping("/books/{id}")
    // PathVariable sets 'id' as the dynamic {id} variable
    public String findBook(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        // Return the HTML
        return "details-book";
    }

    @GetMapping("/add-book")
    public String addBook(Book book, Model model) {
        // We need to show all categories/publishers/authors in the UI for picking from drop-down
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "add-book";
    }

    // PostMapping because the method="post"
    @PostMapping("/save-book")
    // Book object is passed from update-book when we set method="post" and th:object="${book}"
    // BindingResult is used to check for validation errors in the update-book form
    public String saveBook(Book book, BindingResult bindingResult, Model model) {
        // If there are errors, return the same update page
        if(bindingResult.hasErrors()) {
            return "add-book";
        }
        bookService.createBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        // Redirect user to list of books
        return "redirect:/books";
    }

    @GetMapping("/update-book/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        // We need to show all categories/publishers/authors in the UI
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "update-book";
    }

    // PostMapping because the method="post"
    @PostMapping("/save-update-book/{id}")
    // Book object is passed from update-book when we set method="post" and th:object="${book}"
    // BindingResult is used to check for validation errors in the update-book form
    public String saveUpdate(@PathVariable Long id, Book book, BindingResult bindingResult, Model model) {
        // If there are errors, return the same update page
        if(bindingResult.hasErrors()) {
            return "update-book";
        }
        bookService.updateBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        // Redirect user to list of books
        return "redirect:/books";
    }

    @GetMapping("/remove-book/{id}")
    public String removeBook(@PathVariable Long id, Model model) {
        bookService.removeBook(id);
        // Pass list of all books after deleting the book to the UI
        model.addAttribute("books", bookService.findAllBooks());
        // Just return the same interface that lists all the books which will be updated
        return "redirect:/books";
    }
}
