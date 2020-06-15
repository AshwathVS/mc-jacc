package parser.common;

import java.util.List;

public interface ParseTreePrinter<T extends ParseTreePrinter> {
    String getValue();

    List<T> getChildren();
}
