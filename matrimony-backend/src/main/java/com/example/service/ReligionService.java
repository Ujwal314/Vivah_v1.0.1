package com.example.service;
import com.example.entity.Religion;
import com.example.repository.ReligionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class ReligionService {
    private final ReligionRepository religionRepository;

    public ReligionService(ReligionRepository religionRepository) {
        this.religionRepository = religionRepository;
    }

    public Religion addReligion(Religion religion) {
        return religionRepository.save(religion);
    }

    public List<Religion> getAllReligions() {
        return religionRepository.findAll();
    }
}
