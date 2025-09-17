package com.example.vivaha_v100.controller;

import com.example.vivaha_v100.feign.LookupServiceClient;
import com.example.vivaha_v100.feign.UserProfileServiceClient;
import com.example.vivaha_v100.dto.CardDTO; // Import CardDTO
import com.example.vivaha_v100.dto.FilterCriteria;
import com.example.vivaha_v100.dto.MatchDTO;
import com.example.vivaha_v100.dto.ProfileDTO;
import com.example.vivaha_v100.service.FilterService;
import com.example.vivaha_v100.service.MatchService;
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
    private MatchService matchService;

    @Autowired
    private UserProfileServiceClient userProfileServiceClient;

    @Autowired
    private LookupServiceClient lookupServiceClient; // Injected LookupServiceClient

    @GetMapping("/find")
    public List<Map.Entry<CardDTO, Integer>> find( // Changed return type to CardDTO
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                   @RequestParam int rashi_id,
                                                   @RequestParam int nakshatra_id,
                                                   @RequestParam boolean gender, // Assuming String gender based on previous discussion
                                                   @ModelAttribute FilterCriteria criteria) {

        return performMatchmaking(authorizationHeader, rashi_id, nakshatra_id, gender, criteria);
    }

    @GetMapping("/find-me")
    public List<Map.Entry<CardDTO, Integer>> findForCurrentUser( // Changed return type to CardDTO
                                                                 @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                                 @ModelAttribute FilterCriteria criteria) {

        ProfileDTO currentUserProfile = userProfileServiceClient.getProfileOfCurrentUser();

        if (currentUserProfile == null ||
                currentUserProfile.getRashiId() == null ||
                currentUserProfile.getNakshatraId() == null){ // Include gender check
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Current user's profile is incomplete for matchmaking. Rashi, Nakshatra, and Gender are required."
            );
        }

        int currentUserRashiId = currentUserProfile.getRashiId();
        int currentUserNakshatraId = currentUserProfile.getNakshatraId();
        boolean currentUserGender = currentUserProfile.isGender(); // Changed to String

        return performMatchmaking(authorizationHeader, currentUserRashiId, currentUserNakshatraId, currentUserGender, criteria);
    }

    // --- Common Matchmaking Logic ---
    // Changed return type to List<Map.Entry<CardDTO, Integer>>
    private List<Map.Entry<CardDTO, Integer>> performMatchmaking(
            String authorizationHeader,
            int userRashiId,
            int userNakshatraId,
            boolean userGender, // Changed to String
            FilterCriteria criteria) {

        MatchDTO matchDTO = new MatchDTO(userGender, userRashiId, userNakshatraId);
        int[][] matchedRows = MatchService.getMatchedRows(matchDTO);

        List<ProfileDTO> allProfiles = userProfileServiceClient.getAllProfilesExceptCurrentUser();

        Map<ProfileDTO, Integer> intermediateResults = new HashMap<>(); // Store ProfileDTO temporarily

        for (int[] row : matchedRows) {
            int targetRashiId = row[0];
            int targetNakshatraId = row[1];
            int value = row[2];

            for (ProfileDTO profile : allProfiles) {
                if (profile.getRashiId() != null && profile.getNakshatraId() != null &&
                        profile.getRashiId().equals(targetRashiId) &&
                        profile.getNakshatraId().equals(targetNakshatraId) && !profile.isGender()==(userGender)) { // Compare String genders
                    intermediateResults.put(profile, value);
                }
            }
        }

        List<Map.Entry<ProfileDTO, Integer>> sortedProfileResults = new ArrayList<>(intermediateResults.entrySet());
        sortedProfileResults.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Apply filter criteria to ProfileDTOs first
        List<ProfileDTO> filteredProfiles = new ArrayList<>(sortedProfileResults.stream().map(Map.Entry::getKey).toList());

        System.out.println(filteredProfiles);
        if (criteria != null) {
            filteredProfiles = filterService.filterProfiles(filteredProfiles, criteria);
        }

        // Now, map the filtered and sorted ProfileDTOs to CardDTOs and maintain scores
        List<Map.Entry<CardDTO, Integer>> finalCardResults = new ArrayList<>();
        for (Map.Entry<ProfileDTO, Integer> entry : sortedProfileResults) {
            ProfileDTO profile = entry.getKey();
            Integer score = entry.getValue();

            // Only include in final results if it passed the filter criteria
            if (filteredProfiles.stream().anyMatch(p -> p.getProfileId().equals(profile.getProfileId()))) {
                CardDTO card = convertProfileToCardDTO(profile); // Convert to CardDTO
                finalCardResults.add(new AbstractMap.SimpleEntry<>(card, score));
            }
        }

        return finalCardResults;
    }

    // --- Helper method to convert ProfileDTO to CardDTO ---
    private CardDTO convertProfileToCardDTO(ProfileDTO profile) {
        System.out.println(profile);
        CardDTO card = new CardDTO();

        // Copy direct fields
        card.setAge(profile.getAge());
        // For firstName/lastName, you'll need a way to fetch these from the User service
        // For now, they will be null/default as per your instruction to ignore.
        card.setFname(profile.getFname());
        card.setLname(profile.getLname());
        // card.setLname(...);
        card.setGender(profile.isGender()); // Convert String gender to boolean for CardDTO
        card.setMaritalStatus(profile.getMaritalStatus());
        card.setHeight(profile.getHeight());
        card.setWeight(profile.getWeight());
        card.setSalaryPackage(profile.getSalaryPackage());
        card.setJobLocation(profile.getJobLocation());
        card.setEducation(profile.getEducation());
        card.setOccupation(profile.getOccupation());
        card.setMangalik(profile.getMangalik() != null ? profile.getMangalik() : false); // Handle potential null
        card.setDisability(profile.getDisability() != null ? profile.getDisability() : false); // Handle potential null
        card.setDisabilityType(profile.getDisabilityType());
        card.setBloodGroup(profile.getBloodGroup());
        card.setPaada(profile.getPaada());

        card.setCity(profile.getCity());
        card.setPostalCode(profile.getPostalCode());
        card.setCountry(profile.getCountry());
        card.setState(profile.getState());
        card.setFatherName(profile.getFatherName());
        card.setMotherName(profile.getMotherName());
        card.setSiblingsCount(profile.getSiblingsCount());
        card.setAnnualIncome(profile.getAnnualIncome());

        // Fetch names using LookupServiceClient, handling potential null IDs
        if (profile.getRashiId() != null) {
            card.setRashiName(lookupServiceClient.getRashiName(profile.getRashiId()));
        }
        if (profile.getNakshatraId() != null) {
            card.setNakshatraName(lookupServiceClient.getNakshatraName(profile.getNakshatraId()));
        }
        if (profile.getGotraId() != null) {
            card.setGotraName(lookupServiceClient.getGotraName(profile.getGotraId()));
        }
        if (profile.getCasteId() != null) {
            card.setCasteName(lookupServiceClient.getCasteName(profile.getCasteId()));
        }
        if (profile.getReligionId() != null) {
            card.setReligionName(lookupServiceClient.getReligionName(profile.getReligionId()));
        }

        return card;
    }
}