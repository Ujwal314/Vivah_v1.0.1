package com.example.service;

import com.example.entity.Caste;
import com.example.repository.CasteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 2. Import Transactional

import java.util.List;
import java.util.Optional;

@Service
public class CasteService {

    // 1. Use Constructor Injection
    private final CasteRepository casteRepository;

    public CasteService(CasteRepository casteRepository) {
        this.casteRepository = casteRepository;
    }

    // Example of a write method
    // @Transactional
    // public Caste createCaste(Caste caste) {
    //     return casteRepository.save(caste);
    // }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public List<Caste> getAllCastes() {
        return casteRepository.findAll();
    }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public Optional<Caste> getCasteById(int id) {
        return casteRepository.findById(id);
    }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public List<Caste> getCastesByReligionId(int religionId) {
        return casteRepository.findByReligionReligionId(religionId);
    }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public Optional<String> getCasteNameByCasteId(int casteId) {
        return casteRepository.findCasteNameByCasteId(casteId);
    }
}