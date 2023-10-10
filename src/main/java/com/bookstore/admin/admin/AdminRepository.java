package com.bookstore.admin.admin;

import com.bookstore.admin.entity.Admin;
import com.bookstore.admin.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Book, Integer> {
    void deleteBookById(Integer id);
}

