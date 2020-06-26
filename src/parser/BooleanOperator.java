package parser;

import core.Type;

import java.util.HashMap;
import java.util.Map;

public enum BooleanOperator {
    NULL(Integer.MAX_VALUE, null, null),
    NOT(1, Type.NOT, "!"),
    AND(2, Type.AND, "&&"),
    OR(3, Type.OR, "||"),;

    private int precedence;

    private Type type;

    private String value;

    private static Map<Type, BooleanOperator> operatorPrecedenceMap;

    BooleanOperator(int precedence, Type type, String value) {
        this.precedence = precedence;
        this.type = type;
        this.value = value;
    }

    public int getPrecedence() {
        return precedence;
    }

    public Type getType() {
        return type;
    }

    public String value() {
        return this.value;
    }

    public static Type[] getTypes() {
        return new Type[] {Type.NOT, Type.AND, Type.OR};
    }

    public static BooleanOperator getOperator(Type type) {
        for (BooleanOperator operator : values()) {
            if(type.equals(operator.getType())) return operator;
        }
        return null;
    }

    public static BooleanOperator getPrecedenceForOperator(Type type) {
        if (null == operatorPrecedenceMap) {
            operatorPrecedenceMap = new HashMap<>(20);
            for (BooleanOperator iter : BooleanOperator.values()) {
                operatorPrecedenceMap.put(iter.getType(), iter);
            }
        }
        return operatorPrecedenceMap.get(type);
    }

}
