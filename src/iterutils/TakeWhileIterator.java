package iterutils;

import java.util.function.Function;

public class TakeWhileIterator<E> extends Iterator<E> {
    boolean hasFinished;
    E item;
    Iterator<E> toTake;
    Function<E, Boolean> precondition;

    public TakeWhileIterator(Iterator<E> toTake, Function<E, Boolean> precondition) {
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
