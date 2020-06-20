package parser;

import java.util.List;

public class FunctionCall implements BaseStatement, RhsExpression {
    private String functionName;
    List<IdentifierNode> arguments;

    public FunctionCall(String functionName, List<IdentifierNode> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<IdentifierNode> getArguments() {
        return arguments;
    }

    @Override
    public BaseStatementType getStatementType() {
        return BaseStatementType.FUNCTION_CALL;
    }

    @Override
    public BinaryExpressionType getExpressionType() {
        return BinaryExpressionType.FUNCTION_CALL;
    }
}
