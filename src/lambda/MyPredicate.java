package lambda;

@FunctionalInterface
public interface MyPredicate<T> {
    boolean check(T t);
}
