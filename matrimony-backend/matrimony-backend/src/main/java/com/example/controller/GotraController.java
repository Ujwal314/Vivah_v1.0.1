package com.example.controller;


import com.example.entity.Gotra;
import com.example.repository.GotraRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gotras")
@CrossOrigin(origins = "*")
public class GotraController {

    private final GotraRepository gotraRepository;

    public GotraController(GotraRepository gotraRepository) {
        this.gotraRepository = gotraRepository;
    }

    // Get all Gotras
    @GetMapping
    public List<Gotra> getAllGotras() {
        return gotraRepository.findAll();
    }

    // Get Gotra by ID
    @GetMapping("/{id}")
    public Gotra getGotraById(@PathVariable int id) {
        return gotraRepository.findById(id).orElse(null);
    }
}