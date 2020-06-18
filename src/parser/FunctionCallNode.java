package parser;

import java.util.List;

public class FunctionCallNode implements GenericExpressionNode, StatementNode {
    String functionName;
    List<IdentifierNode> argumentsUsed;

    public FunctionCallNode(String functionName, List<IdentifierNode> argumentsUsed) {
        this.functionName = functionName;
        this.argumentsUsed = argumentsUsed;
    }

    @Override
    public RhsValueType getRhsValueType() {
        return RhsValueType.FUNCTION_CALL;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<IdentifierNode> getArgumentsUsed() {
        return argumentsUsed;
    }
}
