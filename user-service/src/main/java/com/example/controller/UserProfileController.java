package com.example.controller;

import com.example.Repositories.AddressRepository;
import com.example.Repositories.FamilyDetailsRepository;
import com.example.dto.AddressDTO;
import com.example.dto.FamilyDetailsDTO;
import com.example.dto.UserProfileDTO;
import com.example.entity.Address;
import com.example.entity.FamilyDetails;
import com.example.entity.User;
import com.example.entity.UserProfile;
import com.example.Repositories.UserProfileRepository;
import com.example.service.AddressService;
import com.example.service.FamilyDetailsService;
import com.example.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private FamilyDetailsRepository familyDetailsRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private FamilyDetailsService familyDetailsService;

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

//    @GetMapping("/test")
//    public ResponseEntity<?> test() {
//        List<UserProfile> allProfiles = userProfileRepository.findAll();
//        System.out.println(allProfiles);
//        List<UserProfileDTO> allProfileDTOs = allProfiles.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(allProfileDTOs);
//    }

    // ✅ Get all user profiles except the currently authenticated user's
    @GetMapping("/all")
    public ResponseEntity<?> getAllProfilesExceptCurrentUser() {
        // Get authenticated user's email
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Get the authenticated user entity
        User currentUser = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

        UserProfile currentProfile = currentUser.getUserProfile();

        // Fetch all profiles
        List<UserProfile> allProfiles = userProfileRepository.findAll();

        // Extract userIds from all profiles
        List<Long> userIds = allProfiles.stream()
                .map(p -> p.getUser().getUserId())
                .collect(Collectors.toList());

        // Batch fetch familyDetails and addresses for all userIds
        List<FamilyDetails> familyDetailsList = familyDetailsRepository.findAllByUser_UserIdIn(userIds);
        List<Address> addressList = addressRepository.findAllByUser_UserIdIn(userIds);

        // Convert lists to maps for fast lookup by userId
        Map<Long, FamilyDetails> familyDetailsMap = familyDetailsList.stream()
                .collect(Collectors.toMap(fd -> fd.getUser().getUserId(), fd -> fd));

        Map<Long, Address> addressMap = addressList.stream()
                .collect(Collectors.toMap(addr -> addr.getUser().getUserId(), addr -> addr));

        // Convert profiles to DTOs using the batch loaded maps
        List<UserProfileDTO> allProfileDTOs = allProfiles.stream()
                .map(profile -> convertToDto(profile, familyDetailsMap, addressMap))
                .collect(Collectors.toList());

        // Filter out current user's profile
        List<UserProfileDTO> filteredProfiles = allProfileDTOs.stream()
                .filter(profile -> currentProfile == null || !profile.getProfileId().equals(currentProfile.getProfileId()))
                .collect(Collectors.toList());

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

    private UserProfileDTO convertToDto(UserProfile userProfile,
                                        Map<Long, FamilyDetails> familyDetailsMap,
                                        Map<Long, Address> addressMap) {
        if (userProfile == null || userProfile.getUser() == null) {
            return null;
        }

        Long userId = userProfile.getUser().getUserId();

        UserProfileDTO.UserProfileDTOBuilder dtoBuilder = UserProfileDTO.builder()
                .profileId(userProfile.getProfileId())
                .userId(userId)
                .fname(userProfile.getUser().getFname())
                .lname(userProfile.getUser().getLname())
                .age(userProfile.getAge())
                .height(userProfile.getHeight())
                .weight(userProfile.getWeight())
                .salaryPackage(userProfile.getSalaryPackage())
                .jobLocation(userProfile.getJobLocation())
                .education(userProfile.getEducation())
                .occupation(userProfile.getOccupation())
                .mangalik(userProfile.getMangalik())
                .disability(userProfile.getDisability())
                .disablityType(userProfile.getDisablityType())
                .bloodGroup(userProfile.getBloodGroup())
                .gender(userProfile.getGender())
                .maritalStatus(userProfile.getMaritalStatus())
                .rashiId(userProfile.getRashiId())
                .nakshatraId(userProfile.getNakshatraId())
                .gotraId(userProfile.getGotraId())
                .paada(userProfile.getPaada())
                .casteId(userProfile.getCasteId())
                .religionId(userProfile.getReligionId());

        FamilyDetails familyDetails = familyDetailsMap.get(userId);
        if (familyDetails != null) {
            dtoBuilder.annualIncome(familyDetails.getAnnualIncome())
                    .siblingsCount(familyDetails.getSiblingsCount())
                    .fatherName(familyDetails.getFatherName())
                    .motherName(familyDetails.getMotherName());
        }

        Address address = addressMap.get(userId);
        if (address != null) {
            dtoBuilder.city(address.getCity())
                    .state(address.getState())
                    .country(address.getCountry())
                    .postalCode(address.getPostalCode());
        }

        return dtoBuilder.build();
    }

}
