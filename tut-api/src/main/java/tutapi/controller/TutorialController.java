package tutapi.controller;

import tutapi.model.Tutorial; // adjust package to wherever your Tutorial class is

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import Tutorial from wherever it is
// import tutapi.model.Tutorial;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Tutorial", description = "Tutorial management APIs")
@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    @Operation(summary = "Retrieve a Tutorial by Id", description = "Get a Tutorial object by specifying its id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Tutorial.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {
        // Example implementation
        Tutorial tutorial = findTutorialById(id); // your service / repository call
        if (tutorial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tutorial);
    }

    private Tutorial findTutorialById(Long id) {
        // dummy impl; replace with your real data access
        return null;
    }

    // other endpoints ...
}

