package com.github.ChrisJose.p1.Service;
import com.github.ChrisJose.p1.Domain.Customer;
import com.github.ChrisJose.p1.Domain.Order;
import com.github.ChrisJose.p1.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log =
            (Logger) LoggerFactory.getLogger("p1.order.service");

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository; }

    public Flux<Order> getAll() {
        log.info("Executing getAll() method");
        return orderRepository.getAll();}

    public Mono<Order> get(String orderId) {
        log.info("Executing get() method");
        return orderRepository.get(Integer.parseInt(orderId)); }

    public Order createOrder(Order order) {
        log.info("Executing createCustomer() method");
        return orderRepository.createOrder(order);
    }

    public Mono<Order> deleteOrder(String orderId) {
        log.info("Executing deleteOrder() method");
        return this.orderRepository.deleteOrder(Integer.parseInt(orderId));
    }


}
