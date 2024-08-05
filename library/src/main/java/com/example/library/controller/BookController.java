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

import com.example.library.entity.Book;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController{

    @Autowired
    private BookService BookService;
    
      @GetMapping
    public ResponseEntity<List<Book>> listBooks(){
        List<Book>Books = BookService.getAllBooks();
        return ResponseEntity.ok(Books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook (@PathVariable int id){
        Book Book = BookService.getBookById(id);
        if (Book ==null)
        {
            return ResponseEntity.notFound().build();
        }
        BookService.deletebookById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book Book = BookService.getBookById(id);
        if (Book ==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book Book) {
        Book existingBook = BookService.getBookById(id);
        if(existingBook == null){
            return ResponseEntity.notFound().build();
        }
        Book.setId(id);
        BookService.saveOrUpdateBook(Book);
        return ResponseEntity.ok(Book);
    }
    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book Book){
        Book createdBook = BookService.saveOrUpdateBook(Book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
    
    
}
