package eldenmessages.config;

// OpenAPI main configuration model (root object for API docs)
import io.swagger.v3.oas.models.OpenAPI;
// Container for reusable OpenAPI components (schemas, security schemes, etc.)
import io.swagger.v3.oas.models.Components;
// Describes a security scheme (e.g., HTTP basic, bearer token)
import io.swagger.v3.oas.models.security.SecurityScheme;
// Describes security requirements applied to operations or the whole API
import io.swagger.v3.oas.models.security.SecurityRequirement;
// Marks a method as a Spring bean definition
import org.springframework.context.annotation.Bean;
// Marks this class as a Spring configuration class
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }
}

