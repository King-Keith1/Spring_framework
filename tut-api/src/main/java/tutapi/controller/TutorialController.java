package tutapi.controller;

import tutapi.model.Tutorial;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // simple in-memory "repo" for demo
    private final Map<Long, Tutorial> store = new LinkedHashMap<>();

    public TutorialController() {
        store.put(1L, new Tutorial(1L, "Intro to Spring Boot", "Basics of Spring Boot", true));
        store.put(2L, new Tutorial(2L, "Swagger Integration", "Documenting REST API with Swagger/OpenAPI", true));
    }

    @Operation(summary = "Retrieve a Tutorial by Id", description = "Get a Tutorial object by specifying its id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Tutorial.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {
        Tutorial tutorial = findTutorialById(id);
        if (tutorial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tutorial);
    }

    private Tutorial findTutorialById(Long id) {
        return store.get(id);
    }

    // other endpoints (create/update/delete) can be added similarly
}
