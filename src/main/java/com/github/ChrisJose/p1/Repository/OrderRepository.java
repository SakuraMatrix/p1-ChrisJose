package com.github.ChrisJose.p1.Repository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.ChrisJose.p1.Domain.Customer;
import com.github.ChrisJose.p1.Domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class OrderRepository {

    private static final Logger log =
            (Logger) LoggerFactory.getLogger("p1.order.repo");
    private CqlSession session;
    public OrderRepository(CqlSession session) {this.session = session; }

    //Retrieve all orders
    public Flux<Order> getAll() {
        log.info("Querying database for all orders");
        return Flux.from(session.executeReactive("SELECT * FROM p1.orderAccount"))
                .map(row -> new Order(
                row.getInt("order_id"),
                row.getInt("customer_id"),
                row.getString("comment"),
                row.getString("sales_associate"),
                row.getString("order_date"),
                row.getString("products"),
                row.getFloat("total_amt")));
    }

    //Retrieve one order
    public Mono<Order> get(int orderId) {
        log.info("Querying database for order");
        return Mono.from(session.executeReactive("SELECT * FROM p1.orderAccount where order_id = " + orderId))
                .map(row -> new Order(
                        row.getInt("order_id"),
                        row.getInt("customer_id"),
                        row.getString("comment"),
                        row.getString("sales_associate"),
                        row.getString("order_date"),
                        row.getString("products"),
                        row.getFloat("total_amt")));
    }

    //Create an order
    public Order createOrder(Order order){
        log.info("Inserting order into database");
        SimpleStatement stmt = SimpleStatement.builder(
                "INSERT INTO p1.orderAccount (order_id, customer_id,comment,sales_associate,order_date,products,total_amt) values (?,?,?,?,?,?,?)")
                .addPositionalValues(
                        order.getOrderId(),
                        order.getCustomerId(),
                        order.getComment(),
                        order.getSalesAssociate(),
                        order.getOrderDate(),
                        order.getProducts(),
                        order.getTotalAmt())
                .build();
        Flux.from(session.executeReactive(stmt)).subscribe();
        return order;
    }

    public Mono<Order> deleteOrder(int orderId){
        return Mono.from(session.executeReactive("DELETE FROM p1.orderAccount WHERE order_id =" + orderId))
                .map(row -> new Order(
                        row.getInt("order_id"),
                        row.getInt("customer_id"),
                        row.getString("comment"),
                        row.getString("sales_associate"),
                        row.getString("order_date"),
                        row.getString("products"),
                        row.getFloat("total_amt")));
    }

}



