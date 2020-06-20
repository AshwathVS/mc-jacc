package parser.symbolTable;

import parser.VariableDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionSymbolTableEntry {
    String functionName;

    List<VariableDeclaration> arguments;

    public FunctionSymbolTableEntry(String functionName, Map<String, VariableDeclaration> arguments) {
        this.functionName = functionName;
        this.arguments = new ArrayList<>(10);
        for(Map.Entry<String, VariableDeclaration> entry : arguments.entrySet()) {
            this.arguments.add(entry.getValue());
        }
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
