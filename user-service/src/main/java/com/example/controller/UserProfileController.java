package com.example.controller;

import com.example.entity.User;
import com.example.entity.UserProfile;
import com.example.Repositories.UserProfileRepository;
import com.example.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserService userService;

    // ✅ Create or Update a User Profile
//    @PostMapping("/{userId}")
//    public ResponseEntity<UserProfile> createOrUpdateProfile(
//            @PathVariable Long userId,
//            @RequestBody UserProfile profileRequest
//    ) {
//        Optional<User> userOpt = userService.getUserById(userId);
//
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        User user = userOpt.get();
//
//        // find by user reference, not by ID
//        Optional<UserProfile> existingProfile = userProfileRepository.findById(profileRequest.getProfileId());
//
//        UserProfile profile = existingProfile.orElse(new UserProfile());
//        profile.setUser(user);
//
//        // copy fields from request
//        profile.setAge(profileRequest.getAge());
//        profile.setHeight(profileRequest.getHeight());
//        profile.setWeight(profileRequest.getWeight());
//        profile.setSalaryPackage(profileRequest.getSalaryPackage());
//        profile.setJobLocation(profileRequest.getJobLocation());
//        profile.setEducation(profileRequest.getEducation());
//        profile.setOccupation(profileRequest.getOccupation());
//        profile.setMangalik(profileRequest.getMangalik());
//        profile.setDisability(profileRequest.getDisability());
//        profile.setDisablityType(profileRequest.getDisablityType());
//        profile.setBloodGroup(profileRequest.getBloodGroup());
//        profile.setGender(profileRequest.getGender());
//        profile.setMaritalStatus(profileRequest.getMaritalStatus());
//        profile.setRashiId(profileRequest.getRashiId());
//        profile.setNakshatraId(profileRequest.getNakshatraId());
//        profile.setGotraId(profileRequest.getGotraId());
//        profile.setPaada(profileRequest.getPaada());
//        profile.setCasteId(profileRequest.getCasteId());
//        profile.setSubcasteId(profileRequest.getSubcasteId());
//        profile.setReligionId(profileRequest.getReligionId());
//
//        return ResponseEntity.ok(userProfileRepository.save(profile));
//    }
    @GetMapping("/me")
    public ResponseEntity<UserProfile> getProfileOfCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        return ResponseEntity.ok(user.getUserProfile());
    }
    @PostMapping("/me")
    public ResponseEntity<UserProfile> createOrUpdateProfile(@RequestBody UserProfile profileRequest) {
        // Get logged-in user's email from the security context
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user entity
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Check if this user already has a profile
        UserProfile existingProfile = user.getUserProfile();

        UserProfile profile = (existingProfile != null) ? existingProfile : new UserProfile();

        // Set the user reference (IMPORTANT to establish bi-directional relationship)
        profile.setUser(user);
        user.setUserProfile(profile); // ✅ This line matches the error you had earlier

        // Copy fields from the request
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

        // Save profile
        UserProfile savedProfile = userProfileRepository.save(profile);

        return ResponseEntity.ok(savedProfile);
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfile> UpdateProfile(@RequestBody UserProfile profileRequest) {
        // Get logged-in user's email from the security context
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user entity
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Check if this user already has a profile
        UserProfile existingProfile = user.getUserProfile();

        UserProfile profile = (existingProfile != null) ? existingProfile : new UserProfile();

        // Set the user reference (IMPORTANT to establish bi-directional relationship)
        profile.setUser(user);
        user.setUserProfile(profile); // ✅ This line matches the error you had earlier

        // Copy fields from the request
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

        // Save profile
        UserProfile savedProfile = userProfileRepository.save(profile);

        return ResponseEntity.ok(savedProfile);
    }



    // ✅ Get Profile by Profile ID
    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfile> getProfileById(@PathVariable Long profileId) {

        return userProfileRepository.findById(profileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get all user profiles except the currently authenticated user's
    @GetMapping("/all")
    public ResponseEntity<?> getAllProfilesExceptCurrentUser() {
        // Get authenticated user's email
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Get the authenticated user entity
        User currentUser = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

        // Get the current user's profile (optional - may be null if not set)
        UserProfile currentProfile = currentUser.getUserProfile();

        // Fetch all profiles
        List<UserProfile> allProfiles = userProfileRepository.findAll();

        // Filter out the current user's profile
        List<UserProfile> filteredProfiles = allProfiles.stream()
                .filter(profile -> currentProfile == null || !profile.getProfileId().equals(currentProfile.getProfileId()))
                .toList();

        return ResponseEntity.ok(filteredProfiles);
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
