package eldenmessages.controller;

import eldenmessages.entity.Message;
import eldenmessages.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

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
