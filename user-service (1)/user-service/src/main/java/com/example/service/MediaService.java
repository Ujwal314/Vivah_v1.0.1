package com.example.service;

import com.example.entity.Media;
import com.example.entity.User;
import com.example.Repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public Media uploadMedia(Media media) {
        return mediaRepository.save(media);
    }

    public List<Media> getMediaByUser(User user) {
        return mediaRepository.findByUser(user);
    }

    public Optional<Media> getMediaById(Long id) {
        return mediaRepository.findById(id);  // ✅ This was missing
    }

    public void deleteMedia(Long id) {
        mediaRepository.deleteById(id);
    }
}
