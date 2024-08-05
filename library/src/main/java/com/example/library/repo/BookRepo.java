package com.example.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{
    
}