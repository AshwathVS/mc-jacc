package parser.common;

public class ParseResult<T> {
    private T parseTree;

    private boolean parseSuccess;

    public static final ParseResult FAILED_PARSE = new ParseResult();

    public ParseResult(T parseTree) {
        this.parseTree = parseTree;
        parseSuccess = true;
    }

    public ParseResult() {
        this.parseSuccess = false;
    }

    public T getParseTree() {
        return parseTree;
    }

    public boolean isParseSuccess() {
        return parseSuccess;
    }

}
