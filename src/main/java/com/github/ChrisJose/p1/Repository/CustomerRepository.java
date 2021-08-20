package com.github.ChrisJose.p1.Repository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.ChrisJose.p1.Domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Repository
public class CustomerRepository {
    private static final Logger log =
            (Logger) LoggerFactory.getLogger("p1.customer.repo");
    private CqlSession session;
    public CustomerRepository(CqlSession session) {this.session = session; }

    //Retrieve all customers
    public Flux<Customer> getAll() {
        log.info("Querying database for all customers");
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
                        row.getString("zip")));
    }

    //Retrieve one customer
    public Mono<Customer> get(int customerId) {
        log.info("Querying database for customer");
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
                        row.getString("zip")));
    }


    //Create a customer
    public Customer createCustomer(Customer customer){
        log.info("Inserting customer into database");
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
        Flux.from(session.executeReactive(stmt)).subscribe();
        return customer;
    }

}
