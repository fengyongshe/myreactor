package com.fys.mongodb.reactive;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document
public class Person {

  private @Id String id;
  private final String firstname;
  private final String lastname;
  private final int age;

}
