package com.example.repository;

import com.example.entity.Caste;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CasteRepository extends JpaRepository<Caste, Integer> {
    List<Caste> findByReligionReligionId(int religionId);
}
