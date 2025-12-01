package eldenmessages.controller;

import eldenmessages.entity.Message;
import eldenmessages.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/home")
    public String home(Model model) {
        List<Message> allMessages = messageRepo.findAll();
        model.addAttribute("messages", allMessages);
        return "home";
    }
}

