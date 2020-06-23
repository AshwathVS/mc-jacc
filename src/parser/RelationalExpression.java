package parser;

public class RelationalExpression extends BooleanExpression implements RhsExpression {

    private BinaryExpression left, right;

    private RelationalOperator operator;

    public RelationalExpression(BinaryExpression left, BinaryExpression right, RelationalOperator operator) {
        super(null, null, null);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public BinaryExpression getLeftExp() {
        return this.left;
    }

    public BinaryExpression getRightExp() {
        return this.right;
    }

    public RelationalOperator getRelationalOperator() {
        return this.operator;
    }

    @Override
    public BinaryExpressionType getExpressionType() {
        return BinaryExpressionType.RELATIONAL_EXPRESSION;
    }
}
