package parser.controlAndLoopStatements;

import parser.BaseStatementType;
import parser.BinaryExpression;
import parser.StatementBlock;

public class WhileNode extends BaseControlStatement {

    private boolean isDoWhile;

    public WhileNode(BinaryExpression conditionStatement, StatementBlock block, boolean isDoWhile) {
        super(conditionStatement, block);
        this.isDoWhile = isDoWhile;
    }

    @Override
    public BaseStatementType getStatementType() {
        if(isDoWhile) return BaseStatementType.DO_WHILE;
        else return BaseStatementType.WHILE;
    }
}
