package parser;

public enum  BaseStatementType {
    VARIABLE_ASSIGNMENT,
    VARIABLE_DECLARATION,
    FUNCTION_CALL,
    RETURN,

    // Skip statement
    BREAK,
    CONTINUE,

    // Control statement
    IF,

    // Loop statement
    FOR,
    WHILE,
    DO_WHILE,
    ;
}
