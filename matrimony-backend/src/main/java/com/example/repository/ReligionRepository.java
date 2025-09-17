package com.example.repository;

import com.example.entity.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Marks this as a data access component
public interface ReligionRepository extends JpaRepository<Religion, Integer> {

    // New method to find a religion by name, ignoring case.
    // This is essential for preventing duplicates like "Hindu" and "hindu".
    Optional<Religion> findByReligionNameIgnoreCase(String religionName);

    Optional<String> findReligionNameByReligionId(int religionid);

}