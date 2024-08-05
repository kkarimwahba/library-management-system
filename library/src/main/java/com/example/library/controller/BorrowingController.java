package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Borrowing;
import com.example.library.service.BorrowingService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @Transactional
    @GetMapping
    public ResponseEntity<List<Borrowing>> listBorrows() {
        List<Borrowing> borrows =  borrowingService.getAllBorrowing();
        return ResponseEntity.ok(borrows);
    }
    
    @Transactional
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public Borrowing borrowBook(@PathVariable int bookId, @PathVariable int patronId) {
        return borrowingService.borrowBook(bookId, patronId);
    }
    
    @Transactional
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public Borrowing returnBook(@PathVariable int bookId, @PathVariable int patronId) {
        return borrowingService.returnBook(bookId, patronId);
    }
}
