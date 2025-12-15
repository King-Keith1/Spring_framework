package tut3.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRestClient userRestClient;

    public UserController(UserRestClient userRestClient) {
        this.userRestClient = userRestClient;
    }

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRestClient.findAll();
    }

    // GET user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRestClient.findById(id);
    }
}