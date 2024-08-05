package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Category;
import com.example.library.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController{

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listCategorys(){
        List<Category> category = categoryService.getAllcategory();
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCategory (@PathVariable int id){
        Category Category = categoryService.getCategoryById(id);
        if (Category ==null)
        {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        if (category ==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category existingCategory = categoryService.getCategoryById(id);
        if(existingCategory == null){
            return ResponseEntity.notFound().build();
        }
        category.setId(id);
        categoryService.saveOrUpdateCategory(category);
        return ResponseEntity.ok(category);
    }
    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category Category){
        Category createdCategory = categoryService.saveOrUpdateCategory(Category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    
}