package com.example.controller;

import com.example.entity.Gotra;
import com.example.entity.Rashi;
import com.example.service.GotraService; // Import the service
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gotras")
// WARNING: This is insecure for production. Specify your frontend URL instead.
@CrossOrigin(origins = "*")
public class GotraController {

    private final GotraService gotraService; // Changed from Repository to Service

    // Inject the service instead of the repository
    public GotraController(GotraService gotraService) {
        this.gotraService = gotraService;
    }

    // Get all Gotras
    @GetMapping
    public ResponseEntity<List<Gotra>> getAllGotras() {
        List<Gotra> gotras = gotraService.getAllGotras();
        return ResponseEntity.ok(gotras);
    }

    // Get Gotra by ID
    @GetMapping("/{id}")
    public ResponseEntity<Gotra> getGotraById(@PathVariable int id) {
        // This is the correct way to handle "Not Found" cases in a REST API
        return gotraService.getGotraById(id)
                .map(ResponseEntity::ok) // If found, return 200 OK with the object
                .orElse(ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }

    @Operation(summary = "Get a Gotra's name by its ID", description = "Fetches only the name (as a String) of a single Gotra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Gotra's name",
                    content = { @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class, example = "Bharadwaja")) }),
            @ApiResponse(responseCode = "404", description = "Gotra not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getGotraNameById(
            @Parameter(description = "ID of the Gotra whose name is to be retrieved", required = true)
            @PathVariable int id) {
        // Assumes you have a getGotraNameById(id) method in your GotraService
        Optional<Gotra> rashiOptional = gotraService.getGotraById(id);

        if (rashiOptional.isPresent()) {
            // Return the casteName as a plain string in the response body
            return ResponseEntity.ok(rashiOptional.get().getGotraName());
        } else {
            // If caste not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}