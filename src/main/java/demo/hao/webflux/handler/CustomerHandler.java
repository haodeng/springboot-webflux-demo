package demo.hao.webflux.handler;

import demo.hao.webflux.dao.CustomerDao;
import demo.hao.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao dao;


    public Mono<ServerResponse> getCustomers(ServerRequest request){
        Flux<Customer> customerList = dao.getCustomerList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> getCustomersViaStream(ServerRequest request) {
        Flux<Customer> customersStream = dao.getCustomersStream();
        return ServerResponse.ok().
                contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
      int customerId= Integer.valueOf( request.pathVariable("id"));
        Mono<Customer> customerMono = dao.getCustomerList()
                .filter(c -> c.getId() == customerId)
                .next();

        return ServerResponse.ok().body(customerMono, Customer.class);
    }


    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }



}
