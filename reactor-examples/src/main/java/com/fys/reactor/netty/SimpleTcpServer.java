package com.fys.reactor.netty;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpClient;
import reactor.netty.tcp.TcpServer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTcpServer {

  final int    msgs    = 10;
  final int    threads = 4;

  ExecutorService threadPool;
  CountDownLatch  latch;

  public SimpleTcpServer() {
    latch = new CountDownLatch(msgs * threads);
    threadPool = Executors.newCachedThreadPool();
  }

  public void start() throws Exception {

    DisposableServer server = TcpServer.create()
        .port(0)
        .handle((in, out) -> {
          in.receive()
              .log("channel")
              .subscribe(trip -> latch.countDown());
          return Flux.never();
        })
        .wiretap(true)
        .bindNow();


    System.out.println("PORT +" + server.address()
        .getPort());

    Connection client = TcpClient.create()
        .port(server.address()
            .getPort())
        .handle((in, out) -> out.sendString(Flux.just("test")))
        .wiretap(true)
        .connectNow();



    client.disposeNow();
    server.disposeNow();
  }

  public void stop(){
    threadPool.shutdownNow();
    Schedulers.shutdownNow();
  }

  public static void main(String[] args) throws Exception {
    SimpleTcpServer simpleServer = new SimpleTcpServer();
    simpleServer.start();
  }
}
