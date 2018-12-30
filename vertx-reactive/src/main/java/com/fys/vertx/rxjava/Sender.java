package com.fys.vertx.rxjava;

import com.fys.vertx.util.Runner;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;

public class Sender extends AbstractVerticle {

  public static void main(String[] args) {
    Runner.runClusteredExample(Sender.class);
  }

  @Override
  public void start() throws Exception {

    EventBus eb = vertx.eventBus();
    vertx.setPeriodic(1000,
        v -> eb.publish("news-feed", "Some news!"));
  }

}
