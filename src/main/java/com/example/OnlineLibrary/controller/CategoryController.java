package com.example.OnlineLibrary.controller;

import com.example.OnlineLibrary.entity.Author;
import com.example.OnlineLibrary.entity.Category;
import com.example.OnlineLibrary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    // GetAll | Add | Update | Delete
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String findAllCategories(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/add-category")
    public String addCategory(Category category, Model model) {
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "categories";
        }
        categoryService.createCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/update-category/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "update-category";
    }

    @PostMapping("/save-update-category/{id}")
    public String updateCategory(@PathVariable Long id, Category category, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/remove-category/{id}")
    public String removeCategory(@PathVariable Long id, Model model) {
        categoryService.removeCategory(id);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }
}
