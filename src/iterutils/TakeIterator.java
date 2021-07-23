package iterutils;

public class TakeIterator<E> extends Iterator<E> {
    private int nToTake;
    private Iterator<E> takeFrom;

    public TakeIterator(Iterator<E> takeFrom, int nToTake) {
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
