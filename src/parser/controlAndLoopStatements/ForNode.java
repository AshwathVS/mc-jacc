package parser.controlAndLoopStatements;

import parser.*;

public class ForNode extends BaseControlStatement {
    private BaseStatement initialisationNode;

    private VariableAssignment incrementNode;

    public ForNode(BaseStatement initialisationNode, BooleanExpression conditionStatement, VariableAssignment incrementNode, StatementBlock blockNode) {
        super(conditionStatement, blockNode);
        this.initialisationNode = initialisationNode;
        this.incrementNode = incrementNode;
    }

    @Override
    public BaseStatementType getStatementType() {
        return BaseStatementType.FOR;
    }
}
