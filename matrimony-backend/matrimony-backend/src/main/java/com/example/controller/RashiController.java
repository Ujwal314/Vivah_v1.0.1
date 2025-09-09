package com.example.controller;

import com.example.entity.Rashi;
import com.example.repository.RashiRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rashi")
public class RashiController {
    private final RashiRepository rashiRepository;

    public RashiController(RashiRepository rashiRepository){
        this.rashiRepository=rashiRepository;
    }

    @GetMapping
    public List<Rashi> getAllRashi(){
        return rashiRepository.findAll();
    }

    @GetMapping("/{rashiId}")
    public ResponseEntity<Rashi> getRashiById(@PathVariable int rashiId){
        return rashiRepository.findById(rashiId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
