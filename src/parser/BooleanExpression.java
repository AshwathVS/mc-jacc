package parser;

public class BooleanExpression implements RhsExpression, TreeExpression<BooleanExpression, BooleanOperator> {

    private BooleanExpression left, right;

    private BooleanOperator booleanOperator;

    public BooleanExpression(BooleanExpression left, BooleanExpression right, BooleanOperator booleanOperator) {
        this.left = left;
        this.right = right;
        this.booleanOperator = booleanOperator;
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.BOOLEAN_EXPRESSION;
    }

    public BooleanExpression getLeft() {
        return left;
    }

    public BooleanExpression getRight() {
        return right;
    }

    public BooleanOperator getOperator() {
        return booleanOperator;
    }
}
