package parser;

import parser.symbolTable.SymbolTable;

import java.util.List;

public class StatementBlock extends SymbolTable {
    private List<BaseStatement> statements;

    public StatementBlock(SymbolTable parent) {
        super(parent);
    }

    public List<BaseStatement> getStatements() {
        return statements;
    }

    public void setStatements(List<BaseStatement> statements) {
        this.statements = statements;
    }
}
