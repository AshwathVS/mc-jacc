package parser;

public class VariableAssignment implements BaseStatement {
    private IdentifierNode lhs;
    private RhsExpression rhs;

    public VariableAssignment(IdentifierNode lhs, RhsExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IdentifierNode getLhs() {
        return lhs;
    }

    public RhsExpression getRhs() {
        return rhs;
    }

    @Override
    public BaseStatementType getStatementType() {
        return BaseStatementType.VARIABLE_ASSIGNMENT;
    }
}
