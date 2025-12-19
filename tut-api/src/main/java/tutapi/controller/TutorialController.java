package tutapi.controller;

import tutapi.model.Tutorial;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

@Tag(name = "Tutorial", description = "Tutorial management APIs")
@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    // In-memory storage (demo only)
    private final Map<Long, Tutorial> store = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(2);

    public TutorialController() {
        store.put(1L, new Tutorial(1L, "Intro to Spring Boot",
                "Basics of Spring Boot", true));
        store.put(2L, new Tutorial(2L, "Swagger Integration",
                "Documenting REST API with Swagger/OpenAPI", true));
    }

    // -------------------- GET --------------------

    @Operation(
            summary = "Get tutorial by ID",
            description = "Retrieve a tutorial using its unique ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tutorial found",
                    content = @Content(schema = @Schema(implementation = Tutorial.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tutorial not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {

        Tutorial tutorial = store.get(id);

        if (tutorial == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tutorial);
    }

    // -------------------- POST --------------------

    @Operation(
            summary = "Create a new tutorial",
            description = "Add a new tutorial using request body"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tutorial created",
                    content = @Content(schema = @Schema(implementation = Tutorial.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input"
            )
    })
    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(
            @RequestBody Tutorial tutorial) {

        // Generate ID for demo
        Long id = idGenerator.incrementAndGet();
        tutorial.setId(id);

        store.put(id, tutorial);

        return ResponseEntity.status(201).body(tutorial);
    }
}
