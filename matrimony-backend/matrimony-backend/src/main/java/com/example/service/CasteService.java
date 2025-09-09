package com.example.service;

import com.example.entity.Caste;
import com.example.repository.CasteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasteService {
    private final CasteRepository casteRepository;

    public CasteService(CasteRepository casteRepository) {
        this.casteRepository = casteRepository;
    }

    public Caste createCaste(Caste caste) {
        return casteRepository.save(caste);
    }

    public List<Caste> getAllCastes() {
        return casteRepository.findAll();
    }

    public Optional<Caste> getCasteById(int id) {
        return casteRepository.findById(id);
    }

    public List<Caste> getCastesByReligionEntity(int religionId) {
        return casteRepository.findByReligionReligionId(religionId);
    }

}
