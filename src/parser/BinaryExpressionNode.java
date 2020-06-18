package parser;

/**
 * Grammar followed is as follows:
 * E -> T | T + T | T - T
 * T -> F | F * F | F / F
 * F -> ID | LITERAL | FUNCTION_CALL | (E)
 */
public class BinaryExpressionNode implements RhsValueNode, StatementNode {
    private RhsValueNode left, right;

    private Operator operator;

    public BinaryExpressionNode(RhsValueNode left, RhsValueNode right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public RhsValueType getRhsValueType() {
        return RhsValueType.BINARY_EXPRESSION;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }

    public RhsValueNode getLeft() {
        return left;
    }

    public RhsValueNode getRight() {
        return right;
    }

    public Operator getOperator() {
        return operator;
    }
}
