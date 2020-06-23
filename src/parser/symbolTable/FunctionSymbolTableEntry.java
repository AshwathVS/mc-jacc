package parser.symbolTable;

import parser.DataType;
import parser.FunctionDeclaration;
import parser.StatementBlock;
import parser.VariableDeclaration;

import java.util.Map;

public class FunctionSymbolTableEntry {

    private String functionName;

    private Map<String, VariableDeclaration> arguments;

    private DataType returnType;

    private StatementBlock functionBlock;

    public FunctionSymbolTableEntry(FunctionDeclaration functionDeclaration) {
        this.functionName = functionDeclaration.getFunctionName();
        this.arguments = functionDeclaration.getArguments();
        this.returnType = functionDeclaration.getReturnType();
        this.functionBlock = functionDeclaration.getFunctionBlock();
    }

    public String getFunctionName() {
        return functionName;
    }

    public Map<String, VariableDeclaration> getArguments() {
        return arguments;
    }

    public DataType getReturnType() {
        return returnType;
    }

    public StatementBlock getFunctionBlock() {
        return functionBlock;
    }

    public Integer getArgumentCount() {
        return this.arguments.size();
    }
}
