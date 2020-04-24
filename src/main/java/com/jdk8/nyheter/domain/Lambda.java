package com.jdk8.nyheter.domain;


import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Lambda {


  public static void main(String[] args) {

    Instant start = Instant.now();

    Instant end = Instant.now();

    Duration elapsed = Duration.between(start, end);

    long m = elapsed.toMillis();

    Set<String> zones = ZoneId.getAvailableZoneIds();

    zones.forEach((e) -> {
      System.out.println(e);
    });

    LocalDate localDate = LocalDate.now();
    System.out.println("localDate" + localDate);

    List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
    List<String> result = givenList.stream()
        .collect(toList());
    Set<String> resul = givenList.stream()
        .collect(toSet());

    Stream<String> stream = givenList.stream();

    List<String> myList =
        Arrays.asList("a1", "a2", "b1", "c2", "c1");

    myList
        .stream()
        .filter(s -> s.startsWith("c"))
        .map(String::toUpperCase)
        .sorted()
        .forEach(System.out::println);

    Employee[] arrayOfEmps = {
        new Employee(1, "Jeff Bezos", 100000.0),
        new Employee(2, "Bill Gates", 200000.0),
        new Employee(3, "Mark Zuckerberg", 300000.0)
    };

    Stream.of(arrayOfEmps);

    // Creating a list of Prime Numbers
    List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11, 13);

    // Creating a list of Odd Numbers
    List<Integer> OddNumbers = Arrays.asList(1, 3, 5);

    // Creating a list of Even Numbers
    List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8);

    List<List<Integer>> listOfListofInts =
        Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers);

    System.out.println("The Structure before flattening is : " +
        listOfListofInts);

    // Using flatMap for transformating and flattening.
    List<Integer> listofInts = listOfListofInts.stream()
        .flatMap(list -> list.stream())
        .collect(toList());

    System.out.println("The Structure after flattening is : " +
        listofInts);
    // create a list of integers
    List<Integer> number = Arrays.asList(2, 3, 4, 5);

    // demonstration of map method
    List<Integer> square = number.stream().map(x -> x * x).collect(toList());
    System.out.println(square);

    // create a list of String
    List<String> names =
        Arrays.asList("Reflection", "Collection", "Stream");

    // demonstration of filter method
    List<String> result3 = names.stream().filter(s -> s.startsWith("S"))
        .collect(toList());
    System.out.println(result3);

    // demonstration of sorted method
    List<String> show =
        names.stream().sorted().collect(toList());
    System.out.println(show);

    // create a list of integers
    List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 2);
    List<String> result2 = new ArrayList<>();

    Consumer<String> c1 = result2::add;

    Consumer<String> c2 = System.out::println;

    // collect method returns a set
    Set<Integer> squareSet = numbers.stream().map(x -> x * x).collect(toSet());
    System.out.println(squareSet);

    // demonstration of forEach method
    number.stream().map(x -> x * x).forEach(y -> System.out.println(y));

    // demonstration of reduce method
    int even =
        number.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);

    System.out.println(even);

    // create a list of strings
    List<String> name =
        Arrays.asList("Geek", "GeeksQuiz", "g1", "QA", "Geek2");

    // declare the predicate type as string and use
    // lambda expression to create object
    Predicate<String> p = (s) -> s.startsWith("G");

    // Iterate through the list
    for (String st : name) {
      // call the test method
      if (p.test(st)) {
        System.out.println(st);
      }
    }
    List<Person> people = Arrays.asList(new Person(20, Gender.MALE),
        new Person(45, Gender.FEMALE), new Person(50, Gender.MALE),
        new Person(65, Gender.MALE));

    people.stream()
        .filter(personIsBetweenAges(16, 25))
        .forEach(person -> {
          System.out.println(person.getAge() + ", " + person.getGender());
        });
  }

  private static Predicate<Person> personIsMale() {
    return person -> person.getGender() == Gender.MALE;
  }

  private static Predicate<Person> personIsBetweenAges(int lower, int upper) {
    return personIsAtLeast(lower).and(personIsYoungerThan(upper));
  }

  private static Predicate<Person> personIsAtLeast(int age) {
    return person -> person.getAge() >= age;
  }

  private static Predicate<Person> personIsYoungerThan(int age) {
    return person -> person.getAge() < age;
  }

}



