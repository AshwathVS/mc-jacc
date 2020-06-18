package parser.symbolTable;

import parser.DataType;
import parser.IdentifierNode;
import parser.GenericExpressionNode;
import parser.VariableDeclarationNode;

public class VariableSymbolTableEntry {
    String variableName;
    DataType dataType;
    GenericExpressionNode value;

    public VariableSymbolTableEntry(String variableName, DataType dataType, GenericExpressionNode value) {
        this.variableName = variableName;
        this.dataType = dataType;
        this.value = value;
    }

    public VariableSymbolTableEntry(VariableDeclarationNode variableDeclarationNode) {
        IdentifierNode identifierNode = variableDeclarationNode.getLhs();
        this.variableName = identifierNode.getIdentifierName();
        this.dataType = identifierNode.getDataType();
        this.value = variableDeclarationNode.getRhs();
    }

    public String getVariableName() {
        return variableName;
    }

    public DataType getDataType() {
        return dataType;
    }
}