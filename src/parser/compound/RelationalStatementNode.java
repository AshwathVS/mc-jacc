package parser.compound;

import parser.common.TypeConverter;
import parser.compound.core.CompoundExpression;
import parser.compound.core.CompoundStatementType;

public class RelationalStatementNode implements CompoundExpression {
    private ArithmeticStatementNode left, right;

    private TypeConverter.RelationalOperator operator;

    public RelationalStatementNode(ArithmeticStatementNode left, ArithmeticStatementNode right, TypeConverter.RelationalOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public CompoundStatementType getCompoundStatementType() {
        return CompoundStatementType.RELATIONAL;
    }

    public ArithmeticStatementNode getLeft() {
        return left;
    }

    public ArithmeticStatementNode getRight() {
        return right;
    }

    public TypeConverter.RelationalOperator getOperator() {
        return operator;
    }
}
