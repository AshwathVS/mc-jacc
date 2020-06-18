package parser.controlAndLoopStatements;

import parser.BlockNode;
import parser.RhsValueNode;
import parser.StatementType;

public class DoWhileNode extends BaseControlStatement {
    public DoWhileNode(BlockNode blockNode, RhsValueNode conditionStatement) {
        super(blockNode, conditionStatement);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.DO_WHILE;
    }
}
