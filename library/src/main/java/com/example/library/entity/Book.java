package com.example.library.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToMany
    @JoinTable(name="books_authors",
               joinColumns={@JoinColumn(name="book_id")},
               inverseJoinColumns={@JoinColumn(name="author_id")})
    private List<Author> authors;  // Changed from 'author' to 'authors'

    @ManyToMany
    @JoinTable(name = "books_categories",
               joinColumns={@JoinColumn(name = "book_id")},
               inverseJoinColumns={@JoinColumn(name="category_id")})
    private List<Category> categories;

  
    private int isbn;
    private String publicationYear;

    public Book() {}

    // Getters and setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {  
        return this.authors;
    }

    public void setAuthors(List<Author> authors) {  
        this.authors = authors;
    }
    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public int getIsbn() {
        return this.isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
}
