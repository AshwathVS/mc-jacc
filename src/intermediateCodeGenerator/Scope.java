package intermediateCodeGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Scope {

    private Map<String, VariableRecord> variableRecordMap;

    private Map<String, Integer> operationHistory;

    private Stack<Integer> functionCallStack;

    private Scope parent;

    public Scope(Scope parent) {
        this.parent = parent;

        this.variableRecordMap = new HashMap<>(50);
        this.operationHistory = new HashMap<>(100);
        this.functionCallStack = new Stack<>();
    }

    public Integer findEquivalentOperationInHistory(String operation) {
        return operationHistory.get(operation);
    }

    public VariableRecord getVariableRecord(String actualVariableName) {
        return this.variableRecordMap.get(actualVariableName);
    }

    public Scope getParent() {
        return parent;
    }

    public boolean addVariableRecord(String actualVariableName, VariableRecord varRec) {
        if(variableRecordMap.containsKey(actualVariableName)) return false;
        variableRecordMap.put(actualVariableName, varRec);
        return true;
    }
}
