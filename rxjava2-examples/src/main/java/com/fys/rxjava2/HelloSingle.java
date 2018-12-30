package com.fys.rxjava2;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

public class HelloSingle {

  public static void main(String[] args) {

    Single.create(new SingleOnSubscribe<String>() {
      @Override
      public void subscribe(@NonNull SingleEmitter<String> se) throws Exception {
        se.onSuccess("test");
      }
    }).subscribe(
        msg -> System.out.println(msg),
        throwable -> throwable.printStackTrace()
    );
    
  }
}
