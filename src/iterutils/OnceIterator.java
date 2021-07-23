package iterutils;

import java.util.function.Function;

public class OnceIterator<E> extends Iterator<E> {
    private boolean hasRun;
    private Function<Void, E> genElement;

    public OnceIterator(Function<Void, E> genElement) {
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
