package parser.compound;

import parser.common.IdNode;
import parser.compound.core.CompoundStatementType;
import parser.compound.core.CompoundExpression;

public class AssignmentStatementNode implements CompoundExpression {

    protected IdNode lhs;

    private ArithmeticStatementNode rhs;

    public AssignmentStatementNode(IdNode lhs, ArithmeticStatementNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public CompoundStatementType getCompoundStatementType() {
        return CompoundStatementType.ASSIGNMENT;
    }

    public IdNode getLhs() {
        return lhs;
    }

    public ArithmeticStatementNode getRhs() {
        return rhs;
    }
}
