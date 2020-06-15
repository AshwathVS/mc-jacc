package parser.common.symbolTable;

import parser.common.FunctionNode;

public class FunctionSymbolTableEntry extends SymbolTableEntry<FunctionNode> {
    public FunctionSymbolTableEntry(FunctionNode value) {
        super(VariableType.FUNCTION, value);
    }
}
