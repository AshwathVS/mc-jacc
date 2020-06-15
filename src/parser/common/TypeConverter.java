package parser.common;

import core.Type;

public class TypeConverter {

    public enum DataType {
        INTEGER,
        DOUBLE,
        BOOLEAN,
        CHARACTER,
        STRING,
        ENUM,
        UNKNOWN,
    }

    public static DataType getDataType(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case INTEGER: return DataType.INTEGER;
            case DOUBLE: return DataType.DOUBLE;
            case CHARACTER: return DataType.CHARACTER;
            case BOOLEAN: return DataType.BOOLEAN;
            case STRING: return DataType.STRING;
            case ENUM: return DataType.ENUM;
        }
        return null;
    }

    /**
     * Arithmetic Operators:
     */
    public enum ArithmeticOperator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        MODULUS,
        INCREMENT,
        DECREMENT,
    }

    public static ArithmeticOperator getArithmeticOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case ADD: return ArithmeticOperator.ADD;
            case SUBTRACT: return ArithmeticOperator.SUBTRACT;
            case MULTIPLY: return ArithmeticOperator.MULTIPLY;
            case DIVIDE: return ArithmeticOperator.DIVIDE;
            case MODULUS: return ArithmeticOperator.MODULUS;
            case INCREMENT: return ArithmeticOperator.INCREMENT;
            case DECREMENT: return ArithmeticOperator.DECREMENT;
        }
        return null;
    }

    public enum UnaryOperator {
        INCREMENT,
        DECREMENT,
    }

    public static UnaryOperator getUnaryOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case INCREMENT: return UnaryOperator.INCREMENT;
            case DECREMENT: return UnaryOperator.DECREMENT;
        }
        return null;
    }

    /**
     * Relational Operators:
     */
    public enum RelationalOperator {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        LESS_THAN,
        GREATER_THAN_EQUAL,
        LESS_THAN_EQUAL,
    }

    public static RelationalOperator getRelationalOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case EQUALS: return RelationalOperator.EQUALS;
            case NOT_EQUALS: return RelationalOperator.NOT_EQUALS;
            case GREATER_THAN: return RelationalOperator.GREATER_THAN;
            case LESS_THAN: return RelationalOperator.LESS_THAN;
            case GREATER_THAN_EQUAL: return RelationalOperator.GREATER_THAN_EQUAL;
            case LESS_THAN_EQUAL: return RelationalOperator.LESS_THAN_EQUAL;
        }
        return null;
    }

    /**
     * Bitwise Operators:
     */
    public enum BitwiseOperator {
        B_AND,
        B_OR,
        B_NOT,
        B_XOR,
        B_LEFT_SHIFT,
        B_RIGHT_SHIFT,
        B_RIGHT_SHIFT_ZERO,
    }

    public static BitwiseOperator getBitwiseOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case B_AND: return BitwiseOperator.B_AND;
            case B_OR: return BitwiseOperator.B_OR;
            case B_NOT: return BitwiseOperator.B_NOT;
            case B_XOR: return BitwiseOperator.B_XOR;
            case B_LEFT_SHIFT: return BitwiseOperator.B_LEFT_SHIFT;
            case B_RIGHT_SHIFT: return BitwiseOperator.B_RIGHT_SHIFT;
            case B_RIGHT_SHIFT_ZERO: return BitwiseOperator.B_RIGHT_SHIFT_ZERO;

        }
        return null;
    }

    /**
     * Logical Operators:
     */
    public enum LogicalOperator {
        AND,
        OR,
        NOT,
    }

    public static LogicalOperator getLogicalOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case AND: return LogicalOperator.AND;
            case OR: return LogicalOperator.OR;
            case NOT: return LogicalOperator.NOT;
        }
        return null;
    }
    /**
     * Assignment Operators:
     */
    public enum AssignmentOperator {
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
    }

    public static AssignmentOperator getAssignmentOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case EQUAL_TO: return AssignmentOperator.EQUAL_TO;
            case ADD_EQUALS: return AssignmentOperator.ADD_EQUALS;
            case SUBTRACT_EQUALS: return AssignmentOperator.SUBTRACT_EQUALS;
            case PRODUCT_EQUALS: return AssignmentOperator.PRODUCT_EQUALS;
            case DIVIDE_EQUALS: return AssignmentOperator.DIVIDE_EQUALS;
            case MODULUS_EQUALS: return AssignmentOperator.MODULUS_EQUALS;
            case B_LEFT_SHIFT_EQUALS: return AssignmentOperator.B_LEFT_SHIFT_EQUALS;
            case B_RIGHT_SHIFT_EQUALS: return AssignmentOperator.B_RIGHT_SHIFT_EQUALS;
            case B_AND_EQUALS: return AssignmentOperator.B_AND_EQUALS;
            case B_XOR_EQUALS: return AssignmentOperator.B_XOR_EQUALS;
            case B_OR_EQUALS: return AssignmentOperator.B_OR_EQUALS;
        }
        return null;
    }
    /**
     * Decision making Operators:
     */
    public enum DecisionMakingOperator {
        IF,
        ELSE,
        ELSE_IF,
        SWITCH,
        CASE,
        CONTINUE,
        BREAK,
        GOTO,
    }
    public static DecisionMakingOperator getDecisionMakingOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case IF: return DecisionMakingOperator.IF;
            case ELSE: return DecisionMakingOperator.ELSE;
            case ELSE_IF: return DecisionMakingOperator.ELSE_IF;
            case SWITCH: return DecisionMakingOperator.SWITCH;
            case CASE: return DecisionMakingOperator.CASE;
            case CONTINUE: return DecisionMakingOperator.CONTINUE;
            case BREAK: return DecisionMakingOperator.BREAK;
            case GOTO: return DecisionMakingOperator.GOTO;
        }
        return null;
    }
    /**
     * Loop Operators:
     */
    public enum LoopingOperator {
        FOR,
        WHILE,
        DO,
    }

    public static LoopingOperator getLoopingOperator(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case FOR: return LoopingOperator.FOR;
            case WHILE: return LoopingOperator.WHILE;
            case DO: return LoopingOperator.DO;
        }
        return null;
    }

    /**
     * Additional Operators:
     */
    public enum OtherOperator {
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
    }
    public static OtherOperator getOtherOperators(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case COLON: return OtherOperator.COLON;
            case SEMI_COLON: return OtherOperator.SEMI_COLON;
            case OPEN_BRACKET: return OtherOperator.OPEN_BRACKET;
            case CLOSE_BRACKET: return OtherOperator.CLOSE_BRACKET;
            case OPEN_SQUARE_BRACKET: return OtherOperator.OPEN_SQUARE_BRACKET;
            case CLOSE_SQUARE_BRACKET: return OtherOperator.CLOSE_SQUARE_BRACKET;
            case OPEN_CURLY_BRACE: return OtherOperator.OPEN_CURLY_BRACE;
            case CLOSE_CURLY_BRACE: return OtherOperator.CLOSE_CURLY_BRACE;
            case SINGLE_QUOTE: return OtherOperator.SINGLE_QUOTE;
            case DOUBLE_QUOTE: return OtherOperator.DOUBLE_QUOTE;
            case FORWARD_SLASH: return OtherOperator.FORWARD_SLASH;
            case BACKWARD_SLASH: return OtherOperator.BACKWARD_SLASH;
            case COMMA: return OtherOperator.COMMA;
            case DOT: return OtherOperator.DOT;
            case UNDER_SCORE: return OtherOperator.UNDER_SCORE;
            case HYPHEN: return OtherOperator.HYPHEN;
            case HASH_TAG: return OtherOperator.HASH_TAG;
            case WHITE_SPACE: return OtherOperator.WHITE_SPACE;
            case EOF: return OtherOperator.EOF;
        }
        return null;
    }

    /**
     * L/R Value
     */
    public enum Operand {
        ID,
        NUMBER,
        TEXT,
    }

    public static Operand getOperand(Type type) {
        if(null == type) throw new NullPointerException("Token cannot be null");
        switch (type) {
            case ID: return Operand.ID;
            case NUMBER: return Operand.NUMBER;
            case TEXT: return Operand.TEXT;
        }
        return null;
    }

}
