package parser.controlAndLoopStatements;

import parser.BlockNode;
import parser.GenericExpressionNode;
import parser.StatementType;

public class DoWhileNode extends BaseControlStatement {
    public DoWhileNode(BlockNode blockNode, GenericExpressionNode conditionStatement) {
        super(blockNode, conditionStatement);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.DO_WHILE;
    }
}
