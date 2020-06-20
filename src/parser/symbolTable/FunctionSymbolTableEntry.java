package parser.symbolTable;

import parser.VariableDeclaration;

import java.util.List;

public class FunctionSymbolTableEntry {
    String functionName;

    List<VariableDeclaration> arguments;

    public FunctionSymbolTableEntry(String functionName, List<VariableDeclaration> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<VariableDeclaration> getArguments() {
        return arguments;
    }

    public Integer getArgumentCount() {
        return this.arguments.size();
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setArguments(List<VariableDeclaration> arguments) {
        this.arguments = arguments;
    }

}
