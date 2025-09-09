package com.example.controller;
import com.example.entity.Caste;
import com.example.service.CasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/castes")
public class CasteController {

    private final CasteService casteService;

    public CasteController(CasteService casteService) {
        this.casteService = casteService;
    }

    @PostMapping
    public ResponseEntity<Caste> createCaste(@RequestBody Caste caste) {
        return ResponseEntity.ok(casteService.createCaste(caste));
    }

    @GetMapping
    public ResponseEntity<List<Caste>> getAllCastes() {
        return ResponseEntity.ok(casteService.getAllCastes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caste> getCasteById(@PathVariable int id) {
        return casteService.getCasteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/religion/{religionId}")
    public ResponseEntity<List<Caste>> getCastesByReligion(@PathVariable int religionId) {
        return ResponseEntity.ok(casteService.getCastesByReligionEntity(religionId));
    }
}