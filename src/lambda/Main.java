package lambda;

import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    static String message = "Hello!";

    public static void main(String[] args) {

        List<String> list = Arrays.asList("7,1,4", "6,5,3");
        List<String> list2 = Arrays.asList("7,1,4", "6,5,2");

        int i1 = list.hashCode();
        int i2 = list2.hashCode();


        String collect1 = list.stream()
              .flatMap(s -> Arrays.stream(s.split(",")))
                .sorted()
                .collect(Collectors.joining(","));


        List<Integer> testList = Arrays.asList(-3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8);


        PrintList.print(testList, i -> i < 0);
        PrintList.print(testList, i -> i > 0);

        List<String> collect = testList.stream()
                .filter(e -> e < 0)
                .peek(System.out::println)
                .map(e -> e.toString())
                .peek(e -> System.out.println( "B: " + e.toString()))
                .sorted()
                .collect(Collectors.toList());


        MyPredicate<String> myPredicate2 = t -> true;



        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(message);
            }
        };

        message = "Good Bye!";
        r.run();

        Runnable r1 = () -> System.out.println("My Runnable");

        Function<Integer, Integer> f = (Integer i) -> ++i;
        Integer apply = f.apply(1);

        Predicate<Integer> p1 = o -> o > 0;
        Predicate<Integer> p2 = o -> o < 10;


        boolean test = p1.and(p2).test(9);

        Supplier<String> s = () -> "Suplier";

        s.get();

        Consumer<String> c = t -> System.out.println(t);

        c.accept("Consumer");



/*
        UnaryOperator
                BinaryOperator
  */

        r.run();

        PrivilegedAction<Integer> integerPrivilegedAction = () -> {
            return 42;
        };

        IntBinaryOperator intBinaryOperator = (int x, int y) -> x + y;

    }
}
