package streamapi;

import org.w3c.dom.ls.LSOutput;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <ul>
 * filter(Predicate predicate)<p>
 * map(Function mapper)<p>
 * flatMap(Function<T, Stream<R>> mapper)<p>
 * mapMulti(BiConsumer<T, Consumer<R>> mapper)<p>
 * limit(long maxSize)<p>
 * skip(long n)<p>
 * sorted()<p>
 * sorted(Comparator comparator)<p>
 * distinct()<p>
 * peek(Consumer action)<p>
 * takeWhile(Predicate predicate)<p>
 * dropWhile(Predicate predicate)<p>
 * boxed()<p>
 * <p>
 * Терминальные операторы:<p>
 * void forEach(Consumer action)<p>
 * void forEachOrdered(Consumer action)<p>
 * long count()<p>
 * Object[] toArray()<p>
 * List<T> toList()<p>
 * R collect(Collector collector)<p>
 *     <ul>
 *      toList()
 *      toSet()<p>
 *      toCollection(Supplier collectionFactory)<p>
 *      toMap(Function keyMapper, Function valueMapper)<p>
 *      toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)<p>
 *      toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)<p>
 *      toConcurrentMap(Function keyMapper, Function valueMapper)<p>
 *      toConcurrentMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)<p>
 *      toConcurrentMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)<p>
 *      collectingAndThen(Collector downstream, Function finisher)<p>
 *      joining()<p>
 *      joining(CharSequence delimiter)<p>
 *      joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)<p>
 *      summingInt(ToIntFunction mapper)<p>
 *      summingLong(ToLongFunction mapper)<p>
 *      summingDouble(ToDoubleFunction mapper)<p>
 *      averagingInt(ToIntFunction mapper)<p>
 *      averagingLong(ToLongFunction mapper)<p>
 *      averagingDouble(ToDoubleFunction mapper)<p>
 *      summarizingInt(ToIntFunction mapper)<p>
 *      summarizingLong(ToLongFunction mapper)<p>
 *      summarizingDouble(ToDoubleFunction mapper)<p>
 *      counting()<p>
 *      filtering(Predicate predicate, Collector downstream)<p>
 *      mapping(Function mapper, Collector downstream)<p>
 *      flatMapping(Function downstream)<p>
 *      reducing(BinaryOperator op)<p>
 *      reducing(T identity, BinaryOperator op)<p>
 *      reducing(U identity, Function mapper, BinaryOperator op)<p>
 *      minBy(Comparator comparator)<p>
 *      maxBy(Comparator comparator)<p>
 *      groupingBy(Function classifier)<p>
 *      groupingBy(Function classifier, Collector downstream)<p>
 *      groupingBy(Function classifier, Supplier mapFactory, Collector downstream)<p>
 *      groupingByConcurrent(Function classifier)<p>
 *      groupingByConcurrent(Function classifier, Collector downstream)<p>
 *      groupingByConcurrent(Function classifier, Supplier mapFactory, Collector downstream)<p>
 *      partitioningBy(Predicate predicate)<p>
 *      partitioningBy(Predicate predicate, Collector downstream)<p>
 *      </ul>
 * T reduce(T identity, BinaryOperator accumulator)<p>
 * U reduce(U identity, BiFunction accumulator, BinaryOperator combiner)<p>
 * Optional reduce(BinaryOperator accumulator)<p>
 * Optional min(Comparator comparator)<p>
 * Optional max(Comparator comparator)<p>
 * Optional findAny()<p>
 * Optional findFirst()<p>
 * boolean allMatch(Predicate predicate)<p>
 * boolean anyMatch(Predicate predicate)<p>
 * boolean noneMatch(Predicate predicate)<p>
 * OptionalDouble average()<p>
 * sum()<p>
 * </ul>
 */
public class Main {
    private static final int SIZE = 100;

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Map<String, Integer> count = WordCounter.count(Arrays.asList("A", "A", "B", "A", "B", "C", "A", "B", "C", "D"));


        List<Integer> randomValues = SecureRandom.getInstanceStrong()
                .ints(SIZE)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> list = IntStream.range(0, SIZE)
                .boxed()
                .collect(Collectors.toList());

        Map<Integer, List<Integer>> collect = IntStream.range(0, SIZE)
                .boxed()
                .collect(Collectors.groupingBy(partition -> (partition / 10),
                        Collectors.mapping(randomValues::get,
                                Collectors.toList())));


        System.exit(0);
    }
}
