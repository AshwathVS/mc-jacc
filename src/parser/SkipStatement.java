package parser;

public class SkipStatement implements BaseStatement {

    private SkipStatementType skipStatementType;

    public SkipStatement(SkipStatementType skipStatementType) {
        this.skipStatementType = skipStatementType;
    }

    @Override
    public BaseStatementType getStatementType() {
        switch (skipStatementType) {
            case BREAK: return BaseStatementType.BREAK;
            case CONTINUE: return BaseStatementType.CONTINUE;
            default: return null;
        }
    }
}
