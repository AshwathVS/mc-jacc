package parser.controlAndLoopStatements;

import parser.BaseStatement;
import parser.BinaryExpression;
import parser.StatementBlock;

public abstract class BaseControlStatement implements BaseStatement {
    private StatementBlock statementBlock;

    private BinaryExpression conditionStatement;

    public BaseControlStatement(BinaryExpression conditionStatement, StatementBlock statementBlock) {
        this.statementBlock = statementBlock;
        this.conditionStatement = conditionStatement;
    }

    public StatementBlock getStatementBlock() {
        return statementBlock;
    }

    public BinaryExpression getConditionStatement() {
        return conditionStatement;
    }
}
