package com.example.repository;

import com.example.entity.Rashi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Marks this interface as a Spring Data repository
public interface RashiRepository extends JpaRepository<Rashi, Integer> {
    Optional<String> findRashiNameByRashiId(int rashiid);

}