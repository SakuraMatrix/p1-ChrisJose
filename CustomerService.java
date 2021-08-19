package com.github.ChrisJose.posSystem.Model;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository; }

    public Flux<Customer> getAll() {
        return customerRepository.getAll();}

    public Mono<Customer> get(String customerId) {
        return customerRepository.get(Integer.parseInt(customerId)); }

    public Customer createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }
    public Mono<Integer> deleteCustomer(String customerId) {
        //log.info("Deleting Customer");
        return customerRepository.deleteCustomer(Integer.parseInt(customerId));
    }



}
