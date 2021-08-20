package com.github.ChrisJose.posSystem.Model;
import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import java.net.URISyntaxException;


@Configuration
@ComponentScan
public class AppConfig {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @Bean
    public CqlSession session() {
        return CqlSession.builder().build();
    }

    @Bean
    public DisposableServer server() throws URISyntaxException  {
        //Specify application endpoints
        return HttpServer.create()
                .port(8080)
                .route(routes ->
                        routes
                                //Get all customers
                                .get("/customers", (request, response) ->
                                        response.send(customerService.getAll()
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                //Make new customer
                                .post("/customers", (request, response) ->
                                        response.send(request.receive().asString()
                                                .map(App::parseCustomer)
                                                .map(customerService::createCustomer)
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                //Get one customer
                                .get("/customers/{param}", (request, response) ->
                                        response.send(customerService.get(request.param("param"))
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                //Get all orders
                                .get("/orders", (request, response) ->
                                        response.send(orderService.getAll()
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                //Make new order
                                .post("/orders", (request, response) ->
                                        response.send(request.receive().asString()
                                                .map(App::parseOrder)
                                                .map(orderService::createOrder)
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                //Get one order
                                .get("/orders/{param}", (request, response) ->
                                        response.send(orderService.get(request.param("param"))
                                                .map(App::toByteBuf)
                                                .log("http-server")))

)
                .bindNow();
    }
}

/*
Path indexHTML = Paths.get(AppConfig.class.getResource("/customer.html").toURI());
                                .get("/}", (request, response) ->
                                        response.sendFile(indexHTML))
                                .get("/customers/delete/{param}", (request, response) ->
                                        response.send(customerService.deleteCustomer(request.param("param"))
                                                .map(App::toByteBuf)//intToByteBuff
                                                .log("https-server")))
 */

