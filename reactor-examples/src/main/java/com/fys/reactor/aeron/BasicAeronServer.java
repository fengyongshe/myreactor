package com.fys.reactor.aeron;


import reactor.core.publisher.Mono;
import reactor.ipc.aeron.server.AeronServer;

public class BasicAeronServer {

  public static void main(String[] args) {

    AeronServer server = AeronServer.create("server",
        options -> {
          options.serverChannel("aeron:udp?endpoint=localhost:13000");
        });
    server.newHandler((inbound,outbound)-> {
      inbound.receive().asString().log("receive").subscribe();
      return Mono.never();
    }).block();
  }
}
