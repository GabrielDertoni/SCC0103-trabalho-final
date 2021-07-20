package interpreter;

public interface OutputDevice {
    void print(String msg);
    void interact();
    void move(Direction direction);
}
