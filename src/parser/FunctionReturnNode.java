package parser;

public class FunctionReturnNode implements StatementNode {
    private IdentifierNode identifierNode;

    public FunctionReturnNode(IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }

    @Override
    public StatementType getStatementType() {
        return null;
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }
}
