package tacos.tacos05;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.tacos05.data.OrderRepository;
import tacos.tacos05.data.TacoRepository;

@Service
public class TacoDesignService {

    private final TacoRepository tacoRepo;
    private final OrderRepository orderRepo;

    public TacoDesignService(TacoRepository tacoRepo, OrderRepository orderRepo) {
        this.tacoRepo = tacoRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
    public void saveTacoWithOrder(Taco taco, TacoOrder order) {
        order.addTaco(taco); // link taco → order
        orderRepo.save(order); // cascade saves taco too
    }
}

