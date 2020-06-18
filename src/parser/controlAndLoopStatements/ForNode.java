package parser.controlAndLoopStatements;

import parser.*;

public class ForNode extends BaseControlStatement {
    private VariableDeclarationNode initialisationNode;

    private GenericExpressionNode incrementNode;

    public ForNode(VariableDeclarationNode initialisationNode, GenericExpressionNode conditionStatement, GenericExpressionNode incrementNode, BlockNode blockNode) {
        super(blockNode, conditionStatement);
        this.initialisationNode = initialisationNode;
        this.incrementNode = incrementNode;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.FOR;
    }
}
