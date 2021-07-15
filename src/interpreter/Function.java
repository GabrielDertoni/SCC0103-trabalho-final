package interpreter;

@FunctionalInterface
public interface Function<T, R> {
    R call(Environment environment, T args);
}
