package iterutils;

import java.util.function.Function;

public class MapIterator<E, T> extends Iterator<T> {
    private Iterator<E> toMap;
    private Function<E, T> mappingFunction;

    public MapIterator(Iterator<E> toMap, Function<E, T> mappingFunction) {
        this.toMap = toMap;
        this.mappingFunction = mappingFunction;
    }

    @Override
    public boolean hasNext() {
        return toMap.hasNext();
    }

    @Override
    public T next() {
        return mappingFunction.apply(toMap.next());
    }
}
