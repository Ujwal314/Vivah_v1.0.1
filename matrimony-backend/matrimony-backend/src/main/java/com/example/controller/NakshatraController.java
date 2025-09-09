package com.example.controller;

import com.example.entity.Nakshatra;
import com.example.repository.NakshatraRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nakshatras")
@CrossOrigin(origins = "*")
public class NakshatraController {

    private final NakshatraRepository nakshatraRepository;

    public NakshatraController(NakshatraRepository nakshatraRepository) {
        this.nakshatraRepository = nakshatraRepository;
    }

    // Get all Nakshatras
    @GetMapping
    public List<Nakshatra> getAllNakshatras() {
        return nakshatraRepository.findAll();
    }

    // Get Nakshatra by ID
    @GetMapping("/{id}")
    public Nakshatra getNakshatraById(@PathVariable int id) {
        return nakshatraRepository.findById(id).orElse(null);
    }
}
