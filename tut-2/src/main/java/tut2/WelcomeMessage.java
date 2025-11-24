package tut2;

import org.springframework.stereotype.Component;

@Component
public class WelcomeMessage {

    public String getWelcomeMessage() {
        return "Welcome to Spring Boot";
    }

    // A main method to run the example
    public static void main(String[] args) {
        WelcomeMessage welcomeMessageInstance = new WelcomeMessage();
        System.out.println(welcomeMessageInstance.getWelcomeMessage());
    }
}