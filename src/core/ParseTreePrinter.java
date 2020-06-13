package core;

public interface ParseTreePrinter<T> {
    String getValue();
    T getLeft();
    T getRight();
}
