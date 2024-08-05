package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repo.BookRepo;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> getAllAuthors()
    {
        return bookRepo.findAll();
    }
    public Book getBookById(int id){
        return bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Given id is incorrect"));
    }
    public Book saveOrUpdateBook(Book book)
    {
        return bookRepo.save(book);
    }
    public void deletebookById(int id){
        bookRepo.deleteById(id);
    }

}
