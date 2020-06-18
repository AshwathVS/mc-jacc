package parser.controlAndLoopStatements;

import parser.*;

public class ForNode extends BaseControlStatement {
    private VariableDeclarationNode initialisationNode;

    private RhsValueNode incrementNode;

    public ForNode(VariableDeclarationNode initialisationNode, RhsValueNode conditionStatement, RhsValueNode incrementNode, BlockNode blockNode) {
        super(blockNode, conditionStatement);
        this.initialisationNode = initialisationNode;
        this.incrementNode = incrementNode;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.FOR;
    }
}
