package parser.controlAndLoopStatements;

import parser.BlockNode;
import parser.GenericExpressionNode;
import parser.StatementType;

public class IfNode extends BaseControlStatement {
    private IfNode elseBlock;

    public IfNode(GenericExpressionNode conditionStatement, BlockNode ifBlock, IfNode elseBlock) {
        super(ifBlock, conditionStatement);
        this.elseBlock = elseBlock;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.IF;
    }
}