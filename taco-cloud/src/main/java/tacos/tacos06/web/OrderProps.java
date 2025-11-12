package tacos.tacos06.web;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.boot.context.properties.
                                        ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component //Tells Spring to create this as a bean (an object Spring manages). So it gets auto-loaded when the app starts.
@ConfigurationProperties(prefix="taco.orders") //It says "look in the config file for properties starting with taco.orders.
@Data //From Lombok – auto-makes getters/setters for pageSize so you can use it easily (like props.getPageSize()).
@Validated //Turns on checks for the rules below
public class OrderProps {
  //Validates it must be 5-25
  @Min(value=5, message="must be between 5 and 25")
  @Max(value=25, message="must be between 5 and 25")
  private int pageSize = 20; //The only setting here. It's an int for how many orders to show per page

}
