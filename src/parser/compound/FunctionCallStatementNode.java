package parser.compound;

import parser.common.FunctionNode;
import parser.compound.core.CompoundExpression;
import parser.compound.core.CompoundStatementType;

public class FunctionCallStatementNode implements CompoundExpression {
    private FunctionNode functionNode;

    public FunctionCallStatementNode(FunctionNode functionNode) {
        this.functionNode = functionNode;
    }

    @Override
    public CompoundStatementType getCompoundStatementType() {
        return CompoundStatementType.FUNCTION_CALL;
    }

    public FunctionNode getFunctionNode() {
        return this.functionNode;
    }
}
