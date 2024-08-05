package com.example.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Borrowing;

public interface BorrowingRepo extends JpaRepository<Borrowing, Integer> {
}
