package parser.controlAndLoopStatements;

import parser.BlockNode;
import parser.RhsValueNode;
import parser.StatementType;

public class WhileNode extends BaseControlStatement {

    public WhileNode(BlockNode blockNode, RhsValueNode conditionStatement) {
        super(blockNode, conditionStatement);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.WHILE;
    }
}
