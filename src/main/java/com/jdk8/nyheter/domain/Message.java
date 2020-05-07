package com.jdk8.nyheter.domain;

class Message {

  private String msg;

  public Message(String msg) {
    this.msg = msg;
  }

  public String getMessage() {
    return msg;
  }

  public String toString() {
    return getMessage();
  }
}