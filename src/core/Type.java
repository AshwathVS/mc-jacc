package core;

public enum Type {
    /**
     * Data types:
     */
    INTEGER,
    DOUBLE,
    CHARACTER,
    BOOLEAN,
    STRING,

    /**
     * Arithmetic Operators:
     */
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MODULUS,

    /**
     * Increment and Decrement Operators:
     */
    INCREMENT,
    DECREMENT,

    /**
     * Relational Operators:
     */
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,


    /**
     * Bitwise Operators:
     */
    B_AND,
    B_OR,
    B_NOT,
    B_XOR,
    B_LEFT_SHIFT,
    B_RIGHT_SHIFT,
    B_RIGHT_SHIFT_ZERO,

    /**
     * Logical Operators:
     */
    AND,
    OR,
    NOT,

    /**
     * Assignment Operators:
     */
    EQUAL_TO,
    ADD_EQUALS,
    SUBTRACT_EQUALS,
    PRODUCT_EQUALS,
    DIVIDE_EQUALS,
    MODULUS_EQUALS,
    B_LEFT_SHIFT_EQUALS,
    B_RIGHT_SHIFT_EQUALS,
    B_AND_EQUALS,
    B_XOR_EQUALS,
    B_OR_EQUALS,


    /**
     * Decision making Operators:
     */
    IF,
    ELSE,
    ELSE_IF,
    SWITCH,
    CASE,
    CONTINUE,
    BREAK,
    GOTO,

    /**
     * Loop Operators:
     */
    FOR,
    WHILE,
    DO,


    /**
     * Additional Operators:
     */
    COLON,
    SEMI_COLON,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    OPEN_SQUARE_BRACKET,
    CLOSE_SQUARE_BRACKET,
    OPEN_CURLY_BRACE,
    CLOSE_CURLY_BRACE,
    SINGLE_QUOTE,
    DOUBLE_QUOTE,
    FORWARD_SLASH,
    BACKWARD_SLASH,
    COMMA,
    DOT,
    UNDER_SCORE,
    HYPHEN,
    HASH_TAG,
    WHITE_SPACE,
    EOF,

    /**
     * L/R Value
     */
    ID,
    INTEGER_LITERAL,
    STRING_LITERAL,
    CHARACTER_LITERAL,
    BOOLEAN_LITERAL,
    DOUBLE_LITERAL,;

    public static Type[] getDataTypeOperators() {
        return new Type[] {INTEGER, DOUBLE, CHARACTER, STRING, BOOLEAN};
    }

};
