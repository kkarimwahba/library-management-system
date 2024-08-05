package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Category;
import com.example.library.repo.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllcategory()
    {
        return categoryRepo.findAll();
    }
    public Category getCategoryById(int id){
        return categoryRepo.findById(id).orElse(null);
    }
    public Category saveOrUpdateCategory(Category category)
    {
        return categoryRepo.save(category);
    }
    public void deleteCategoryById(int id){
        categoryRepo.deleteById(id);
    }

}
