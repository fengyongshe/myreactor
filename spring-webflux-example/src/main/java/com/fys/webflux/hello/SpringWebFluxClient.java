package com.fys.webflux.hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class SpringWebFluxClient {

  public static void main(String[] args) {
    //SpringApplication.run(SpringWebFluxClient.class, args);
    GreetingWebClient gwc = new GreetingWebClient();
    System.out.println(gwc.getResult());
  }

}
