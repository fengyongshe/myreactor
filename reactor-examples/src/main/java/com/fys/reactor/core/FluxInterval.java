package com.fys.reactor.core;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FluxInterval {

  public static void main(String[] args) throws Exception {
    String desc = "Zip two sources together, that is to say wait for" +
        " all the sources to emit one element " +
        "and combine these elements once into a Tuple2.";
    Flux<String> descFlux = Flux.fromArray(desc.split("\\s+"));
    descFlux.subscribe(System.out::println);
    CountDownLatch countDownLatch = new CountDownLatch(1);
    Flux.zip(
        descFlux,
        Flux.interval(Duration.ofSeconds(2))
    ).subscribe(
        t -> System.out.println(t.getT1()+":"+t.getT2()), null, countDownLatch::countDown
    );
    countDownLatch.await(100, TimeUnit.SECONDS);
  }
}
