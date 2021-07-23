package iterutils;

public class RepeatIterator extends Iterator<Void> {
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Void next() {
        return null;
    }
}
