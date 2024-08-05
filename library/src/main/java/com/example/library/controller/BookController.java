package com.example.library.controller;

import java.util.ArrayList;
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

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.example.library.service.CategoryService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;  

    @Autowired
    private AuthorService authorService;  

    @Autowired
    private CategoryService categoryService;  

    @GetMapping
    public ResponseEntity<List<Book>> listBooks() {
        List<Book> books = bookService.getAllBooks();  
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable int id) {
        Book book = bookService.getBookById(id); 
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        bookService.deletebookById(id);  
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id); 
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) { 
    //     Book existingBook = bookService.getBookById(id);
    //     if (existingBook == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //         // Ensure authors and categories are not null
    // if (book.getAuthors() == null) {
    //     book.setAuthors(new ArrayList<>());
    // }
    // if (book.getCategories() == null) {
    //     book.setCategories(new ArrayList<>());
    // }

    // List<Author> authors = new ArrayList<>();
    // for (Author author : book.getAuthors()) {
    //     Author foundAuthor = authorService.getAuthorById(author.getId());
    //     if (foundAuthor == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     authors.add(foundAuthor);
    // }
    // book.setAuthors(authors);

    // List<Category> categories = new ArrayList<>();
    // for (Category category : book.getCategories()) {
    //     Category foundCategory = categoryService.getCategoryById(category.getId());
    //     if (foundCategory == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     categories.add(foundCategory);
    // }
    // book.setCategories(categories);
    //     book.setId(id);
    //     bookService.saveOrUpdateBook(book);
    //     return ResponseEntity.ok(book);
    // }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook == null) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        bookService.saveOrUpdateBook(book);
        return ResponseEntity.ok(book);
    }
    @PostMapping
public ResponseEntity<Book> saveBook(@RequestBody Book book) {
    // Ensure authors and categories are not null
    if (book.getAuthors() == null) {
        book.setAuthors(new ArrayList<>());
    }
    if (book.getCategories() == null) {
        book.setCategories(new ArrayList<>());
    }

    List<Author> authors = new ArrayList<>();
    for (Author author : book.getAuthors()) {
        Author foundAuthor = authorService.getAuthorById(author.getId());
        if (foundAuthor == null) {
            return ResponseEntity.notFound().build();
        }
        authors.add(foundAuthor);
    }
    book.setAuthors(authors);

    List<Category> categories = new ArrayList<>();
    for (Category category : book.getCategories()) {
        Category foundCategory = categoryService.getCategoryById(category.getId());
        if (foundCategory == null) {
            return ResponseEntity.notFound().build();
        }
        categories.add(foundCategory);
    }
    book.setCategories(categories);

    Book createdBook = bookService.saveOrUpdateBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
}

}
