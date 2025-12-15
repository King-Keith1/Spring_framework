package eldenmessages.repository;

import eldenmessages.entity.User;              // Entity class representing application users
import org.springframework.data.jpa.repository.JpaRepository;  // Spring Data JPA base repository interface

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
