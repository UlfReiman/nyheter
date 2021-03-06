package com.jdk8.nyheter.domain;


import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
interface Converter<F, T> {

  T convert(F from);
}

public class JDK8 {

  private Set<String> shakespeare;

  public static void main(String[] args) throws IOException {


    Function<String,Integer> stringToInt = s ->  Integer.valueOf(s);
    int i = stringToInt.apply("1");

    Converter<String, Integer> integerConverter1 = (from) -> Integer.valueOf(from);
    Integer converted1 = integerConverter1.convert("123");

    Message message = new Message("java2s.com");

    Consumer<Message> messageConsumer = (t) -> System.out.println(t);
    Consumer<Message> endConsumer = (t) -> System.out.println("End: " + t);
    messageConsumer.andThen(endConsumer).accept((message));

    List<Book> books = new ArrayList<>();
    books.add(new Book("A"));
    books.add(new Book("D"));
    books.add(new Book("C"));
    books.add(new Book("B"));

    Function<Book, String> function = Book::getName;
    Consumer<Book> consumerFunction = Book::getName;
    books.forEach(Book::getName);
    books.forEach(consumerFunction);

    Message message2 = new Message("java2s.com");
    Person person = new Person("Peter", 28, Gender.MALE);

    Consumer<Message> messageConsumer2 = (t) -> System.out.println(t);
    messageConsumer.accept(message2);

    Consumer<Person> personConsumer = (t) -> System.out.println(t);
    personConsumer.accept(person);

    List<Person> persons2 =
        Arrays.asList(
            new Person("Max", 18, Gender.MALE),
            new Person("Peter", 23, Gender.MALE),
            new Person("Pamela", 23, Gender.FEMALE),
            new Person("David", 12, Gender.MALE));
    parallelPerson(persons2);

    List<Person> persons1 =
        Arrays.asList(
            new Person("Max", 18, Gender.MALE),
            new Person("Peter", 23, Gender.MALE),
            new Person("Pamela", 23, Gender.FEMALE),
            new Person("David", 12, Gender.MALE));
    int upAge = 25;
    int lowAge = 16;
    filterAge(persons1, upAge, lowAge);

    Path path1 = Paths.get("TomSawyer1.txt");
    Path path2 = Paths.get("TomSawyer2.txt");
    Path path3 = Paths.get("TomSawyer3.txt");
    Stream<String> s1 = Files.lines(path1);
    Stream<String> s2 = Files.lines(path2);
    Stream<String> s3 = Files.lines(path3);
    textToWords(s1, s2, s3);

    Set<String> shakespeare = Files.lines(Paths.get("words.shakespeare.txt"))
        .map(word -> word.toLowerCase()).collect(Collectors.toSet());

    Set<String> scrabble = Files.lines(Paths.get("ospd.txt"))
        .map(word -> word.toLowerCase()).collect(Collectors.toSet());
    final int[] scrabbleENScore = {
        // a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,  q, r, s, t, u, v, w, x, y,  z
        1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    Function<String, Integer> score = words -> words.chars()
        .map(letter -> scrabbleENScore[letter - 'a']).sum();
    ToIntFunction<String> intScore = words -> words.chars()
        .map(letter -> scrabbleENScore[letter - 'a']).sum();

    Object array;
    Supplier<Stream<int[]>> streamSupplier = () -> Stream.of(scrabbleENScore);

    String scoreResult = shakespeare.stream()
        .filter(words -> scrabble.contains(words))
        .max(Comparator.comparing(score)).get();

    IntSummaryStatistics intSummaryStatistics = shakespeare.stream()
        .filter(scrabble::contains).mapToInt(intScore).summaryStatistics();
    System.out.println("Stats:: " + intSummaryStatistics);

    System.out.println(shakespeare.size());
    System.out.println(scrabble.size());

    Path p1 = Paths.get("p1.txt");
    Path p2 = Paths.get("p2.txt");

    Stream<Integer> stream5 = Stream.of(1, 3, 5);
    Stream<Integer> stream2 = Stream.of(2, 4, 6);
    Stream<Integer> stream3 = Stream.of(18, 15, 36);
    Stream<Integer> stream4 = Stream.of(99);



   /* Path path1 = Paths.get("TomSawyer1.txt");
    Path path2 = Paths.get("TomSawyer2.txt");
    Path path3 = Paths.get("TomSawyer3.txt");
    Stream<String> s1=Files.lines(path1);
    Stream<String> s2=Files.lines(path2);
    Stream<String> s3=Files.lines(path3);*/

    Function<String, Stream<String>> splitIntoWords = textToWords();

  /*  Set<String> ord = Stream.of(s1, s2, s3)
        .flatMap(Function.identity())
        .flatMap(splitIntoWords).collect(toSet());
*/
    String[] stream1 = {"one", "two", "three", "four"};
    spliteratorExample(stream1);

    Stream<Stream<String>> streamStream = Stream.of(s1, s2, s3);
    System.out.println(streamStream.count());

    ArrayList<String> listA = new ArrayList<>();
    listA.add("A");
    listA.add("B");
    listA.add("C");
    listA.add("D");
    spliterator(listA);

    SortedSet<String> set = new TreeSet<>(Collections.reverseOrder());
    set.add("A");
    set.add("D");
    set.add("C");
    set.add("B");
    setSpliterator(set);

    Map<String, Employee> map1 = new HashMap<>();
    Map<String, Employee> map2 = new HashMap<>();
    Employee employee1 = new Employee(1L, "Henry");
    map1.put(employee1.getName(), employee1);
    Employee employee2 = new Employee(22L, "Annie");
    map1.put(employee2.getName(), employee2);
    Employee employee3 = new Employee(8L, "John");
    map1.put(employee3.getName(), employee3);
    Employee employee4 = new Employee(2L, "George");
    map2.put(employee4.getName(), employee4);
    Employee employee5 = new Employee(3L, "Henry");
    map2.put(employee5.getName(), employee5);
    System.out.println(ConcatEmployees(map1, map2));

    StringJoiner stringJoiner = new StringJoiner(",");
    stringJoiner.add("1").add("2").add("3");
    System.out.println(stringJoiner.toString());
    System.out.println(String.join(" ,", "1", "2", "3"));

    Map<Integer, String> HOSTING = new HashMap<>();
    HOSTING.put(1, "linode.com");
    HOSTING.put(2, "heroku.com");
    HOSTING.put(3, "digitalocean.com");
    HOSTING.put(4, "aws.amazon.com");
    //Map -> Stream -> Filter -> String
    String resultS = "";
    resultS = HOSTING.entrySet().stream()
        .filter(map -> "aws.amazon.com".equals(map.getValue()))
        .map(map -> map.getValue())
        .collect(Collectors.joining());
    System.out.println("With Java 8 : " + resultS);

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
    List<String> result5 = givenList.stream()
        .collect(toList());
    Set<String> result6 = givenList.stream()
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
    List<String> result3 = names.stream().filter(r -> r.startsWith("S"))
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
    Predicate<String> pr = (s) -> s.startsWith("G");

    // Iterate through the list
    for (String st : name) {
      // call the test method
      if (pr.test(st)) {
        System.out.println(st);
      }
    }

  }

  private static Set<String> textToWords(Stream<String> s1, Stream<String> s2,
      Stream<String> s3) {
    Stream<String> p = Stream.of(s1, s2, s3).flatMap(Function.identity())
        .flatMap(textToWords()); //varargs
    Set<String> wordsSet = p.collect(toSet());
    System.out.println(wordsSet);
    return wordsSet;
  }

  private static void filterAge(List<Person> persons1, int upAge, int lowAge) {
    persons1.stream()
        .filter(personIsBetweenAges(lowAge, upAge))
        .forEach(person -> {
          System.out.println(person.getAge() + ", " + person.getGender());
        });
  }

  private static void parallelPerson(List<Person> persons2) {
    persons2.parallelStream().filter(person -> person.getAge() > 20)
        .collect(toList()).forEach(System.out::println);
  }


  private static Function<String, Stream<String>> textToWords() {
    return line -> Pattern.compile(" ")
        .splitAsStream(line);
  }

  private static Map<String, Employee> ConcatEmployees(Map<String, Employee> map1,
      Map<String, Employee> map2) {
    return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (value1, value2) -> new Employee(value2.getId(), value1.getName())));

  }

  private static void setSpliterator(SortedSet<String> set) {
    System.out.println(set);

    System.out.println(set.spliterator().getComparator());
  }

  private static void spliterator(ArrayList<String> listA) {
    Spliterator<String> spliterator = listA.spliterator();

    spliterator.forEachRemaining(System.out::println);
  }

  private static void spliteratorExample(String[] stream1) {
    Spliterator<String> s1 = Stream.of(stream1).spliterator();
    Spliterator<String> s2 = s1.trySplit();
    System.out.println("-- first Spliterator --");
    s1.forEachRemaining(System.out::println);
    System.out.println("-- second Spliterator --");
    s2.forEachRemaining(System.out::println);
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

  private static Stream<String> concatenateStrings(Path path1, Path path2) throws IOException {
    Stream<String> s1 = Files.lines(path1);
    Stream<String> s2 = Files.lines(path2);
    Stream<String> concat = Stream.concat(s1, s2);

    return concat;

  }

}



