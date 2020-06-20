package parser;

public class BinaryExpression implements RhsExpression {
    private RhsExpression left, right;
    private Operator operator;

    public BinaryExpression(RhsExpression left, RhsExpression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public BinaryExpression(RhsExpression left) {
        this.left = left;
    }

    public RhsExpression getLeft() {
        return left;
    }

    public RhsExpression getRight() {
        return right;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public BinaryExpressionType getExpressionType() {
        return BinaryExpressionType.BINARY_EXPRESSION;
    }
}
