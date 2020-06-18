package parser;

import parser.symbolTable.SymbolTable;

import java.util.List;

public class FunctionDeclarationNode extends SymbolTable {
    private String functionName;

    private List<IdentifierNode> argumentList;

    private BlockNode blockNode;

    private DataType returnType;

    private Integer symbolTableId;

    public FunctionDeclarationNode(SymbolTable parent, String functionName, List<IdentifierNode> argumentList, BlockNode blockNode, DataType returnType, Integer symbolTableId) {
        super(parent);
        this.functionName = functionName;
        this.argumentList = argumentList;
        this.blockNode = blockNode;
        this.returnType = returnType;
        this.symbolTableId = symbolTableId;
    }

    public FunctionDeclarationNode(SymbolTable parent, String functionName, List<IdentifierNode> argumentList, DataType returnType) {
        super(parent);
        this.functionName = functionName;
        this.argumentList = argumentList;
        this.returnType = returnType;
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

    public void setBlockNode(BlockNode blockNode) {
        this.blockNode = blockNode;
    }

}
