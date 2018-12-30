package com.fys.vertx.rxjava;

import com.fys.vertx.util.Runner;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;

public class Receiver extends AbstractVerticle {

  public static void main(String[] args) {
    Runner.runClusteredExample(Receiver.class);
  }


  @Override
  public void start() throws Exception {

    EventBus eb = vertx.eventBus();
    eb.consumer("news-feed")
        .toObservable()
        .subscribe( message ->
            System.out.println("Received news: " + message.body()));
    System.out.println("Ready!");

  }
}
