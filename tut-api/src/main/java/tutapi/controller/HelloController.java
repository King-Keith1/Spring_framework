package tutapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hello", description = "Simple test API")
@RestController
public class HelloController {

    @Operation(summary = "Hello endpoint", description = "Returns a simple greeting message")
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Swagger is working!";
    }
}

