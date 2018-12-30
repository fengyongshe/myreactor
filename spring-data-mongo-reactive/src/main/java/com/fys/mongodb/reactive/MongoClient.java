package com.fys.mongodb.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.Disposable;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@SpringBootApplication
public class MongoClient {

  @Autowired ReactivePersonRepository repository;

  public void search() {
    Queue<Person> people = new ConcurrentLinkedQueue<>();

    Disposable disposable = repository.findWithTailableCursorBy() //
        .doOnNext(System.out::println) //
        .doOnNext(people::add) //
        .doOnComplete(() -> System.out.println("Complete")) //
        .doOnTerminate(() -> System.out.println("Terminated")) //
        .subscribe();
  }

  public static void main(String[] args) {


  }
}
