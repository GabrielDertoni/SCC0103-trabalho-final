package iterutils;

public class JavaIteratorAdapter<E> extends Iterator<E> {
    private java.util.Iterator<E> javaIterator;

    public JavaIteratorAdapter(java.util.Iterator<E> javaIterator) {
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
