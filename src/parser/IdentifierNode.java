package parser;

import parser.symbolTable.VariableSymbolTableEntry;

public class IdentifierNode implements GenericExpressionNode, StatementNode {
    private DataType dataType;
    private String identifierName;
    private Integer symbolTableId;

    public IdentifierNode(DataType dataType, String identifierName) {
        this.dataType = dataType;
        this.identifierName = identifierName;
    }

    public IdentifierNode(VariableSymbolTableEntry variableSymbolTableEntry, Integer symbolTableId) {
        this.dataType = variableSymbolTableEntry.getDataType();
        this.identifierName = variableSymbolTableEntry.getVariableName();
        this.symbolTableId = symbolTableId;
    }

    public IdentifierNode(DataType dataType, String identifierName, Integer symbolTableId) {
        this.dataType = dataType;
        this.identifierName = identifierName;
        this.symbolTableId = symbolTableId;
    }

    public DataType getDataType() {
        return dataType;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public Integer getSymbolTableId() {
        return symbolTableId;
    }

    public void setSymbolTableId(Integer symbolTableId) {
        this.symbolTableId = symbolTableId;
    }

    @Override
    public RhsValueType getRhsValueType() {
        return RhsValueType.IDENTIFIER;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }
}
