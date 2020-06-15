package parser.compound;

import parser.common.TypeConverter;
import parser.common.IdNode;
import parser.compound.core.CompoundExpression;
import parser.compound.core.CompoundStatementType;

public class UnaryStatementNode extends AssignmentStatementNode implements CompoundExpression {
    TypeConverter.UnaryOperator operator;

    public UnaryStatementNode(IdNode lhs, TypeConverter.UnaryOperator operator) {
        super(lhs, null);
        this.operator = operator;
    }

    @Override
    public CompoundStatementType getCompoundStatementType() {
        return CompoundStatementType.UNARY_STATEMENT;
    }

    public TypeConverter.UnaryOperator getOperator() {
        return operator;
    }
}
