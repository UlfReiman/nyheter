package com.jdk8.nyheter.domain;

class Book {

  private String name;

  public Book(String name) {
    this.name = name;
  }

  public String getName() {
    System.out.println(name);
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
