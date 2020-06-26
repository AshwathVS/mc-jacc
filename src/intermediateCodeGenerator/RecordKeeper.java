package intermediateCodeGenerator;

import java.util.*;

public class RecordKeeper {

    private Scope head, current;

    // functionMarkers will hold the start instruction number for all the functions
    private Map<String, Integer> functionMarkers;

    // globalVariableMarkers will hold the end instruction for all the global variables
    private Map<String, Integer> globalVariableMarkers;

    // This will have the order in which the instructions must be implemented
    private List<Integer> instructionPipeline;

    // Each of the instruction in the pipeline maps to a triple which will be stored in this map
    private Map<Integer, Triple> tripleMap;

    private Integer instructionCounter = 0;

    private Integer tempNameCounter = 0;

    private String tempNamePrefix = "t";

    public RecordKeeper() {
        head = new Scope(null);
        current = head;

        functionMarkers = new HashMap<>(20);
        globalVariableMarkers = new HashMap<>(50);
        instructionPipeline = new ArrayList<>(100);
        tripleMap = new HashMap<>(100);
    }

    public void initNewScope() {
        Scope newScope = new Scope(current);
        current = newScope;
    }

    public void exitScope() {
        current = current.getParent();
    }

    public void addVariableRecord(String actualVariableName, VariableRecord variableRecord) throws ICGException {
        if(!this.current.addVariableRecord(actualVariableName, variableRecord)) {
            throw new ICGException("Duplicate variable name used (" + actualVariableName + ")");
        }
    }

    public String generateTempName() {
        this.tempNameCounter++;
        return this.tempNamePrefix + this.tempNameCounter;
    }

    public VariableRecord getVariableRecord(String actualVariableName) throws ICGException {
        Scope iter = current;
        while(iter != null) {
            VariableRecord varRec = iter.getVariableRecord(actualVariableName);
            if(null != varRec) {
                return varRec;
            }
            iter = iter.getParent();
        }
        throw new ICGException("Cannot search undefined variable (" + actualVariableName + ")");
    }

    public Integer findEquivalentOperationInHistory(String operation) {
        return this.current.findEquivalentOperationInHistory(operation);
    }

    private Integer getNextInstructionId() {
        this.instructionCounter++;
        return this.instructionCounter;
    }

    public void addInstruction(String operator, String arg1, String arg2) {
        int instructionId = getNextInstructionId();
        this.instructionPipeline.add(instructionId);

        this.tripleMap.put(instructionId, new Triple(operator, arg1, arg2));
    }
}
