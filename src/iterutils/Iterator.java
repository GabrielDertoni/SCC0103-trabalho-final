package iterutils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Iterator<E> implements java.util.Iterator<E> {
    abstract public boolean hasNext();
    abstract public E next();

    public <T> Iterator<T> flatMap(Function<E, Iterator<T>> toIterator) {
        return new FlatMapIterator(this, toIterator);
    }

    public <T> Iterator<T> map(Function<E, T> mappingFunction) {
        return new MapIterator<>(this, mappingFunction);
    }

    public Iterator<E> takeWhile(Function<E, Boolean> predicate) {
        return new TakeWhileIterator<>(this, predicate);
    }

    public Iterator<E> take(int nToTake) {
        return new TakeIterator<>(this, nToTake);
    }

    public List<E> collectToList() {
        List<E> collection = new ArrayList<>();

        while (hasNext()) {
            collection.add(next());
        }

        return collection;
    }

}
