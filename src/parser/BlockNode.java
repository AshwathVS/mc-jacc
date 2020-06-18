package parser;

import parser.symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends SymbolTable {
    List<StatementNode> statementNodes;

    public BlockNode(SymbolTable parent) {
        super(parent);
        statementNodes = new ArrayList<>(100);
    }

    public List<StatementNode> getStatementNodes() {
        return statementNodes;
    }

    public void addStatementNode(StatementNode statementNode) {
        this.statementNodes.add(statementNode);
    }
}
