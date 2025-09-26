package com.example.Repositories;

import com.example.entity.FamilyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyDetailsRepository extends JpaRepository<FamilyDetails, Long> {
    Optional<FamilyDetails> findByUser_UserId(Long userId);
    List<FamilyDetails> findAllByUser_UserIdIn(List<Long> userIds);// custom finder by User ID
}
