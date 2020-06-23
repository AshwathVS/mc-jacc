package parser;

public class BinaryExpression implements RhsExpression, TreeExpression<RhsExpression, ArithmeticOperator> {
    private RhsExpression left, right;
    private ArithmeticOperator operator;

    public BinaryExpression(RhsExpression left, RhsExpression right, ArithmeticOperator operator) {
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

    public ArithmeticOperator getOperator() {
        return operator;
    }

    @Override
    public BinaryExpressionType getExpressionType() {
        return BinaryExpressionType.BINARY_EXPRESSION;
    }
}
