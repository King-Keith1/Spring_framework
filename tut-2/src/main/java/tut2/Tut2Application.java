package tut2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Tut2Application {

    private static final Logger log = LoggerFactory.getLogger(Tut2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Tut2Application.class, args);
        log.info("Application started successfully!");
    }

}
