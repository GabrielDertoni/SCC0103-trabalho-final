package iterutils;

import java.util.function.Function;

public class FlatMapIterator<E, T> extends Iterator<T> {
    private Function<E, Iterator<T>> toIterator;
    private Iterator<E> toFlatten;
    private Iterator<T> curIterator;

    public FlatMapIterator(Iterator<E> toFlatten, Function<E, Iterator<T>> toIterator) {
        this.toIterator = toIterator;
        this.toFlatten = toFlatten;
        curIterator = null;
    }

    @Override
    public boolean hasNext() {
        return toFlatten.hasNext() || (curIterator != null && curIterator.hasNext());
    }

    @Override
    public T next() {
        if (curIterator == null || !curIterator.hasNext()) {
            curIterator = toIterator.apply(toFlatten.next());
        }
        return curIterator.next();
    }
}
