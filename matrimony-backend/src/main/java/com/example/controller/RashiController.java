package com.example.controller;

import com.example.entity.Rashi;
import com.example.service.RashiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Rashi API", description = "Endpoints for managing Rashi master data")
@RestController
@RequestMapping("/api/rashis")
public class RashiController {

    private final RashiService rashiService; // Now depends on the service

    public RashiController(RashiService rashiService){
        this.rashiService = rashiService;
    }

    @Operation(summary = "Get all Rashis", description = "Returns a complete list of all available Rashis.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of rashis",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Rashi.class)))
    @GetMapping
    public ResponseEntity<List<Rashi>> getAllRashi(){
        return ResponseEntity.ok(rashiService.getAllRashi());
    }

    @Operation(summary = "Get a Rashi by its ID", description = "Fetches a single Rashi object based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Rashi",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rashi.class)) }),
            @ApiResponse(responseCode = "404", description = "Rashi not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{rashiId}")
    public ResponseEntity<Rashi> getRashiById(
            @Parameter(description = "ID of the Rashi to retrieve", required = true)
            @PathVariable int rashiId){
        return rashiService.getRashiById(rashiId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get a Rashi's name by its ID", description = "Fetches only the name (as a String) of a single Rashi.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Rashi's name",
                    content = { @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class, example = "Mesha")) }),
            @ApiResponse(responseCode = "404", description = "Rashi not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}/name") // A clear, RESTful path for this specific data
    public ResponseEntity<String> getRashiNameById(@PathVariable int id) {
        Optional<Rashi> rashiOptional = rashiService.getRashiById(id);

        if (rashiOptional.isPresent()) {
            // Return the casteName as a plain string in the response body
            return ResponseEntity.ok(rashiOptional.get().getRashiName());
        } else {
            // If caste not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}