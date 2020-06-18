package parser.controlAndLoopStatements;

import parser.BlockNode;
import parser.RhsValueNode;
import parser.StatementNode;

public abstract class BaseControlStatement implements StatementNode {
    private BlockNode blockNode;

    private RhsValueNode conditionStatement;

    public BaseControlStatement(BlockNode blockNode, RhsValueNode conditionStatement) {
        this.blockNode = blockNode;
        this.conditionStatement = conditionStatement;
    }

    public BlockNode getBlockNode() {
        return blockNode;
    }

    public void setBlockNode(BlockNode blockNode) {
        this.blockNode = blockNode;
    }

    public RhsValueNode getConditionStatement() {
        return conditionStatement;
    }

    public void setConditionStatement(RhsValueNode conditionStatement) {
        this.conditionStatement = conditionStatement;
    }
}
