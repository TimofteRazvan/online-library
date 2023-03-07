package com.example.OnlineLibrary;

import com.example.OnlineLibrary.entity.Author;
import com.example.OnlineLibrary.entity.Book;
import com.example.OnlineLibrary.entity.Category;
import com.example.OnlineLibrary.entity.Publisher;
import com.example.OnlineLibrary.service.BookService;
import com.example.OnlineLibrary.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineLibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService, CategoryService categoryService) {
		return(args) -> {
		};
	}
}
