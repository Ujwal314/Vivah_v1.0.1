package com.example.controller;

import com.example.entity.FamilyDetails;
import com.example.service.FamilyDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family-details")
@RequiredArgsConstructor
public class FamilyDetailsController {

    private final FamilyDetailsService familyService;

    // Create
    @PostMapping
    public ResponseEntity<FamilyDetails> addFamilyDetails(@Valid @RequestBody FamilyDetails familyDetails) {
        return ResponseEntity.ok(familyService.saveFamilyDetails(familyDetails));
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<FamilyDetails> getFamilyDetails(@PathVariable Long id) {
        return familyService.getFamilyDetails(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<FamilyDetails> getFamilyDetailsByUser(@PathVariable Long userId) {
        return familyService.getFamilyDetailsByUser(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<FamilyDetails> updateFamilyDetails(
            @PathVariable Long id,
            @Valid @RequestBody FamilyDetails familyDetails) {

        return familyService.updateFamilyDetails(id, familyDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFamilyDetails(@PathVariable Long id) {
        boolean deleted = familyService.deleteFamilyDetails(id);
        if (deleted) {
            return ResponseEntity.ok("Family details deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
