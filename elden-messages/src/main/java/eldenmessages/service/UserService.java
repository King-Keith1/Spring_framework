package eldenmessages.service;

// Import the User entity class representing application users
import eldenmessages.entity.User;
// Import the UserRepository interface for accessing user data from the database
import eldenmessages.repository.UserRepository;

// Import Spring's dependency injection annotation to automatically wire beans
import org.springframework.beans.factory.annotation.Autowired;
// Import core Spring Security interface used to load user-specific data
import org.springframework.security.core.userdetails.UserDetails;
// Service interface used by Spring Security to look up users during authentication
import org.springframework.security.core.userdetails.UserDetailsService;
// Exception thrown when a user cannot be found by username
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// Builder class for constructing Spring Security UserDetails instances
import org.springframework.security.core.userdetails.User.UserBuilder;
// Marks this class as a Spring service component (discovered via component scanning)
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(user.getPassword());
        builder.roles("USER"); // simple role

        return builder.build();
    }

    public User save(User user) {
        return userRepo.save(user);
    }
}

