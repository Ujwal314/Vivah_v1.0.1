package com.example.vivaha_v100.controller;

import com.example.vivaha_v100.feign.UserProfileServiceClient;
import com.example.vivaha_v100.dto.FilterCriteria;
import com.example.vivaha_v100.dto.MatchDTO;
import com.example.vivaha_v100.dto.ProfileDTO;
import com.example.vivaha_v100.service.FilterService;
import com.example.vivaha_v100.service.MatchService; // Ensure this is available
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/match")
public class Matchmaking {
    @Autowired
    private FilterService filterService;

    @Autowired
    private MatchService matchService; // Make sure this is autowired if it has state/logic

    @Autowired
    private UserProfileServiceClient userProfileServiceClient;

    // Existing /find endpoint (if you keep it for explicit parameter control)
    @GetMapping("/find")
    public List<Map.Entry<ProfileDTO, Integer>> find(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestParam int rashi_id,
            @RequestParam int nakshatra_id,
            @RequestParam boolean gender,
            @ModelAttribute FilterCriteria criteria) {

        // Delegate to a common helper method to avoid code duplication
        return performMatchmaking(authorizationHeader, rashi_id, nakshatra_id, gender, criteria);
    }

    // NEW: find-me endpoint
    @GetMapping("/find-me")
    public List<Map.Entry<ProfileDTO, Integer>> findForCurrentUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, // Required for security propagation
            @ModelAttribute FilterCriteria criteria) { // FilterCriteria is optional

        // 1. Fetch the authenticated user's profile
        ProfileDTO currentUserProfile = userProfileServiceClient.getProfileOfCurrentUser();

        // 2. Validate essential matchmaking data in current user's profile
        if (currentUserProfile == null ||
                currentUserProfile.getRashiId() == null ||
                currentUserProfile.getNakshatraId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Current user's profile is incomplete for matchmaking. Rashi, Nakshatra, and Gender are required."
            );
        }

        // 3. Extract the required parameters from the current user's profile
        int currentUserRashiId = currentUserProfile.getRashiId();
        int currentUserNakshatraId = currentUserProfile.getNakshatraId();
        // Assuming gender is a Boolean and represents the user's own gender
        boolean currentUserGender = currentUserProfile.isGender();

        // 4. Delegate to the common matchmaking logic
        return performMatchmaking(authorizationHeader, currentUserRashiId, currentUserNakshatraId, currentUserGender, criteria);
    }


    // --- Common Matchmaking Logic (extracted to avoid duplication) ---
    private List<Map.Entry<ProfileDTO, Integer>> performMatchmaking(
            String authorizationHeader,
            int userRashiId,
            int userNakshatraId,
            boolean userGender,
            FilterCriteria criteria) {

        MatchDTO matchDTO = new MatchDTO(userGender, userRashiId, userNakshatraId);
        int[][] matchedRows = MatchService.getMatchedRows(matchDTO); // Calling static method from MatchService

        // Fetch all profiles *excluding* the current user's profile
        List<ProfileDTO> allProfiles = userProfileServiceClient.getAllProfilesExceptCurrentUser(); // JWT propagated by Feign interceptor or @RequestHeader

        Map<ProfileDTO, Integer> results = new HashMap<>();

        for (int[] row : matchedRows) {
            int targetRashiId = row[0];
            int targetNakshatraId = row[1];
            int value = row[2];

            for (ProfileDTO profile : allProfiles) {
                // Ensure profile data is not null before comparison
                if (profile.getRashiId() != null && profile.getNakshatraId() != null &&
                        profile.getRashiId().equals(targetRashiId) &&
                        profile.getNakshatraId().equals(targetNakshatraId) && profile.isGender() != userGender) { // Match opposite gender
                    results.put(profile, value);
                }
            }
        }

        List<Map.Entry<ProfileDTO, Integer>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        if (criteria != null) {
            List<ProfileDTO> filteredProfiles = filterService.filterProfiles(new ArrayList<>(results.keySet()), criteria);

            Set<Integer> filteredProfileIds = filteredProfiles.stream()
                    .map(ProfileDTO::getProfileId)
                    .collect(Collectors.toSet());

            sortedResults.removeIf(entry -> !filteredProfileIds.contains(entry.getKey().getProfileId()));
        }

        return sortedResults;
    }
}