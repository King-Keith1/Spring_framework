package eldenmessages.entity;

import jakarta.persistence.*;
import lombok.Data;
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
