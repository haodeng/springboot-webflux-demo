package demo.hao.webflux.router;

import demo.hao.webflux.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers",handler::getCustomers)
                .GET("/router/customers/stream",handler::getCustomersViaStream)
                .GET("/router/customer/{id}",handler::findCustomer)
                .POST("/router/customer",handler::saveCustomer)
                .build();

    }
}
