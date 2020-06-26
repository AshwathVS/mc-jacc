package icg;

import core.Pair;
import parser.DataType;

import java.util.*;

public class IntermediateCode {

    /** Constants start */
    private Integer memAddr = 1000;
    private Integer varCounter = 0;
    private Character varBase = 'a';
    public static final String INSTRUCTION_ADDRESS_PREFIX = "#";
    private static final String TEMP_NAME_PREFIX = "t";
    private Integer tmpCounter = 0;
    /** Constants end */

    private Map<Integer, Triple> tripleMap;

    private Scope head, tail;

    private List<Integer> indirectTripleList;

    private Stack<String> functionCall;

    public IntermediateCode() {
        tripleMap = new HashMap<>(100);
        indirectTripleList = new ArrayList<>(100);
        functionCall = new Stack<>();

        head = new Scope(null);
        tail = head;
    }

    /**
     * int a = x-y;
     * int b = x-y;
     * Here we need to reuse the value of (x-y), but when there is an assignment in between:
     * int a = x - y;
     * x = x + 2;
     * int b = x - y;
     * Now we need to recalculate (x-y) as the value is different
     *
     * So we need to track all the assignments and remove from the operation history also
     */
    public int addRecord(String operator, String arg1, String arg2) {
        Integer addr = memAddr++;
        String operation = generateOperation(operator, arg1, arg2);

        if (tail.operationHistory.containsKey(operation)) {

            // if the operation has occurred after the variable has been modified, then we can reuse it
            Integer latestVarAssignmentForArg1 = null, latestVarAssignmentForArg2 = null;
            if(tail.internalNames.contains(arg1)) {
                latestVarAssignmentForArg1 = tail.variableAssignmentsTracker.get(arg1);
            }
            if(tail.internalNames.contains(arg2)) {
                latestVarAssignmentForArg2 = tail.variableAssignmentsTracker.get(arg2);
            }
            if(tail.operationHistory.get(operation) > latestVarAssignmentForArg1
                    && tail.operationHistory.get(operation) > latestVarAssignmentForArg2) {

                return tail.operationHistory.get(operation);
            }
        }

        tripleMap.put(addr, new Triple(operator, arg1, arg2));
        indirectTripleList.add(addr);

        addAssignmentEntry(operator, arg1, addr);

        return addr;
    }

    private void addAssignmentEntry(String operator, String internalVariableName, int addr) {
        if(operator == "=") {
            tail.variableAssignmentsTracker.put(internalVariableName, addr);
        }
    }

    public void newScope() {
        Scope scope = new Scope(tail);
        tail = scope;
    }

    public void exitScope() {
        tail = tail.parent;
    }

    public String popArgumentFromFunctionStack() {
        return functionCall.pop();
    }

    public void pushArgumentToFunctionStack(String value) {
        functionCall.add(value);
    }

    public String convertVariable(String variableName, DataType dataType) throws ICGException {
        if(tail.internalNameMap.containsKey(variableName)) throw new ICGException("Duplicate variable used: " + variableName);
        String intName = generateInternalName();
        tail.internalNameMap.put(variableName, new Pair<>(intName, dataType));
        tail.internalNames.add(intName);
        return intName;
    }

    public String getInternalName(String variableName) throws ICGException {
        Scope tmp = tail;
        while(tmp != null) {
            if(tmp.internalNameMap.containsKey(variableName)) {
                return tmp.internalNameMap.get(variableName).key;
            }
            tmp = tmp.parent;
        }
        throw new ICGException("Cannot refer unknown variable");
    }

    public String generateTempName() {
        return TEMP_NAME_PREFIX + tmpCounter++;
    }

    private String generateInternalName() throws ICGException {

        if(varCounter == 100) {
            varCounter = 0;
            varBase++;
        }

        // throw exception when limit(z100) has exceeded
        if(varBase - 1 == 'z') throw new ICGException("Internal names limit reached");

        return varBase.toString() + varCounter;
    }

    private String generateOperation(String operator, String arg1, String arg2) {
        if (null == arg2) {
            return operator + arg1;
        } else {
            return arg1 + operator + arg2;
        }
    }

    public static class Scope {

        private Scope parent;

        private Map<String, Integer> operationHistory;

        private Map<String, Pair<String, DataType>> internalNameMap;

        private Set<String> internalNames;

        // This will have records abt each internal variable and the instruction id of the latest assignment
        private Map<String, Integer> variableAssignmentsTracker;

        public Scope(Scope parent) {
            this.parent = parent;
            operationHistory = new HashMap<>(50);
            internalNameMap = new HashMap<>(100);
            internalNames = new HashSet<>(100);
            variableAssignmentsTracker = new HashMap<>(100);
        }
    }
}
