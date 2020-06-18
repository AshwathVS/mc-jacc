package parser.controlAndLoopStatements;

import parser.BlockNode;
import parser.GenericExpressionNode;
import parser.StatementNode;

public abstract class BaseControlStatement implements StatementNode {
    private BlockNode blockNode;

    private GenericExpressionNode conditionStatement;

    public BaseControlStatement(BlockNode blockNode, GenericExpressionNode conditionStatement) {
        this.blockNode = blockNode;
        this.conditionStatement = conditionStatement;
    }

    public BlockNode getBlockNode() {
        return blockNode;
    }

    public void setBlockNode(BlockNode blockNode) {
        this.blockNode = blockNode;
    }

    public GenericExpressionNode getConditionStatement() {
        return conditionStatement;
    }

    public void setConditionStatement(GenericExpressionNode conditionStatement) {
        this.conditionStatement = conditionStatement;
    }
}
