package com.example.library.controller;

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

import com.example.library.entity.Patron;
import com.example.library.service.PatronService;


@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<Patron>> listPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();  
        return ResponseEntity.ok(patrons);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable int id) {
        Patron patron = patronService.getPatronById(id); 
        if (patron == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patron);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePatron( @PathVariable int id) {
        Patron patron = patronService.getPatronById(id); 
        if (patron == null) {
            return ResponseEntity.notFound().build();
        }
        patronService.deletePatronById(id);  
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable int id, @RequestBody Patron patron) {
        if (patron==null) {
            return ResponseEntity.notFound().build();
        }
        patron.setId(id);
        Patron updatedPatron = patronService.saveOrUpdatePatron(patron);
        return ResponseEntity.ok(updatedPatron);
    }
    @PostMapping
    public ResponseEntity<Patron> addPatrom(@RequestBody Patron patron) {
        Patron createdPatron= patronService.saveOrUpdatePatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatron);
    }
    
}
