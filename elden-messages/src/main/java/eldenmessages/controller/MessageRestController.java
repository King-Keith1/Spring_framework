package eldenmessages.controller;

// Domain entity representing a message
import eldenmessages.entity.Message;
// Spring Data repository interface for Message persistence
import eldenmessages.repository.MessageRepository;

// Spring's dependency injection annotation
import org.springframework.beans.factory.annotation.Autowired;
// Spring's HTTP response wrapper
import org.springframework.http.ResponseEntity;
// REST controller and request-mapping annotations
import org.springframework.web.bind.annotation.*;

// Represents the currently authenticated user
import java.security.Principal;
// Collection type for returning lists of messages
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping
    public ResponseEntity<List<Message>> listMessages() {
        List<Message> messages = messageRepo.findAll();
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message, Principal principal) {

        // Attach authenticated username
        message.setUsername(principal.getName());

        Message saved = messageRepo.save(message);

        return ResponseEntity.status(201).body(saved);
    }
}
