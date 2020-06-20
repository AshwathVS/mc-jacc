package parser;

public class FunctionReturn implements BaseStatement {
    private RhsExpression returnValue;

    public FunctionReturn(RhsExpression returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public BaseStatementType getStatementType() {
        return BaseStatementType.RETURN;
    }

    public RhsExpression getReturnValue() {
        return returnValue;
    }
}
