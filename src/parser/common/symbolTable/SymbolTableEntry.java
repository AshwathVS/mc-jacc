package parser.common.symbolTable;

public class SymbolTableEntry<T> {

    private VariableType variableType;

    private T value;

    public SymbolTableEntry(VariableType variableType, T value) {
        this.variableType = variableType;
        this.value = value;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public T getValue() {
        return value;
    }
}
