package com.bookstore.admin.admin;

import com.bookstore.admin.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/admin")
    private String getSecret() {
        return "Only admins can see this!";
    }

    @Transactional
    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        // Authentication logic here: Ensure the user is an admin.
        // If authenticated:
        adminRepository.deleteBookById(id);
        return "Book deleted successfully!";
    }
}

