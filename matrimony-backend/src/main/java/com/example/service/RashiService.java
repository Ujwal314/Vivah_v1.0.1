package com.example.service;

import com.example.entity.Rashi;
import com.example.repository.RashiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RashiService {

    private final RashiRepository rashiRepository;

    // Using professional constructor injection
    public RashiService(RashiRepository rashiRepository) {
        this.rashiRepository = rashiRepository;
    }

    /**
     * Retrieves all Rashis. This is a read-only, transactional operation.
     * @return A list of all Rashi entities.
     */
    @Transactional(readOnly = true)
    public List<Rashi> getAllRashi() {
        return rashiRepository.findAll();
    }

    /**
     * Finds a single Rashi by its unique ID. This is a read-only, transactional operation.
     * @param rashiId The ID of the Rashi to find.
     * @return An Optional<Rashi> which will be empty if no Rashi is found.
     */
    @Transactional(readOnly = true)
    public Optional<Rashi> getRashiById(int rashiId) {
        return rashiRepository.findById(rashiId);
    }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public Optional<String> getRashiNameByRashiId(int rashiId) {
        return rashiRepository.findRashiNameByRashiId(rashiId);
    }
}