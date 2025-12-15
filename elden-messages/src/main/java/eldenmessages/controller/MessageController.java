package eldenmessages.controller;

// Domain/entity and repository imports
import eldenmessages.entity.Message;              // Represents the message entity persisted in the database
import eldenmessages.repository.MessageRepository; // Repository interface for CRUD operations on Message

// Spring framework imports
import org.springframework.beans.factory.annotation.Autowired; // Enables dependency injection of beans
import org.springframework.stereotype.Controller;               // Marks this class as a Spring MVC controller
import org.springframework.ui.Model;                           // Holds attributes for rendering views
import org.springframework.web.bind.annotation.GetMapping;     // Maps HTTP GET requests to handler methods
import org.springframework.web.bind.annotation.ModelAttribute; // Binds form fields to method parameters/objects
import org.springframework.web.bind.annotation.PostMapping;    // Maps HTTP POST requests to handler methods

// Java standard library imports
import java.security.Principal; // Represents the currently authenticated user



@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("messages", messageRepo.findAll());
        model.addAttribute("message", new Message());
        return "home";
    }

    @PostMapping("/post")
    public String postMessage(@ModelAttribute Message message, Principal principal) {
        message.setUsername(principal.getName());
        messageRepo.save(message);
        return "redirect:/home";
    }
}
