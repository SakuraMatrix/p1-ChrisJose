
import com.github.ChrisJose.p1.App;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class reactorNetty {

    public static void main(String[] args) throws URISyntaxException {

    List<String> test = new ArrayList<String>();


    Path xml = Paths.get(App.class.getResource("/log4j2.xml").toURI());
    Path sample = Paths.get(App.class.getResource("/sample.html").toURI());
        HttpServer.create()
                .port(8080)
                .route(routes ->

                        routes.post("/test/{param}", (request, response) ->
                                response.sendString(request.receive().asString().map(s -> s + ' ' + request.param("param") + '!')
                                        .log("http-server")))
                                //shows Hello World! in browser
                                .get("/items", (request, response) -> response.sendString(Mono.just("Hello World!")
                                        .log("http-server")))
                                //shows whatever you type in {abc} in web browser
                                .get("/items/{abc}", (request, response) -> response.sendString(Mono.just(request.param("abc"))
                                        .log("http-server")))
                                //shows in web browser but not terminal
                                .get("/sample", (request, response) -> response.status(404)
                                        .addHeader("Message","Goofed").sendFile(sample))
                                //shows in terminal but not web browser
                                .get("/xml", (request, response) -> response.addHeader("Message","Goofed").sendFile(xml)))
                              //  .get("/customers", (request, response) -> response.send(CustomerRepository.selectAll()))





                .bindNow()
                .onDispose()
                .block();// Starts the server in a blocking fashion, and waits for it to finish its init
    }

}