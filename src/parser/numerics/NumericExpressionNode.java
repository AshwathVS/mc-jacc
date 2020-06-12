package parser.numerics;

import core.Token;
import core.Type;

/**
 * Operator will be null and the value will be filled for leaf nodes and vice versa parent nodes.
 */
public class NumericExpressionNode {

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

}
