package parser;

/**
 * Grammar followed is as follows:
 * E -> T | T + T | T - T
 * T -> F | F * F | F / F
 * F -> ID | LITERAL | FUNCTION_CALL | (E)
 */
public class BinaryExpressionNode implements GenericExpressionNode, StatementNode {
    private GenericExpressionNode left, right;

    private Operator operator;

    public BinaryExpressionNode(GenericExpressionNode left, GenericExpressionNode right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public RhsValueType getGenericExpressionType() {
        return RhsValueType.BINARY_EXPRESSION;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }

    public GenericExpressionNode getLeft() {
        return left;
    }

    public GenericExpressionNode getRight() {
        return right;
    }

    public Operator getOperator() {
        return operator;
    }
}
