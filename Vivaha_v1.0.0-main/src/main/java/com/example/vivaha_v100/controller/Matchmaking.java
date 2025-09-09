package com.example.vivaha_v100.controller;

import com.example.vivaha_v100.dto.FilterCriteria;
import com.example.vivaha_v100.dto.MatchDTO;
import com.example.vivaha_v100.dto.ProfileDTO;
import com.example.vivaha_v100.service.FilterService;
import com.example.vivaha_v100.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/match")
public class Matchmaking {
    @Autowired
    private FilterService filterService;

    @GetMapping("/find")
    public List<Map.Entry<ProfileDTO, Integer>> find(@RequestParam int rashi_id,
                                                     @RequestParam int nakshatra_id,
                                                     @RequestParam boolean gender,
                                                     @ModelAttribute FilterCriteria criteria) {
        MatchDTO matchDTO = new MatchDTO(gender, rashi_id, nakshatra_id);
        int[][] matchedRows = MatchService.getMatchedRows(matchDTO);

        Map<ProfileDTO, Integer> results = new HashMap<>();
        List<ProfileDTO> allProfiles = ProfileGenerator.generateProfiles(100);

        for (int[] row : matchedRows) {
            int rashiId = row[0];
            int nakshatraId = row[1];
            int value = row[2];

            for (ProfileDTO profile : allProfiles) {
                if (profile.getRashiId() != null && profile.getNakshatraId() != null &&
                        profile.getRashiId() == rashiId &&
                        profile.getNakshatraId() == nakshatraId &&
                        profile.isGender() != gender) {   // ✅ opposite gender check

                    results.put(profile, value);
                }
            }
        }

        // ✅ Convert to list and sort by value (descending)
        List<Map.Entry<ProfileDTO, Integer>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // ✅ If filter criteria present, filter corresponding profiles
        if (criteria != null) {
            List<ProfileDTO> filteredProfiles = filterService.filterProfiles(new ArrayList<>(results.keySet()), criteria);

            // Keep only filtered entries
            sortedResults.removeIf(entry ->
                    filteredProfiles.stream().noneMatch(p -> p.getProfileId().equals(entry.getKey().getProfileId())));
        }

        return sortedResults;
    }
}
