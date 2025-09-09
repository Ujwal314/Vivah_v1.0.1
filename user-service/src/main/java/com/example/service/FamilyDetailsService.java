package com.example.service;

import com.example.entity.FamilyDetails;
import com.example.Repositories.FamilyDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamilyDetailsService {

    private final FamilyDetailsRepository familyDetailsRepository;

    // Create / Save
    public FamilyDetails saveFamilyDetails(FamilyDetails details) {
        return familyDetailsRepository.save(details);
    }

    // Get by ID
    public Optional<FamilyDetails> getFamilyDetails(Long id) {
        return familyDetailsRepository.findById(id);
    }

    // Get by User ID
    public Optional<FamilyDetails> getFamilyDetailsByUser(Long userId) {
        return familyDetailsRepository.findByUser_UserId(userId);
    }

    // Update
    public Optional<FamilyDetails> updateFamilyDetails(Long id, FamilyDetails newDetails) {
        return familyDetailsRepository.findById(id).map(existing -> {
            existing.setFatherName(newDetails.getFatherName());
            existing.setMotherName(newDetails.getMotherName());
            existing.setSiblingsCount(newDetails.getSiblingsCount());
            existing.setAnnualIncome(newDetails.getAnnualIncome());
            // add other fields as per your entity
            return familyDetailsRepository.save(existing);
        });
    }

    // Delete
    public boolean deleteFamilyDetails(Long id) {
        if (familyDetailsRepository.existsById(id)) {
            familyDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
