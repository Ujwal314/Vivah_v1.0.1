package com.example.service;

import com.example.entity.Religion;
import com.example.repository.ReligionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;


@Service
public class ReligionService {

    private final ReligionRepository religionRepository;

    public ReligionService(ReligionRepository religionRepository) {
        this.religionRepository = religionRepository;
    }

    /**
     * Adds a new Religion after validating it doesn't already exist.
     * This is a write operation and must be transactional.
     * @param religion The Religion object to be saved.
     * @return The saved Religion entity with its generated ID.
     * @throws DataIntegrityViolationException if a religion with the same name already exists.
     */
    @Transactional
    public Religion addReligion(Religion religion) {
        // Business Logic: Check if a religion with this name already exists (case-insensitive)
        religionRepository.findByReligionNameIgnoreCase(religion.getReligionName())
                .ifPresent(existing -> {
                    throw new DataIntegrityViolationException("Religion with name '" + religion.getReligionName() + "' already exists.");
                });
        return religionRepository.save(religion);
    }

    /**
     * Retrieves all Religions. This is a read-only operation for performance.
     * @return A list of all Religion entities.
     */
    @Transactional(readOnly = true)
    public List<Religion> getAllReligions() {
        return religionRepository.findAll();
    }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public Optional<String> getReligionNameByReligionId(int religionId) {
        return religionRepository.findReligionNameByReligionId(religionId);
    }

    @Transactional(readOnly = true)
    public Optional<Religion> getReligionById(int id) {
        return religionRepository.findById(id);
    }
}