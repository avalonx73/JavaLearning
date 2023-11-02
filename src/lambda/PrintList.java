package lambda;

import java.util.List;

public class PrintList {

    public static void print(List<Integer> list, MyPredicate<Integer> predicate) {
        for(Integer element : list) {
            if (predicate.check(element)) {
                System.out.println(element.toString());
            }
        }
    }
}
