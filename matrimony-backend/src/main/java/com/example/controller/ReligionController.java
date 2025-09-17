package com.example.controller;

import com.example.entity.Rashi;
import com.example.entity.Religion;
import com.example.service.ReligionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "Religion API", description = "Endpoints for managing Religion master data")
@RestController
@RequestMapping("/api/religions")
public class ReligionController {

    private final ReligionService religionService;

    public ReligionController(ReligionService religionService) {
        this.religionService = religionService;
    }

    @Operation(summary = "Create a new Religion", description = "Adds a new Religion to the database. The religion name must be unique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Religion created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Religion.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input, e.g., missing religion name", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict: A religion with this name already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Religion> addReligion(@RequestBody Religion religion) {
        Religion savedReligion = religionService.addReligion(religion);

        // Create the location URI of the new resource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedReligion.getReligionId())
                .toUri();

        // Return 201 Created with the location header and the created object in the body
        return ResponseEntity.created(location).body(savedReligion);
    }

    @Operation(summary = "Get all Religions", description = "Returns a complete list of all available Religions.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of religions")
    @GetMapping
    public ResponseEntity<List<Religion>> getAllReligions() {
        return ResponseEntity.ok(religionService.getAllReligions());
    }

    // --- ADD THIS METHOD FIRST ---
    @Operation(summary = "Get a Religion by its ID", description = "Fetches the full Religion object based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Religion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Religion.class)) }),
            @ApiResponse(responseCode = "404", description = "Religion not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Religion> getReligionById(
            @Parameter(description = "ID of the Religion to retrieve", required = true)
            @PathVariable int id) {
        // You will need to add the corresponding getReligionById method to your ReligionService
        return religionService.getReligionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // --- THEN ADD THIS METHOD ---
    @Operation(summary = "Get a Religion's name by its ID", description = "Fetches only the name (as a String) of a single Religion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Religion's name",
                    content = { @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class, example = "Hinduism")) }),
            @ApiResponse(responseCode = "404", description = "Religion not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getReligionNameById(
            @Parameter(description = "ID of the Religion whose name is to be retrieved", required = true)
            @PathVariable int id) {
        Optional<Religion> rashiOptional = religionService.getReligionById(id);

        if (rashiOptional.isPresent()) {
            // Return the casteName as a plain string in the response body
            return ResponseEntity.ok(rashiOptional.get().getReligionName());
        } else {
            // If caste not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}