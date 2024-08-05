package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Author;
import com.example.library.repo.AuthorRepo;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public List<Author> getAllAuthors()
    {
        return authorRepo.findAll();
    }
    public Author getAuthorById(int id){
        return authorRepo.findById(id).orElseThrow(()-> new RuntimeException("Given id is incorrect"));
    }
    public Author saveOrUpdateAuthor(Author author)
    {
        return authorRepo.save(author);
    }
    public void deleteAuthorById(int id){
        authorRepo.deleteById(id);
    }

}
