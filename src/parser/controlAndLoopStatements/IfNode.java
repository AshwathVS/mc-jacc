package parser.controlAndLoopStatements;

import parser.BaseStatementType;
import parser.BooleanExpression;
import parser.StatementBlock;

public class IfNode extends BaseControlStatement {
    private IfNode elseBlock;

    public IfNode(BooleanExpression conditionStatement, StatementBlock ifBlock, IfNode elseBlock) {
        super(conditionStatement, ifBlock);
        this.elseBlock = elseBlock;
    }

    @Override
    public BaseStatementType getStatementType() {
        return BaseStatementType.IF;
    }

    public IfNode getElseBlock() {
        return elseBlock;
    }

    public void setElseBlock(IfNode elseBlock) {
        this.elseBlock = elseBlock;
    }
}
