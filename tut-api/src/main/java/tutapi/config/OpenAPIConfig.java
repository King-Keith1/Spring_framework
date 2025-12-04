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

@Configuration
public class OpenAPIConfig {

    @Value("${app.openapi.dev-url}")
    private String devUrl;

    @Value("${app.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server devServer = new Server().url(devUrl).description("Development server");
        Server prodServer = new Server().url(prodUrl).description("Production server");

        Contact contact = new Contact().name("Your Name").email("you@example.com").url("https://your-site");

        License mit = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .description("APIs to manage tutorials")
                .contact(contact)
                .license(mit);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
