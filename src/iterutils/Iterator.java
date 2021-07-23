package iterutils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Iterator<E> implements java.util.Iterator<E> {
    abstract public boolean hasNext();
    abstract public E next();

    public <T> Iterator<T> flatMap(Function<E, Iterator<T>> toIterator) {
        return new FlatMap<>(this, toIterator);
    }

    public <T> Iterator<T> map(Function<E, T> mappingFunction) {
        return new Map<>(this, mappingFunction);
    }

    public Iterator<E> takeWhile(Function<E, Boolean> predicate) {
        return new TakeWhile<>(this, predicate);
    }

    public Iterator<E> take(int nToTake) {
        return new Take<>(this, nToTake);
    }

    public Iterator<E> chain(Iterator<E> other) {
        return new Chain<>(this, other);
    }

    public List<E> collectToList() {
        List<E> collection = new ArrayList<>();

        while (hasNext()) {
            collection.add(next());
        }

        return collection;
    }

    public static class JavaAdapter<E> extends Iterator<E> {
        private java.util.Iterator<E> javaIterator;

        public JavaAdapter(java.util.Iterator<E> javaIterator) {
            this.javaIterator = javaIterator;
        }

        @Override
        public boolean hasNext() {
            return javaIterator.hasNext();
        }

        @Override
        public E next() {
            return javaIterator.next();
        }
    }

    public static class FlatMap<E, T> extends Iterator<T> {
        private Function<E, Iterator<T>> toIterator;
        private Iterator<E> toFlatten;
        private Iterator<T> curIterator;

        public FlatMap(Iterator<E> toFlatten, Function<E, Iterator<T>> toIterator) {
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

    public static class Map<E, T> extends Iterator<T> {
        private Iterator<E> toMap;
        private Function<E, T> mappingFunction;

        public Map(Iterator<E> toMap, Function<E, T> mappingFunction) {
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

    public static class Once<E> extends Iterator<E> {
        private boolean hasRun;
        private Function<Void, E> genElement;

        public Once(Function<Void, E> genElement) {
            this.genElement = genElement;
            hasRun = false;
        }

        @Override
        public boolean hasNext() {
            return !hasRun;
        }

        @Override
        public E next() {
            hasRun = true;
            return genElement.apply(null);
        }
    }

    public static class Repeat extends Iterator<Void> {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Void next() {
            return null;
        }
    }

    public static class Take<E> extends Iterator<E> {
        private int nToTake;
        private Iterator<E> takeFrom;

        public Take(Iterator<E> takeFrom, int nToTake) {
            this.takeFrom = takeFrom;
            this.nToTake = nToTake;
        }

        @Override
        public boolean hasNext() {
            return nToTake > 0;
        }

        @Override
        public E next() {
            nToTake--;
            return takeFrom.next();
        }
    }
    public static class TakeWhile<E> extends Iterator<E> {
        boolean hasFinished;
        E item;
        Iterator<E> toTake;
        Function<E, Boolean> precondition;

        public TakeWhile(Iterator<E> toTake, Function<E, Boolean> precondition) {
            this.toTake = toTake;
            this.precondition = precondition;
            this.hasFinished = toTake.hasNext();

            if (!this.hasFinished) {
                this.item = toTake.next();
            }
        }

        @Override
        public boolean hasNext() {
            return !hasFinished;
        }

        @Override
        public E next() {
            E prevItem = item;
            hasFinished = precondition.apply(item);

            if (!hasFinished) {
                if (toTake.hasNext()) {
                    item = toTake.next();
                } else {
                    hasFinished = true;
                }
                return prevItem;
            }
            return null;
        }
    }

    public static class Empty<E> extends Iterator<E> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    public static class Chain<E> extends Iterator<E> {
        private Iterator<E> begin;
        private Iterator<E> end;

        public Chain(Iterator<E> begin, Iterator<E> end) {
            begin = begin;
            end = end;
        }

        @Override
        public boolean hasNext() {
            return begin.hasNext() || end.hasNext();
        }

        @Override
        public E next() {
            if (begin.hasNext()) {
                return begin.next();
            }
            return end.next();
        }
    }

}
