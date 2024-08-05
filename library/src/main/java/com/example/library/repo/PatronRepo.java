package com.example.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Patron;

@Repository
public interface PatronRepo extends JpaRepository<Patron, Integer> {
}
