package parser;

public class IdentifierNode implements RhsExpression {
    private String identifierName;
    private DataType dataType;
    private Integer symbolTableReference;

    public IdentifierNode(String identifierName, DataType dataType, Integer symbolTableReference) {
        this.identifierName = identifierName;
        this.dataType = dataType;
        this.symbolTableReference = symbolTableReference;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Integer getSymbolTableReference() {
        return symbolTableReference;
    }

    public void setSymbolTableReference(Integer symbolTableReference) {
        this.symbolTableReference = symbolTableReference;
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.IDENTIFIER;
    }
}
