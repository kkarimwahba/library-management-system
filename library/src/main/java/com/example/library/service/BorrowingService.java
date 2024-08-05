package com.example.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.entity.Borrowing;
import com.example.library.entity.Patron;
import com.example.library.repo.BookRepo;
import com.example.library.repo.BorrowingRepo;
import com.example.library.repo.PatronRepo;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepo borrowingRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private PatronRepo patronRepo;
    
  
    public List<Borrowing> getAllBorrowing() {
        return borrowingRepo.findAll();
    }

    public Borrowing borrowBook(int bookId, int patronId) {
        Optional<Book> book = bookRepo.findById(bookId);
        Optional<Patron> patron = patronRepo.findById(patronId);

        if (book.isPresent() && patron.isPresent()) {
            Borrowing borrowing = new Borrowing();
            borrowing.setBook(book.get());
            borrowing.setPatron(patron.get());
            borrowing.setBorrowDate(LocalDate.now());
            return borrowingRepo.save(borrowing);
        }
        throw new RuntimeException("Book or Patron not found");
    }

    public Borrowing returnBook(int bookId, int patronId) {
        Optional<Borrowing> borrowing = borrowingRepo.findAll().stream()
            .filter(b -> b.getBook().getId() == bookId && b.getPatron().getId() == patronId)
            .findFirst();

        if (borrowing.isPresent()) {
            Borrowing record = borrowing.get();
            record.setReturnDate(LocalDate.now());
            return borrowingRepo.save(record);
        }
        throw new RuntimeException("Borrowing record not found");
    }
}
