package com.example.controller;

import com.example.entity.User;
import com.example.entity.UserProfile;
import com.example.Repositories.UserProfileRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserService userService;

    // ✅ Create or Update a User Profile
    @PostMapping("/{userId}")
    public ResponseEntity<UserProfile> createOrUpdateProfile(
            @PathVariable Long userId,
            @RequestBody UserProfile profileRequest
    ) {
        Optional<User> userOpt = userService.getUserById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();

        // find by user reference, not by ID
        Optional<UserProfile> existingProfile = userProfileRepository.findById(profileRequest.getProfileId());

        UserProfile profile = existingProfile.orElse(new UserProfile());
        profile.setUser(user);

        // copy fields from request
        profile.setAge(profileRequest.getAge());
        profile.setHeight(profileRequest.getHeight());
        profile.setWeight(profileRequest.getWeight());
        profile.setSalaryPackage(profileRequest.getSalaryPackage());
        profile.setJobLocation(profileRequest.getJobLocation());
        profile.setEducation(profileRequest.getEducation());
        profile.setOccupation(profileRequest.getOccupation());
        profile.setMangalik(profileRequest.getMangalik());
        profile.setDisability(profileRequest.getDisability());
        profile.setDisablityType(profileRequest.getDisablityType());
        profile.setBloodGroup(profileRequest.getBloodGroup());
        profile.setGender(profileRequest.getGender());
        profile.setMaritalStatus(profileRequest.getMaritalStatus());
        profile.setRashiId(profileRequest.getRashiId());
        profile.setNakshatraId(profileRequest.getNakshatraId());
        profile.setGotraId(profileRequest.getGotraId());
        profile.setPaada(profileRequest.getPaada());
        profile.setCasteId(profileRequest.getCasteId());
        profile.setSubcasteId(profileRequest.getSubcasteId());
        profile.setReligionId(profileRequest.getReligionId());

        return ResponseEntity.ok(userProfileRepository.save(profile));
    }

    // ✅ Get Profile by Profile ID
    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfile> getProfileById(@PathVariable Long profileId) {
        return userProfileRepository.findById(profileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get Profile by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfile> getProfileByUserId(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(User::getUserProfile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Profile
    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long profileId) {
        if (userProfileRepository.existsById(profileId)) {
            userProfileRepository.deleteById(profileId);
            return ResponseEntity.ok("Profile deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
