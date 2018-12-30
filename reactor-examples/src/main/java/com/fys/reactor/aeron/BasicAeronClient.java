package com.fys.reactor.aeron;

import reactor.core.publisher.Mono;
import reactor.ipc.aeron.client.AeronClient;
import reactor.ipc.aeron.ByteBufferFlux;

public class BasicAeronClient {

  public static void main(String[] args) {
    AeronClient client = AeronClient.create("client",
        options -> {
          options.serverChannel("aeron:udp?endpoint=localhost:13000");
          options.serverChannel("aeron:udp?endpoint=localhost:12001");
        });
    client.newHandler((inbound,outbound) -> {
      System.out.println("Handler Invoked:");
      outbound.send(ByteBufferFlux.from("Hello","World").log("Send"))
          .then().subscribe(avoid -> {}, Throwable::printStackTrace);
      return Mono.never();
    }).block();
    System.out.println("Main Completed");
  }
}
