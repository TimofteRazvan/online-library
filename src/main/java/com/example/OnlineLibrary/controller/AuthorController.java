package com.example.OnlineLibrary.controller;

import com.example.OnlineLibrary.entity.Author;
import com.example.OnlineLibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController {
    // GetAll | Add | Update | Delete

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public String findAllAuthors(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/add-author")
    public String addAuthor(Author author, Model model) {
        return "add-author";
    }

    @PostMapping("/save-author")
    public String saveAuthor(Author author, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "authors";
        }
        authorService.createAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Model model) {
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "update-author";
    }

    @PostMapping("/save-update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Author author, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "update-author";
        }
        authorService.updateAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/remove-author/{id}")
    public String removeAuthor(@PathVariable Long id, Model model) {
        authorService.removeAuthor(id);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }
}
