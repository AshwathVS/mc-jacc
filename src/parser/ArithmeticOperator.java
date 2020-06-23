package parser;

import core.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ArithmeticOperator {
    NULL(Integer.MAX_VALUE, null),
    MODULUS(1, Type.MODULUS),
    EXPONENTIAL_OPERATOR(2, Type.B_XOR),
    MULTIPLICATION(3, Type.MULTIPLY),
    DIVISION(3, Type.DIVIDE),
    ADDITION(4, Type.ADD),
    SUBTRACTION(4, Type.SUBTRACT),;

    private int precedence;

    private Type operator;

    private static Map<Type, ArithmeticOperator> operatorPrecedenceMap;

    ArithmeticOperator(int precedence, Type operator) {
        this.precedence = precedence;
        this.operator = operator;
    }

    public static ArithmeticOperator getPrecedenceForOperator(Type type) {
        if (null == operatorPrecedenceMap) {
            operatorPrecedenceMap = new HashMap<>(20);
            for (ArithmeticOperator iter : ArithmeticOperator.values()) {
                operatorPrecedenceMap.put(iter.getOperator(), iter);
            }
        }
        return operatorPrecedenceMap.get(type);
    }

    public static List<Type> getOperatorsWithEqualPrecedence(int precedence) {
        List<Type> operators = new ArrayList<>(5);
        for(ArithmeticOperator iter : ArithmeticOperator.values()) {
            if(iter.getPrecedence() == precedence) operators.add(iter.getOperator());
        }
        return operators;
    }

    public int getPrecedence() {
        return precedence;
    }

    public Type getOperator() {
        return this.operator;
    }

    public static List<Type> getOperatorsWithGreaterPrecedence(int precedence) {
        List<Type> operators = new ArrayList<>(10);
        for(ArithmeticOperator iter: ArithmeticOperator.values()) {
            if(iter.precedence <= precedence) {
                operators.add(iter.getOperator());
            }
        }
        return operators;
    }
}
