package eldenmessages.entity;

import jakarta.persistence.*;      // JPA annotations for ORM mapping (e.g., @Entity, @Table, @Id, @GeneratedValue)
import lombok.Data;               // Lombok annotation to auto-generate getters, setters, equals, hashCode, and toString


@Data
@Entity
@Table(name = "users")   // ✔ FIX — rename table so H2 is happy
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
}
