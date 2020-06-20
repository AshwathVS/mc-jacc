package parser;

import parser.symbolTable.SymbolTable;
import parser.symbolTable.VariableSymbolTableEntry;

import java.util.*;

public class FunctionDeclaration extends SymbolTable {
    private String functionName;
    private Map<String, VariableDeclaration> arguments;
    private StatementBlock functionBlock;
    private Integer symbolTableReference;
    private DataType returnType;

    public FunctionDeclaration(SymbolTable symbolTable, String functionName, DataType returnType) {
        super(symbolTable);
        this.functionName = functionName;
        this.returnType = returnType;
        this.arguments = new HashMap<>(10);
    }

    public String getFunctionName() {
        return functionName;
    }

    public Map<String, VariableDeclaration> getArguments() {
        return arguments;
    }

    public StatementBlock getFunctionBlock() {
        return functionBlock;
    }

    public void setSymbolTableReference(Integer symbolTableReference) {
        this.symbolTableReference = symbolTableReference;
    }

    public Integer getSymbolTableReference() {
        return symbolTableReference;
    }

    public DataType getReturnType() {
        return returnType;
    }


    public void addArgument(VariableDeclaration variableDeclaration) {
        this.arguments.put(variableDeclaration.getLhs().getIdentifierName(), variableDeclaration);
        addVariable(new VariableSymbolTableEntry(variableDeclaration.getLhs().getIdentifierName(), variableDeclaration.getLhs().getDataType(), variableDeclaration.getRhs()));
    }

    public void setFunctionBlock(StatementBlock functionBlock) {
        this.functionBlock = functionBlock;
    }
}
