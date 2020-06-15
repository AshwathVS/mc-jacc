package parser.compound;

import parser.common.IdNode;

public class OperandNode extends ArithmeticStatementNode {
    private IdNode idNode;

    public OperandNode(IdNode idNode) {
        super(null, null, null);
        this.idNode = idNode;
    }

    public IdNode getIdNode() {
        return idNode;
    }
}
