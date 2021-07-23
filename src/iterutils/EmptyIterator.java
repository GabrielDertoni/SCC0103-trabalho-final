package iterutils;

public class EmptyIterator<E> extends Iterator<E> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }
}
