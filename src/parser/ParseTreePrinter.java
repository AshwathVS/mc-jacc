package parser;

import java.util.List;

public interface ParseTreePrinter<T extends ParseTreePrinter> {
    Object getValue();

    List<T> getChildren();
}
