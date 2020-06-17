package parser.symbolTable;

import parser.IdentifierNode;
import java.util.List;

public class FunctionSymbolTableEntry {
    String functionName;

    List<IdentifierNode> arguments;

    public FunctionSymbolTableEntry(String functionName, List<IdentifierNode> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<IdentifierNode> getArguments() {
        return arguments;
    }

    public Integer getArgumentCount() {
        return this.arguments.size();
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setArguments(List<IdentifierNode> arguments) {
        this.arguments = arguments;
    }

}
