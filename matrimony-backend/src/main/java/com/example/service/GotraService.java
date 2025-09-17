package com.example.service;

import com.example.entity.Gotra;
import com.example.repository.GotraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GotraService {

    private final GotraRepository gotraRepository;

    // Use constructor injection for dependencies
    public GotraService(GotraRepository gotraRepository) {
        this.gotraRepository = gotraRepository;
    }

    /**
     * Retrieves all Gotras from the database.
     * Marked as read-only for performance optimization.
     * @return a list of all Gotra entities.
     */
    @Transactional(readOnly = true)
    public List<Gotra> getAllGotras() {
        return gotraRepository.findAll();
    }

    /**
     * Finds a single Gotra by its ID.
     * Marked as read-only for performance optimization.
     * @param id the ID of the Gotra to find.
     * @return an Optional containing the Gotra if found, or an empty Optional otherwise.
     */
    @Transactional(readOnly = true)
    public Optional<Gotra> getGotraById(int id) {
        return gotraRepository.findById(id);
    }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public Optional<String> getGotraNameByGotraId(int gotraId) {
        return gotraRepository.findGotraNameByGotraId(gotraId);
    }
}