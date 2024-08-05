package com.example.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer>{
    
}