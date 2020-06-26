package parser;

import core.Type;

public enum RelationalOperator {
    GREATER_THAN(Type.GREATER_THAN, ">"),
    LESS_THAN(Type.LESS_THAN, "<"),
    GREATER_THAN_EQUAL_TO(Type.GREATER_THAN_EQUAL, ">="),
    LESS_THAN_EQUAL_TO(Type.LESS_THAN_EQUAL, "<="),
    EQUALS(Type.EQUALS, "=="),
    NOT_EQUAL_TO(Type.NOT_EQUALS, "!="),;

    private Type type;

    private String value;

    RelationalOperator(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String value() {
        return this.value;
    }

    public static Type[] getTypes() {
        return new Type[] {Type.EQUALS, Type.GREATER_THAN, Type.LESS_THAN, Type.GREATER_THAN_EQUAL, Type.LESS_THAN_EQUAL, Type.NOT_EQUALS};
    }

    public static RelationalOperator getOperator(Type type) {
        for(RelationalOperator operator : values()) {
            if(operator.getType().equals(type)) return operator;
        }
        return null;
    }
}
