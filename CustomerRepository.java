package com.github.ChrisJose.posSystem.Model;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

//@Repository

public class CustomerRepository {
    private CqlSession session;
    public CustomerRepository(CqlSession session) {this.session = session; }

    public Flux<Customer> getAll() {
        return Flux.from(session.executeReactive("SELECT * FROM p1.customerAccount"))
                .map(row -> new Customer(
                        row.getInt("customer_id"),
                        row.getString("first_name"),
                        row.getString("last_name"),
                        row.getString("phone_no"),
                        row.getString("email"),
                        row.getString("address"),
                        row.getString("address2"),
                        row.getString("city"),
                        row.getString("state"),
                        row.getString("zip"))); //Add order_id
    }

    public Mono<Customer> get(int customerId) {
        return Mono.from(session.executeReactive("SELECT * FROM p1.customerAccount where customer_id = " + customerId))
                .map(row -> new Customer(
                        row.getInt("customer_id"),
                        row.getString("first_name"),
                        row.getString("last_name"),
                        row.getString("phone_no"),
                        row.getString("email"),
                        row.getString("address"),
                        row.getString("address2"),
                        row.getString("city"),
                        row.getString("state"),
                        row.getString("zip"))); //Add order_id
    }


    public Customer createCustomer(Customer customer){
        SimpleStatement stmt = SimpleStatement.builder(
                "INSERT INTO p1.customerAccount (customer_id, first_name,last_name,phone_no,email,address,address2,city,state,zip) values (?,?,?,?,?,?,?,?,?,?)")
                .addPositionalValues(
                        customer.getCustomerId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getPrimaryPhoneNo(),
                        customer.getEmail(),
                        customer.getAddress(),
                        customer.getAddress2(),
                        customer.getCity(),
                        customer.getState(),
                        customer.getZip())
                .build();
        Flux.from(session.executeReactive(stmt)).subscribe();  //subscribe()
        return customer;
    }

    public Mono<Integer> deleteCustomer(int customerId) {
        // log.info("Deleting Customer");
        Flux.from(session.executeReactive(
                SimpleStatement.builder("DELETE FROM p1.customerAccount where customer_id = " + customerId)
                        .addPositionalValue(customerId)
                        .build()))
                .subscribe();
        return Mono.just(customerId);
    }






}
