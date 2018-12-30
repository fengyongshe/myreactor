package com.fys.reactor.netty;

import reactor.core.publisher.Flux;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.server.HttpServer;

public class SimpleHttpServer {

  public static void main(String[] args) {
    HttpServer.create()
        .host("localhost")// Prepares an HTTP server ready for configuration
        .port(0)    // Configures the port number as zero, this will let the system pick up
        // an ephemeral port when binding the server
        .route(routes ->
            // The server will respond only on POST requests
            // where the path starts with /test and then there is path parameter
            routes.post("/test/{param}", (request, response) ->
                response.sendString(request.receive()
                    .asString()
                    .map(s -> s + ' ' + request.param("param") + '!')
                    .log("http-server"))))
        .bindNow();
    HttpClient.create()             // Prepares an HTTP client ready for configuration
        .port(18080)  // Obtains the server's port and provides it as a port to which this
        // client should connect
        .post()               // Specifies that POST method will be used
        .uri("/test/World")   // Specifies the path
        .send(ByteBufFlux.fromString(Flux.just("Hello")))  // Sends the request body
        .responseContent()    // Receives the response body
        .aggregate()
        .asString()
        .log("http-client")
        .block();
  }
}
