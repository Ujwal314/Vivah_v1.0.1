package com.example.vivaha_v100.feign;// com.example.vivaha_v100.client.UserProfileServiceClient

import com.example.vivaha_v100.feign.FeignClientConfiguration; // Import the config
import com.example.vivaha_v100.dto.ProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Add 'configuration = FeignClientConfiguration.class'
@FeignClient(name = "user-service", path = "/api/user-profiles", configuration = FeignClientConfiguration.class)
public interface UserProfileServiceClient {

    // Now you DON'T need @RequestHeader("Authorization") here anymore
    @GetMapping("/all")
    List<ProfileDTO> getAllProfilesExceptCurrentUser();

    @GetMapping("/me")
    ProfileDTO getProfileOfCurrentUser();
}