package sia.tut1;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sia.tut1.controller.PizzaController;

@SpringBootApplication
public class Tut1Application {

    public static void main(String[] args) {
        var context = SpringApplication.run(Tut1Application.class, args);
       PizzaController pizzaController = context.getBean(PizzaController.class);
       System.out.println(pizzaController.getPizza());
    }

}
