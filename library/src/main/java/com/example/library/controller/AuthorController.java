package com.example.library.controller;

import java.util.List;

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

import com.example.library.entity.Author;
import com.example.library.service.AuthorService;




@RestController
@RequestMapping("/api/authors")
public class AuthorController{
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> listAuthors(){
        List<Author>authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAuthor (@PathVariable int id){
        Author author = authorService.getAuthorById(id);
        if (author ==null)
        {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author ==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        Author existingAuthor = authorService.getAuthorById(id);
        if(existingAuthor == null){
            return ResponseEntity.notFound().build();
        }
        author.setId(id);
        authorService.saveOrUpdateAuthor(author);
        return ResponseEntity.ok(author);
    }
    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author){
        Author createdAuthor = authorService.saveOrUpdateAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }
    
    
}