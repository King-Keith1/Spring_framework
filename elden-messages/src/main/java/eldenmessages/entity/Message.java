package eldenmessages.entity;

// JPA annotations for mapping this class to a database table
import jakarta.persistence.*;
// Lombok annotation to auto-generate getters, setters, equals, hashCode, and toString
import lombok.Data;
// Java date-time API for storing the message creation timestamp
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;   // used by controller: setUsername(...)
    private String content;    // must match form input name="content"

    private LocalDateTime createdAt = LocalDateTime.now();
}
