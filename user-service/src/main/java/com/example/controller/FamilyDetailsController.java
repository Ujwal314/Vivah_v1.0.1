package com.example.controller;

import com.example.entity.FamilyDetails;
import com.example.entity.User;
import com.example.service.FamilyDetailsService;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/family-details")
@RequiredArgsConstructor
public class FamilyDetailsController {

    @Autowired
    private UserService userService;
    @Autowired
    private FamilyDetailsService familyService;

    // Create
    @PostMapping("/me")
    public ResponseEntity<Optional<FamilyDetails>> addFamilyDetails(@Valid @RequestBody FamilyDetails familyDetails) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        return ResponseEntity.ok(familyService.saveFamilyDetails(user.getUserId(),familyDetails));
    }

    // Get by ID
    @GetMapping("/me")
    public ResponseEntity<FamilyDetails> getFamilyDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        return familyService.getFamilyDetailsByUser(user.getUserId())
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
    @PutMapping("/me")
    public ResponseEntity<FamilyDetails> updateFamilyDetails(@Valid @RequestBody FamilyDetails familyDetails) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        return familyService.updateFamilyDetails(familyService.getFamilyDetailsByUser(user.getUserId()).get().getFamilyId(), familyDetails)
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
