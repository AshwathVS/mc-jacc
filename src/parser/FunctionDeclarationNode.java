package parser;

import parser.symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class FunctionDeclarationNode extends SymbolTable {
    private String functionName;

    private List<IdentifierNode> argumentList;

    private BlockNode blockNode;

    private DataType returnType;

    private Integer symbolTableId;

    public FunctionDeclarationNode(String functionName, SymbolTable parent, DataType returnType, BlockNode blockNode) {
        super(parent);
        this.functionName = functionName;
        argumentList = new ArrayList<>(100);
        this.returnType = returnType;
        this.blockNode = blockNode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<IdentifierNode> getArgumentList() {
        return argumentList;
    }

    public BlockNode getBlockNode() {
        return blockNode;
    }

    public DataType getReturnType() {
        return returnType;
    }

    public Integer getSymbolTableId() {
        return symbolTableId;
    }

    public void setSymbolTableId(Integer symbolTableId) {
        this.symbolTableId = symbolTableId;
    }
}
