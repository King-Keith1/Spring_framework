package eldenmessages.controller;

import eldenmessages.repository.MessageRepository; // Repository interface for accessing Message entities

import org.springframework.beans.factory.annotation.Autowired; // Enables dependency injection of beans
import org.springframework.stereotype.Controller;            // Marks this class as a Spring MVC controller
import org.springframework.ui.Model;                       // Holds attributes for rendering views
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests to handler methods


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
}
