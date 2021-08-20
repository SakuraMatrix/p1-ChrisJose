package com.github.ChrisJose.posSystem.Model.Service;
import com.github.ChrisJose.posSystem.Model.Domain.Customer;
import com.github.ChrisJose.posSystem.Model.Repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger log =
            (Logger) LoggerFactory.getLogger("p1.customer.service");

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository; }

    public Flux<Customer> getAll() {
        log.info("Executing getAll() method");
        return customerRepository.getAll();}

    public Mono<Customer> get(String customerId) {
        log.info("Executing get() method");
        return customerRepository.get(Integer.parseInt(customerId)); }

    public Customer createCustomer(Customer customer) {
        log.info("Executing createCustomer() method");
        return customerRepository.createCustomer(customer);
    }

}
