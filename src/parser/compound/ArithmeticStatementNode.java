package parser.compound;

import parser.common.TypeConverter;
import parser.compound.core.CompoundStatementType;
import parser.compound.core.CompoundExpression;

public class ArithmeticStatementNode implements CompoundExpression {

    private TypeConverter.ArithmeticOperator operator;

    private ArithmeticStatementNode left, right;

    public ArithmeticStatementNode(TypeConverter.ArithmeticOperator operator, ArithmeticStatementNode left, ArithmeticStatementNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public CompoundStatementType getCompoundStatementType() {
        return CompoundStatementType.ARITHMETIC;
    }

    public TypeConverter.ArithmeticOperator getOperator() {
        return operator;
    }

    public ArithmeticStatementNode getLeft() {
        return left;
    }

    public ArithmeticStatementNode getRight() {
        return right;
    }
}
