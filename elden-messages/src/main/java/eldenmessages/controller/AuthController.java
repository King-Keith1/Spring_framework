package eldenmessages.controller;

// Spring MVC stereotype annotation to mark this class as a web controller
import org.springframework.stereotype.Controller;
// Annotation for mapping HTTP GET requests to handler methods
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // resolves to src/main/resources/templates/login.html
    }

    // ... existing code ...
}
