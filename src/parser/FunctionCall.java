package parser;

import java.util.List;

public class FunctionCall implements BaseStatement, RhsExpression {
    private String functionName;
    private List<BinaryExpression> arguments;

    public FunctionCall(String functionName, List<BinaryExpression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<BinaryExpression> getArguments() {
        return arguments;
    }

    @Override
    public BaseStatementType getStatementType() {
        return BaseStatementType.FUNCTION_CALL;
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.FUNCTION_CALL;
    }
}
