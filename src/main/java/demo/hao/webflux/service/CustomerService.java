package demo.hao.webflux.service;

import demo.hao.webflux.dao.CustomerDao;
import demo.hao.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> getCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();

        System.out.println("Total execution time : " + (System.currentTimeMillis() - start));
        return customers;
    }

    public Flux<Customer> getCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();

        System.out.println("Total execution time : " + (System.currentTimeMillis() - start));
        return customers;
    }
}
