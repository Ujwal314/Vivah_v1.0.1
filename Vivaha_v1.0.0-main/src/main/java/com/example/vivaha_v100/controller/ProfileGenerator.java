package com.example.vivaha_v100.controller;

import com.example.vivaha_v100.dto.ProfileDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProfileGenerator {

    private static final String[] LOCATIONS = {"Bangalore", "Chennai", "Hyderabad", "Pune", "Delhi", "Mumbai"};
    private static final String[] EDUCATIONS = {"B.Tech", "M.Tech", "MBA", "B.Sc", "M.Sc", "PhD"};
    private static final String[] OCCUPATIONS = {"Software Engineer", "Doctor", "Teacher", "Business Analyst", "Lawyer", "Manager"};
    private static final String[] BLOOD_GROUPS = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private static final String[] MARITAL_STATUSES = {"Single", "Divorced", "Widowed"};

    private static final Random RANDOM = new Random();

    public static List<ProfileDTO> generateProfiles(int count) {
        List<ProfileDTO> profiles = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            ProfileDTO profile = new ProfileDTO(
                    i, // profileId
                    1000 + i, // userId
                    20 + RANDOM.nextInt(15), // age 20–35
                    RANDOM.nextBoolean(), // gender (true = male, false = female for example)
                    MARITAL_STATUSES[RANDOM.nextInt(MARITAL_STATUSES.length)], // maritalStatus
                    150 + RANDOM.nextDouble() * 40, // height 150–190 cm
                    45 + RANDOM.nextDouble() * 55, // weight 45–100 kg
                    300000 + RANDOM.nextDouble() * 1200000, // salary 3L–15L
                    LOCATIONS[RANDOM.nextInt(LOCATIONS.length)], // jobLocation
                    EDUCATIONS[RANDOM.nextInt(EDUCATIONS.length)], // education
                    OCCUPATIONS[RANDOM.nextInt(OCCUPATIONS.length)], // occupation
                    RANDOM.nextBoolean(), // mangalik
                    RANDOM.nextBoolean(), // disability
                    RANDOM.nextBoolean() ? "None" : "Visual", // disabilityType
                    BLOOD_GROUPS[RANDOM.nextInt(BLOOD_GROUPS.length)], // bloodGroup
                    1 + RANDOM.nextInt(12), // rashiId (1–12)
                    1 + RANDOM.nextInt(27), // nakshatraId (1–27)
                    1 + RANDOM.nextInt(10), // gotraId
                    1 + RANDOM.nextInt(4), // paada (1–4)
                    1 + RANDOM.nextInt(20), // casteId
                    1 + RANDOM.nextInt(5) // religionId
            );

            profiles.add(profile);
        }

        return profiles;
    }
}
