package com.example.controller;

import com.example.entity.Caste;
import com.example.entity.Rashi;
import com.example.service.CasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "Caste API", description = "Endpoints for managing Caste master data")
@RestController
@RequestMapping("/api/castes")
public class CasteController {

    private final CasteService casteService;

    public CasteController(CasteService casteService) {
        this.casteService = casteService;
    }


    @Operation(summary = "Create a new Caste", description = "Adds a new Caste to the database. The caste name must be unique.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Caste created successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Caste.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input, e.g., missing caste name", content = @Content),
        @ApiResponse(responseCode = "409", description = "Conflict, a caste with this name already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Caste> createCaste(@RequestBody Caste caste) {
        // Assuming createCaste service method handles logic and might throw exceptions
        Caste createdCaste = casteService.createCaste(caste);
        return ResponseEntity.created(URI.create("/api/castes/" + createdCaste.getCasteId())).body(createdCaste);
    }


    @Operation(summary = "Get all Castes", description = "Returns a complete list of all available Castes.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of castes",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Caste.class)))
    @GetMapping
    public ResponseEntity<List<Caste>> getAllCastes() {
        return ResponseEntity.ok(casteService.getAllCastes());
    }

    @Operation(summary = "Get a Caste by its ID", description = "Fetches the full Caste object based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Caste",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caste.class)) }),
            @ApiResponse(responseCode = "404", description = "Caste not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Caste> getCasteById(
            @Parameter(description = "ID of the Caste to retrieve", required = true)
            @PathVariable int id) {
        return casteService.getCasteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get a Caste's name by its ID", description = "Fetches only the name (as a String) of a single Caste.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Caste's name",
                    content = { @Content(mediaType = "text/plain", // A raw string is better described as text/plain
                            schema = @Schema(implementation = String.class, example = "Brahmin")) }),
            @ApiResponse(responseCode = "404", description = "Caste not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getCasteNameById(
            @Parameter(description = "ID of the Caste whose name is to be retrieved", required = true)
            @PathVariable int id) {
//        return casteService.getCasteNameByCasteId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
        Optional<Caste> rashiOptional = casteService.getCasteById(id);

        if (rashiOptional.isPresent()) {
            // Return the casteName as a plain string in the response body
            return ResponseEntity.ok(rashiOptional.get().getCasteName());
        } else {
            // If caste not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get Castes by Religion ID", description = "Returns a list of all Castes that are associated with a specific Religion.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list. The list will be empty if no castes match the religion ID.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Caste.class)))
    @GetMapping("/by-religion/{religionId}")
    public ResponseEntity<List<Caste>> getCastesByReligionId(
            @Parameter(description = "The ID of the Religion to filter Castes by", required = true)
            @PathVariable int religionId) {
        return ResponseEntity.ok(casteService.getCastesByReligionId(religionId));
    }
}