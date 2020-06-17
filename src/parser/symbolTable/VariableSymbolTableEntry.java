package parser.symbolTable;

import parser.DataType;

public class VariableSymbolTableEntry {
    String variableName;
    DataType dataType;
    Object value;

    public VariableSymbolTableEntry(String variableName, DataType dataType, Object value) {
        this.variableName = variableName;
        this.dataType = dataType;
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }

    public DataType getDataType() {
        return dataType;
    }
}
