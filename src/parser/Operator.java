package parser;

import core.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Operator {
    NULL(Integer.MAX_VALUE, null),
    MODULUS(1, Type.MODULUS),
    EXPONENTIAL_OPERATOR(2, Type.B_XOR),
    MULTIPLICATION(3, Type.MULTIPLY),
    DIVISION(3, Type.DIVIDE),
    ADDITION(4, Type.ADD),
    SUBTRACTION(4, Type.SUBTRACT),
    GREATER_THAN(5, Type.GREATER_THAN),
    LESS_THAN(5, Type.LESS_THAN),
    GREATER_THAN_EQUAL_TO(5, Type.GREATER_THAN_EQUAL),
    LESS_THAN_EQUAL_TO(5, Type.LESS_THAN_EQUAL),
    NOT_EQUAL_TO(5, Type.NOT_EQUALS),;

    private int precedence;

    private Type operator;

    private static Map<Type, Operator> operatorPrecedenceMap;

    Operator(int precedence, Type operator) {
        this.precedence = precedence;
        this.operator = operator;
    }

    public static Operator getPrecedenceForOperator(Type type) {
        if (null == operatorPrecedenceMap) {
            operatorPrecedenceMap = new HashMap<>(20);
            for (Operator iter : Operator.values()) {
                operatorPrecedenceMap.put(iter.getOperator(), iter);
            }
        }
        return operatorPrecedenceMap.get(type);
    }

    public static Type[] getOperatorsWithEqualPrecedence(int precedence) {
        List<Type> operators = new ArrayList<>(5);
        for(Operator iter : Operator.values()) {
            if(iter.getPrecedence() == precedence) operators.add(iter.getOperator());
        }
        return (Type[]) operators.toArray();
    }

    public int getPrecedence() {
        return precedence;
    }

    public Type getOperator() {
        return this.operator;
    }

    public static Type[] getOperatorsWithGreaterPrecedence(int precedence) {
        List<Type> operators = new ArrayList<>(10);
        for(Operator iter: Operator.values()) {
            if(iter.precedence <= precedence) {
                operators.add(iter.getOperator());
            }
        }
        return (Type[]) operators.toArray();
    }
}
