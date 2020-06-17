package parser.numerics;

import parser.ParseTreePrinter;
import core.Type;

import java.util.Arrays;
import java.util.List;

/**
 * Operator will be null and the value will be filled for leaf nodes and vice versa parent nodes.
 */
public class NumericExpressionNode implements ParseTreePrinter<NumericExpressionNode> {

    private Type operator;

    private NumericExpressionNode left, right;

    public NumericExpressionNode(Type operator, NumericExpressionNode left, NumericExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Number evaluate() {
        switch (operator) {
            case ADD: {
                return left.evaluate().doubleValue() + right.evaluate().doubleValue();
            }
            case SUBTRACT: {
                return left.evaluate().doubleValue() - right.evaluate().doubleValue();
            }
            case MULTIPLY: {
                return left.evaluate().doubleValue() * right.evaluate().doubleValue();
            }
            case DIVIDE: {
                return left.evaluate().doubleValue() / right.evaluate().doubleValue();
            }
            case MODULUS: {
                return left.evaluate().doubleValue() % right.evaluate().doubleValue();
            }
        }

        // TODO: Throw exception here instead of returning null
        return null;
    }

    @Override
    public String getValue() {
        return operator.toString();
    }

    @Override
    public List<NumericExpressionNode> getChildren() {
        return Arrays.asList(this.left, this.right);
    }

    public Type getOperator() {
        return this.operator;
    }
}
