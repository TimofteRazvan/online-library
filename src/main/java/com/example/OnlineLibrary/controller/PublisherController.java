package com.example.OnlineLibrary.controller;

import com.example.OnlineLibrary.entity.Publisher;
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
public class PublisherController {
    // GetAll | Add | Update | Delete
    @Autowired
    PublisherService publisherService;

    @GetMapping("/publishers")
    public String findAllPublishers(Model model) {
        List<Publisher> publishers = publisherService.findAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publishers";
    }

    @GetMapping("/add-publisher")
    public String addPublisher(Publisher publisher, Model model) {
        return "add-publisher";
    }

    @PostMapping("/save-publisher")
    public String savePublisher(Publisher publisher, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "publishers";
        }
        publisherService.createPublisher(publisher);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @GetMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable Long id, Model model) {
        Publisher publisher = publisherService.findPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "update-publisher";
    }

    @PostMapping("/save-update-publisher/{id}")
    public String updatePublisher(@PathVariable Long id, Publisher publisher, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "update-publisher";
        }
        publisherService.updatePublisher(publisher);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @GetMapping("/remove-publisher/{id}")
    public String removepublisher(@PathVariable Long id, Model model) {
        publisherService.removePublisher(id);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }
}
