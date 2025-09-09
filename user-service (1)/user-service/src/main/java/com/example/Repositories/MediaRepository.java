package com.example.Repositories;

import com.example.entity.Media;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    // Fetch all media of a particular user
    List<Media> findByUser(User user);
}
