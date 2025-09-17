package com.example.controller;

import com.example.entity.Nakshatra;
import com.example.entity.Rashi;
import com.example.service.NakshatraService;
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

@Tag(name = "Nakshatra API", description = "Endpoints for managing Nakshatra master data")
@RestController
@RequestMapping("/api/nakshatras")
// WARNING: Insecure for production. Specify your frontend URL.
@CrossOrigin(origins = "*")
public class NakshatraController {

    private final NakshatraService nakshatraService; // Now depends on the Service

    public NakshatraController(NakshatraService nakshatraService) {
        this.nakshatraService = nakshatraService;
    }

    @Operation(summary = "Get all Nakshatras", description = "Returns a complete list of all available Nakshatras.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list")
    @GetMapping
    public ResponseEntity<List<Nakshatra>> getAllNakshatras() {
        return ResponseEntity.ok(nakshatraService.getAllNakshatras());
    }

    @Operation(summary = "Get a Nakshatra by its ID", description = "Fetches a single Nakshatra based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Nakshatra",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Nakshatra.class)) }),
            @ApiResponse(responseCode = "404", description = "Nakshatra not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Nakshatra> getNakshatraById(@PathVariable int id) {
        return nakshatraService.getNakshatraById(id)
                .map(ResponseEntity::ok) // If found, wrap in 200 OK
                .orElse(ResponseEntity.notFound().build()); // If not found, return a proper 404
    }

    @Operation(summary = "Get a Nakshatra's name by its ID", description = "Fetches only the name (as a String) of a single Nakshatra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Nakshatra's name",
                    content = { @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class, example = "Ashwini")) }),
            @ApiResponse(responseCode = "404", description = "Nakshatra not found with the given ID",
                    content = @Content)
    })
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getNakshatraNameById(
            @Parameter(description = "ID of the Nakshatra whose name is to be retrieved", required = true)
            @PathVariable int id) {
        Optional<Nakshatra> rashiOptional = nakshatraService.getNakshatraById(id);

        if (rashiOptional.isPresent()) {
            // Return the casteName as a plain string in the response body
            return ResponseEntity.ok(rashiOptional.get().getNakshatraName());
        } else {
            // If caste not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}