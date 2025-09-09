package com.example.service;

import com.example.entity.UserProfile;
import com.example.Repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }

    public Optional<UserProfile> getProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile updateProfile(Long id, UserProfile updatedProfile) {
        return userProfileRepository.findById(id).map(profile -> {
            profile.setAge(updatedProfile.getAge());
            profile.setHeight(updatedProfile.getHeight());
            profile.setWeight(updatedProfile.getWeight());
            profile.setSalaryPackage(updatedProfile.getSalaryPackage());
            profile.setJobLocation(updatedProfile.getJobLocation());
            profile.setEducation(updatedProfile.getEducation());
            profile.setOccupation(updatedProfile.getOccupation());
            profile.setMangalik(updatedProfile.getMangalik());
            profile.setDisability(updatedProfile.getDisability());
            profile.setDisablityType(updatedProfile.getDisablityType());
            profile.setBloodGroup(updatedProfile.getBloodGroup());
            profile.setGender(updatedProfile.getGender());
            profile.setMaritalStatus(updatedProfile.getMaritalStatus());

            profile.setRashiId(updatedProfile.getRashiId());
            profile.setNakshatraId(updatedProfile.getNakshatraId());
            profile.setGotraId(updatedProfile.getGotraId());
            profile.setPaada(updatedProfile.getPaada());
            profile.setCasteId(updatedProfile.getCasteId());
            profile.setReligionId(updatedProfile.getReligionId());
            return userProfileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Profile not found with id " + id));
    }
}
