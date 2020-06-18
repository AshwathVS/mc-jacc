package parser;

public class VariableDeclarationNode implements StatementNode {
    private IdentifierNode lhs;
    private RhsValueNode rhs;

    public VariableDeclarationNode(IdentifierNode lhs, RhsValueNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IdentifierNode getLhs() {
        return lhs;
    }

    public RhsValueNode getRhs() {
        return rhs;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }
}
