package parser;

public class VariableDeclaration implements BaseStatement {
    private IdentifierNode lhs;
    private RhsExpression rhs;

    public VariableDeclaration(IdentifierNode lhs, RhsExpression rhsExpression) {
        this.lhs = lhs;
        this.rhs = rhsExpression;
    }

    public IdentifierNode getLhs() {
        return lhs;
    }

    public RhsExpression getRhs() {
        return rhs;
    }

    @Override
    public BaseStatementType getStatementType() {
        return BaseStatementType.VARIABLE_DECLARATION;
    }
}
