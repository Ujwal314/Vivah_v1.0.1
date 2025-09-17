package com.example.repository;

import com.example.entity.Nakshatra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Marks this as a data access component
public interface NakshatraRepository extends JpaRepository<Nakshatra, Integer> {
    Optional<String> findNakshatraNameByNakshatraId(int nakshatraid);

}