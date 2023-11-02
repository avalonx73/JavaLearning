package streamapi;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkingWithMapsUsing {
    public static void main(String[] args) {

        Map<String, String> cardProcessState = Map.of("key1", "value1", "key2", "value2");

        Set<String> collect1 = cardProcessState.entrySet().stream()
                .filter(e -> "value2".equals(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        Set<String> collect2 = cardProcessState.entrySet().stream()
                .filter(e -> "key1".equals(e.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }
}
