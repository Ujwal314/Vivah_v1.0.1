package com.example.controller;

import com.example.entity.Media;
import com.example.service.MediaService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserService userService;

    // upload photos for the user
    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> uploadPhoto(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file
    ) {
        return userService.getUserById(userId)
                .map(user -> {
                    try {
                        Media media = Media.builder()
                                .user(user)
                                .photo(file.getBytes())
                                .build();
                        return ResponseEntity.ok(mediaService.uploadMedia(media));
                    } catch (IOException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // ✅ Get all media of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Media>> getPhotosByUser(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(mediaService.getMediaByUser(user)))
                .orElseGet(() -> ResponseEntity.notFound().build()); // ✅ FIXED
    }

    // ✅ Get photo by ID (returns raw image bytes)
    @GetMapping("/{photoId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long photoId) {
        return mediaService.getMediaById(photoId)
                .map(media -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // ⚠️ assumes JPEG
                        .body(media.getPhoto()))
                .orElseGet(() -> ResponseEntity.notFound().build()); // ✅ FIXED
    }

    // ✅ Delete photo
    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long photoId) {
        mediaService.deleteMedia(photoId);
        return ResponseEntity.noContent().build();
    }
}
