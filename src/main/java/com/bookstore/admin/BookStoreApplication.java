package com.bookstore.admin;

import com.bookstore.admin.book.BookRepository;
import com.bookstore.admin.book.BookService;
import com.bookstore.admin.category.CategoryRepository;
import com.bookstore.admin.entity.Category;
import com.bookstore.admin.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreApplication {

	@Autowired
	private BookRepository bookService;
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}



}
