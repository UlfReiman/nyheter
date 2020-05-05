package com.jdk8.nyheter.domain;

class Person {

  String name;
  int age;
  Gender gender;

  Person(String name, int age, Gender gender) {

    this.gender = gender;
    this.name = name;
    this.age = age;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}