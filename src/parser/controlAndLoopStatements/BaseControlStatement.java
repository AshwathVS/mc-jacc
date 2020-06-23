package parser.controlAndLoopStatements;

import parser.BaseStatement;
import parser.BooleanExpression;
import parser.StatementBlock;

public abstract class BaseControlStatement implements BaseStatement {
    private StatementBlock statementBlock;

    private BooleanExpression conditionStatement;

    public BaseControlStatement(BooleanExpression conditionStatement, StatementBlock statementBlock) {
        this.statementBlock = statementBlock;
        this.conditionStatement = conditionStatement;
    }

    public StatementBlock getStatementBlock() {
        return statementBlock;
    }

    public BooleanExpression getConditionStatement() {
        return conditionStatement;
    }
}
