package com.fys.reactor.netty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Message {

  private int id;

  public Message(int id) {
    this.id = id;
  }

}
