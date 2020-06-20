package core;

public class Token {
    private Type type;
    private String value;
    private int lineNumber;
    private int columnNumber;

    public Token(Type type, int lineNumber, int columnNumber, String value) {
        this.type = type;
        this.value = value;
        this.lineNumber = lineNumber + 1; // 1 - indexed
        this.columnNumber = columnNumber + 1; // 1 - indexed
    }

    public Token(Type type, int lineNumber, int columnNumber) {
        this.type = type;
        this.value = null;
        this.lineNumber = lineNumber + 1;
        this.columnNumber = columnNumber + 1;
    }

    public Type getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
