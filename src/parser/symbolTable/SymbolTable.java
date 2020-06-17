package parser.symbolTable;

import core.Pair;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private static final Integer INITIAL_TABLE_CAPACITY = 100;

    private static final Integer START_SYMBOL_TABLE_INDEX = 1000;

    private Integer variableIndex = START_SYMBOL_TABLE_INDEX, functionIndex = START_SYMBOL_TABLE_INDEX;

    private Map<Integer, VariableSymbolTableEntry> variableSymbolTable;

    private Map<String, Integer> variableNames;

    private Map<Integer, FunctionSymbolTableEntry> functionSymbolTable;

    private Map<String, Integer> functionNames;

    private SymbolTable parent;

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;

        variableSymbolTable = new HashMap<>(INITIAL_TABLE_CAPACITY);
        functionSymbolTable = new HashMap<>(INITIAL_TABLE_CAPACITY);

        variableNames = new HashMap<>(INITIAL_TABLE_CAPACITY);
        functionNames = new HashMap<>(INITIAL_TABLE_CAPACITY);
    }

    protected Integer addVariable(VariableSymbolTableEntry variableSymbolTableEntry) {
        if(variableNames.containsKey(variableSymbolTableEntry.getVariableName())) {
            return null;
        } else {
            variableSymbolTable.put(variableIndex, variableSymbolTableEntry);
            variableNames.put(variableSymbolTableEntry.getVariableName(), variableIndex);
            return variableIndex++;
        }
    }

    protected Integer addFunction(FunctionSymbolTableEntry functionSymbolTableEntry) {
        String internalFunctionName = functionSymbolTableEntry.getFunctionName() + functionSymbolTableEntry.getArgumentCount();
        if(functionNames.containsKey(internalFunctionName)) {
            return null;
        } else {
            functionSymbolTable.put(functionIndex, functionSymbolTableEntry);
            functionNames.put(internalFunctionName, functionIndex);
            return functionIndex++;
        }
    }

    public Pair<Integer, VariableSymbolTableEntry> getVariable(String variableName) {
        if(variableNames.containsKey(variableName)) {
            Integer id = variableNames.get(variableName);
            VariableSymbolTableEntry variableSymbolTableEntry = variableSymbolTable.get(id);
            return new Pair<>(id, variableSymbolTableEntry);
        }
        else if (null != parent) return parent.getVariable(variableName);
        else return null;
    }

    public Pair<Integer, FunctionSymbolTableEntry> getFunction(String functionName, Integer argumentCount) {
        return getFunction(functionName + argumentCount);
    }

    protected Pair<Integer, FunctionSymbolTableEntry> getFunction(String internalFunctionName) {
        if(functionNames.containsKey(internalFunctionName)) {
            Integer id = functionNames.get(internalFunctionName);
            FunctionSymbolTableEntry functionSymbolTableEntry = functionSymbolTable.get(id);
            return new Pair<>(id, functionSymbolTableEntry);
        } else if (null != parent) return parent.getFunction(internalFunctionName);
        else return null;
    }

}
