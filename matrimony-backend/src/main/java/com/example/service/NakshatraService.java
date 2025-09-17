package com.example.service;

import com.example.entity.Nakshatra;
import com.example.repository.NakshatraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NakshatraService {

    private final NakshatraRepository nakshatraRepository;

    // Using professional constructor injection
    public NakshatraService(NakshatraRepository nakshatraRepository) {
        this.nakshatraRepository = nakshatraRepository;
    }

    /**
     * Retrieves all Nakshatras. This operation is read-only.
     * @return A list of all Nakshatra entities.
     */
    @Transactional(readOnly = true)
    public List<Nakshatra> getAllNakshatras() {
        return nakshatraRepository.findAll();
    }

    /**
     * Finds a Nakshatra by its unique ID. This operation is read-only.
     * @param id The ID of the nakshatra to find.
     * @return An Optional<Nakshatra> which is empty if not found.
     */
    @Transactional(readOnly = true)
    public Optional<Nakshatra> getNakshatraById(int id) {
        return nakshatraRepository.findById(id);
    }

    @Transactional(readOnly = true) // 2. Add Read-Only Transaction
    public Optional<String> getNakshatraNameByNakshatraId(int nakshatraId) {
        return nakshatraRepository.findNakshatraNameByNakshatraId(nakshatraId);
    }
}