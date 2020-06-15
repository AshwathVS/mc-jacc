package parser.common.symbolTable;

public class IdSymbolTableEntry extends SymbolTableEntry<Object> {
    public IdSymbolTableEntry(Object value) {
        super(VariableType.ID, value);
    }

}
