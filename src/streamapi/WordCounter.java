package streamapi;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCounter {
    public static Map<String, Integer> count(List<String> list) {
        return list.stream().collect(Collectors.toMap(Function.identity() /* t -> t */, o -> 1, (v1, v2) -> v1 + v2));
    }
}
