package eldenmessages.repository;

// Domain entity representing a message
import eldenmessages.entity.Message;
// Spring Data repository providing CRUD operations for Message
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}
