package com.fys.reactor.netty;

import com.fys.reactor.myreactor.Flux;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

public class SimpleTcpClient {

  public static void main(String[] args) {
    Connection client = TcpClient.create()
        .port(18080)
        .host("localhost")
        .handle((in,out) -> out.sendString(Flux.just("test")))
        .wiretap(true)
        .connectNow();
    client.disposeNow();
  }
}
