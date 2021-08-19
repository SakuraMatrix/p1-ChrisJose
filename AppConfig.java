package com.github.ChrisJose.posSystem.Model;
import com.datastax.oss.driver.api.core.CqlSession;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import java.net.URISyntaxException;


/*@Configuration
@ComponentScan
 */
public class AppConfig {

    CustomerService customerService;

    public CqlSession session() {
        return CqlSession.builder().build();
    }

    public static void main(String[] args) {

        CqlSession session = CqlSession.builder().build();
        CustomerRepository customerRepository = new CustomerRepository(session);
        CustomerService customerService = new CustomerService(customerRepository);
        // public DisposableServer disposableServer() throws URISyntaxException {
        //return
        HttpServer.create()
                .port(8080)
                .route(routes ->
                        routes
                                .get("/test",(request, response) ->
                                        response.sendString(Mono.just("Hello World!")
                                                .log("http-server")))
                                .get("/customers", (request, response) ->
                                        response.send(customerService.getAll()
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .post("/customers", (request, response) ->
                                        response.send(request.receive().asString()
                                                .map(App::parseCustomer)
                                                .map(customerService::createCustomer)
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .get("/customers/{param}", (request, response) ->
                                        response.send(customerService.get(request.param("param"))
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .get("/customers/delete/{param}", (request, response) ->
                                        response.send(customerService.deleteCustomer(request.param("param"))
                                                .map(App::intToByteBuff)
                                                .log("https-server")))

                )

                .bindNow()
                .onDispose()
                .block();
    }
}


