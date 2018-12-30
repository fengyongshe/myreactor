package com.fys.reactor.core;

import reactor.core.publisher.Mono;

public class MonoSnippets {

  public static void main(String[] args) {

    Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
    Mono.create(sink -> sink.success("Sink")).subscribe(System.out::println);

  }
}
