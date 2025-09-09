package com.example.repository;
import com.example.entity.Religion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReligionRepository extends JpaRepository<Religion, Integer>{
}
