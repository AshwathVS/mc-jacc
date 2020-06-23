package parser.symbolTable;

import parser.DataType;
import parser.IdentifierNode;
import parser.RhsExpression;
import parser.VariableDeclaration;

public class VariableSymbolTableEntry {

    String variableName;

    DataType dataType;

    RhsExpression value;

    public VariableSymbolTableEntry(String variableName, DataType dataType, RhsExpression value) {
        this.variableName = variableName;
        this.dataType = dataType;
        this.value = value;
    }

    public VariableSymbolTableEntry(VariableDeclaration variableDeclaration) {
        IdentifierNode identifierNode = variableDeclaration.getLhs();
        this.variableName = identifierNode.getIdentifierName();
        this.dataType = identifierNode.getDataType();
        this.value = variableDeclaration.getRhs();
    }

    public String getVariableName() {
        return variableName;
    }

    public DataType getDataType() {
        return dataType;
    }
}
