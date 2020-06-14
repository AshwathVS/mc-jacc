package core;

public interface ParseTreePrinter<T extends ParseTreePrinter> {
    String getValue();

    T getLeft();

    T getRight();
}
