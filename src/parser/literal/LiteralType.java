package parser.literal;

import core.Type;

public enum LiteralType {
    INTEGER_LITERAL,
    STRING_LITERAL,
    CHARACTER_LITERAL,
    BOOLEAN_LITERAL,
    DOUBLE_LITERAL,;

    public static LiteralType convertType(Type type) {
        if(null != type) {
            switch (type) {
                case INTEGER_LITERAL:
                    return INTEGER_LITERAL;
                case STRING_LITERAL:
                    return STRING_LITERAL;
                case CHARACTER_LITERAL:
                    return CHARACTER_LITERAL;
                case BOOLEAN_LITERAL:
                    return BOOLEAN_LITERAL;
                case DOUBLE_LITERAL:
                    return DOUBLE_LITERAL;
            }
        }
        return null;
    }

    public static Type[] getLiteralTypes() {
        return new Type[] {Type.INTEGER_LITERAL, Type.STRING_LITERAL, Type.CHARACTER_LITERAL, Type.BOOLEAN_LITERAL, Type.DOUBLE_LITERAL};
    }

}
