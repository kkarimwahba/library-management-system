package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Patron;
import com.example.library.repo.PatronRepo;

@Service
public class PatronService {

    @Autowired
    private PatronRepo patronRepo;

    public List<Patron> getAllPatrons()
    {
        return patronRepo.findAll();
    }
    public Patron getPatronById(int id){
        return patronRepo.findById(id).orElse(null);
    }
    public Patron saveOrUpdatePatron(Patron patron)
    {
        return patronRepo.save(patron);
    }
    public void deletePatronById(int id){
        patronRepo.deleteById(id);
    }
}
