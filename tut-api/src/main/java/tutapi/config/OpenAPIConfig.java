package tutapi.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration // Marks this as a Spring config class
public class OpenAPIConfig {

    // URLs loaded from application.properties
    @Value("${app.openapi.dev-url}")
    private String devUrl;

    @Value("${app.openapi.prod-url}")
    private String prodUrl;

    @Bean // Exposes OpenAPI config as a Spring Bean
    public OpenAPI customOpenAPI() {

        // Server definitions shown in Swagger UI
        Server devServer = new Server().url(devUrl).description("Development server");
        Server prodServer = new Server().url(prodUrl).description("Production server");

        // API contact details
        Contact contact = new Contact()
                .name("Your Name")
                .email("you@example.com")
                .url("https://your-site");

        // API license info
        License mit = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        // General API metadata
        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .description("APIs to manage tutorials")
                .contact(contact)
                .license(mit);

        // Final OpenAPI object
        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}
