package parser.controlAndLoopStatements;

import parser.BlockNode;
import parser.GenericExpressionNode;
import parser.StatementType;

public class WhileNode extends BaseControlStatement {

    public WhileNode(BlockNode blockNode, GenericExpressionNode conditionStatement) {
        super(blockNode, conditionStatement);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.WHILE;
    }
}
