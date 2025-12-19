package sia.tut1.controller;

import org.springframework.stereotype.Component;


@Component
public class PizzaController {

    public String getPizza(){
        return "I learnt how to do spring annotations from; https://www.udemy.com/course/spring-and-spring-boot-annotations/";
    }

}
