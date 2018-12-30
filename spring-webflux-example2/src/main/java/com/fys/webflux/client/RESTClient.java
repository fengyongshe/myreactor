package com.fys.webflux.client;

import com.fys.webflux.bean.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RESTClient {

  public static void main(String[] args) {
    final User user = new User();
    user.setId("test");
    user.setName("Hello");
    final WebClient client = WebClient.create("http://localhost:18080/user");
    final Mono<User> createdUser = client.post()
        .uri("")
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(user),User.class)
        .exchange()
        .flatMap(response -> response.bodyToMono(User.class));
    System.out.println(createdUser.block());
  }
}
