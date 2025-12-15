package eldenmessages.controller;

// Import the User entity representing users in the system
import eldenmessages.entity.User;
// Import the Spring Data JPA repository for accessing User entities
import eldenmessages.repository.UserRepository;
// Import ResponseEntity to build HTTP responses with status and body
import org.springframework.http.ResponseEntity;
// Import PasswordEncoder to securely hash user passwords
import org.springframework.security.crypto.password.PasswordEncoder;
// Import core Spring Web annotations for REST controllers and request mapping
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthRestController(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepo.findByUsername(user.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Username already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);

        return ResponseEntity.status(201).body("User registered successfully");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Not authenticated");
        }
        return ResponseEntity.ok("Logged in as: " + principal.getName());
    }

}
