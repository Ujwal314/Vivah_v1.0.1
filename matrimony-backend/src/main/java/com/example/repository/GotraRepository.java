package com.example.repository;

import com.example.entity.Gotra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Added for convention and exception translation
public interface GotraRepository extends JpaRepository<Gotra, Integer> {
    // You can add custom queries here later if needed
    Optional<String> findGotraNameByGotraId(int gotraid);
}