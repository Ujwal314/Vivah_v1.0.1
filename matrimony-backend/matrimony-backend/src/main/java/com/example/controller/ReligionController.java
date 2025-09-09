package com.example.controller;

import com.example.entity.Religion;
import com.example.service.ReligionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/religions")
public class ReligionController {
    private final ReligionService religionService;

    public ReligionController(ReligionService religionService) {
        this.religionService = religionService;
    }

    // Add new religion
    @PostMapping
    public Religion addReligion(@RequestBody Religion religion) {
        return religionService.addReligion(religion);
    }

    // Get all religions
    @GetMapping
    public List<Religion> getAllReligions() {
        return religionService.getAllReligions();
    }
}
