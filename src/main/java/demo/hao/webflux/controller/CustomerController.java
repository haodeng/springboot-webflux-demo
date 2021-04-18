package demo.hao.webflux.controller;

import demo.hao.webflux.dto.Customer;
import demo.hao.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;


    // This is blocking, wait 10s and return all customers
    @GetMapping
    public List<Customer> getCustomers() {
        return service.getCustomers();
    }

    // Push to client every 1 s
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersStream() {
        return service.getCustomersStream();
    }
}
