package tacos.tacos05.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.tacos05.Ingredient;
import tacos.tacos05.Ingredient.Type;
import tacos.tacos05.TacoOrder;
import tacos.tacos05.Taco;
import tacos.tacos05.User;
import tacos.tacos05.data.IngredientRepository;
import tacos.tacos05.data.TacoRepository;
import tacos.tacos05.data.OrderRepository;
import tacos.tacos05.data.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository tacoRepo,
            OrderRepository orderRepo,
            UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        for (Type type : Ingredient.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        if (principal == null) {
            return null;
        }
        return userRepo.findByUsername(principal.getName());
    }

    @GetMapping
    public String showDesignForm(Model model) {
        if (!model.containsAttribute("taco")) {
            model.addAttribute("taco", new Taco());
        }
        return "design";
    }

    @PostMapping
    @Transactional
    public String processTaco(
            @Valid @ModelAttribute("taco") Taco taco,
            Errors errors,
            @ModelAttribute TacoOrder order,
            @ModelAttribute User user,
            Model model) {

        if (errors.hasErrors()) {
            log.warn("Taco design has validation errors: {}", errors);
            return "design";
        }

        // ✅ Ensure the order belongs to the logged-in user
        if (order.getUser() == null && user != null) {
            order.setUser(user);
        }

        // ✅ Save or update order first (so it’s persistent)
        TacoOrder savedOrder = orderRepo.save(order);

        // ✅ Link taco to that persistent order
        taco.setTacoOrder(savedOrder);

        // ✅ Save taco and link it back
        Taco savedTaco = tacoRepo.save(taco);
        savedOrder.addTaco(savedTaco);

        log.info("✅ Taco '{}' saved and linked to order ID {}", savedTaco.getName(), savedOrder.getId());

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
