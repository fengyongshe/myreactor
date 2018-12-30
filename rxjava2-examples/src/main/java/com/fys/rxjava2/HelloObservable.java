package com.fys.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class HelloObservable {

  public static void main(String[] args) {

    Consumer<Long> subscriber1 = new Consumer<Long>() {
      @Override
      public void accept(Long aLong) throws Exception {
        System.out.println("SubScriber-1 :" + aLong);
      }
    };

    Consumer<Long> subscriber2 = new Consumer<Long>() {
      @Override
      public void accept(Long aLong) throws Exception {
        System.out.println("SubScriber-2 :" + aLong);
      }
    };

    Observable<Long> observable = Observable.create(
        new ObservableOnSubscribe<Long>() {
          @Override
          public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
            Observable.interval(10,
                TimeUnit.MICROSECONDS,
                Schedulers.computation()).take(Integer.MAX_VALUE)
                .subscribe(emitter::onNext);
          }
        }
    );

    observable.subscribe(subscriber1);
    observable.subscribe(subscriber2);

    try {
      Thread.sleep(100L);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}
