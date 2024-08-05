package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repo.BookRepo;

import jakarta.persistence.Cacheable;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Cacheable("books")
    public List<Book> getAllBooks() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bookRepo.findAll();
    }
    public Book getBookById(int id){
        return bookRepo.findById(id).orElse(null);
    }
    public Book saveOrUpdateBook(Book book)
    {
        return bookRepo.save(book);
    }
    public void deletebookById(int id){
        bookRepo.deleteById(id);
    }

}
