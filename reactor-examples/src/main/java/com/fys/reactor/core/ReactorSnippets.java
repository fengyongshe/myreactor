package com.fys.reactor.core;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReactorSnippets {

  public static void main(String[] args) throws Exception {
    List<String> words = Arrays.asList(
        "the",
        "quick",
        "brown",
        "fox",
        "jumped",
        "over",
        "the",
        "lazy",
        "dog"
    );
    Flux<String> fewWords = Flux.just("Hello","World");
    Flux<String> manyWords = Flux.fromIterable(words);

    fewWords.subscribe(System.out::println);
    System.out.println("Descripe the ManyWorkds");
    manyWords.subscribe(System.out::println);

    System.out.println("Flux Interval emitter");
    Flux.interval(Duration.of(100, ChronoUnit.SECONDS))
        .subscribe(System.out::println);
    Thread.sleep(5000);

    System.out.println("Flux generate Message");
    Random random = new Random();
    Flux.generate(ArrayList::new, (list,sink) -> {
      int value = random.nextInt(100);
      list.add(value);
      sink.next(value);
      if(list.size() == 10) {
        sink.complete();
      }
      return list;
    }).subscribe(System.out::println);

  }

}
