package parser;

public class VariableDeclarationNode implements StatementNode {
    private IdentifierNode lhs;
    private GenericExpressionNode rhs;

    public VariableDeclarationNode(IdentifierNode lhs, GenericExpressionNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IdentifierNode getLhs() {
        return lhs;
    }

    public GenericExpressionNode getRhs() {
        return rhs;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }
}
