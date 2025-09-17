package com.example.repository;

import com.example.entity.Caste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // 1. Add @Repository Annotation
import java.util.List;
import java.util.Optional;

@Repository // 1. Add @Repository Annotation
public interface CasteRepository extends JpaRepository<Caste, Integer> {

    // Assumes Religion entity has a "religionId" field
    List<Caste> findByReligionReligionId(int religionId);

    // Assumes Caste entity has "casteName" and "casteId" fields
    Optional<String> findCasteNameByCasteId(int casteId);
}