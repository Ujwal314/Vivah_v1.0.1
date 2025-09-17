package com.example.controller;

import com.example.entity.Religion;
import com.example.service.ReligionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/religions")
public class ReligionController {

    private final ReligionService religionService;

    public ReligionController(ReligionService religionService) {
        this.religionService = religionService;
    }

    // ✅ Add a new religion
    @PostMapping
    public ResponseEntity<Religion> addReligion(@RequestBody Religion religion) {
        if ("Muslim".equalsIgnoreCase(religion.getReligionName())) {
            return ResponseEntity.badRequest().build();
        }
        Religion saved = religionService.addReligion(religion);
        return ResponseEntity.ok(saved);
    }


    // ✅ Get all religions
    @GetMapping
    public ResponseEntity<List<Religion>> getAllReligions() {
        List<Religion> religions = religionService.getAllReligions();
        return ResponseEntity.ok(religions);
    }
}
