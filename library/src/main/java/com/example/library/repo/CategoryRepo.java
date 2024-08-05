package com.example.library.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}