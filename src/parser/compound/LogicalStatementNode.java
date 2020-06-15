package parser.compound;

import parser.common.TypeConverter;
import parser.compound.core.CompoundExpression;
import parser.compound.core.CompoundStatementType;

public class LogicalStatementNode implements CompoundExpression {
    private ArithmeticStatementNode left, right;

    private TypeConverter.LogicalOperator logicalOperator;

    public LogicalStatementNode(ArithmeticStatementNode left, ArithmeticStatementNode right, TypeConverter.LogicalOperator logicalOperator) {
        this.left = left;
        this.right = right;
        this.logicalOperator = logicalOperator;
    }

    @Override
    public CompoundStatementType getCompoundStatementType() {
        return CompoundStatementType.LOGICAL;
    }

    public ArithmeticStatementNode getLeft() {
        return left;
    }

    public ArithmeticStatementNode getRight() {
        return right;
    }

    public TypeConverter.LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }
}
