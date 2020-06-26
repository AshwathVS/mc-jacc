package intermediateCodeGenerator;

import parser.DataType;

public class VariableRecord {

    private DataType dataType;

    private String internalName;

    private Integer instructionId;

    public VariableRecord(DataType dataType, String internalName, Integer instructionId) {
        this.dataType = dataType;
        this.internalName = internalName;
        this.instructionId = instructionId;
    }

    public DataType getDataType() {
        return dataType;
    }

    public String getInternalName() {
        return internalName;
    }

    public Integer getInstructionId() {
        return instructionId;
    }
}
