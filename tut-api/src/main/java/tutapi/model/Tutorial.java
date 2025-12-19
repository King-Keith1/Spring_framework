package tutapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tutorial model")
public class Tutorial {

    @Schema(description = "ID of the tutorial", example = "1")
    private Long id;

    @Schema(description = "Title of the tutorial", example = "Spring Boot + Swagger")
    private String title;

    @Schema(description = "Description of the tutorial",
            example = "How to integrate Swagger with Spring Boot")
    private String description;

    @Schema(description = "Published flag", example = "true")
    private boolean published;

    public Tutorial() {}

    public Tutorial(Long id, String title, String description, boolean published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }
}
