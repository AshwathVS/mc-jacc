package parser;

import parser.symbolTable.FunctionSymbolTableEntry;
import parser.symbolTable.SymbolTable;
import parser.symbolTable.VariableSymbolTableEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * ProgramNode is the root node and will have the global symbol tree which has no parents
 */
public class ProgramNode extends SymbolTable {

    private List<VariableDeclarationNode> variableDeclarations;

    private List<FunctionDeclarationNode> functionDeclarationNodes;

    public ProgramNode() {
        super(null);
        variableDeclarations = new ArrayList<>(100);
        functionDeclarationNodes = new ArrayList<>(100);
    }

    public void addVariable(VariableDeclarationNode variableDeclarationNode) {
        IdentifierNode identifierNode = variableDeclarationNode.getLhs();
        VariableSymbolTableEntry variableSymbolTableEntry = new VariableSymbolTableEntry(identifierNode.getIdentifierName(), identifierNode.getDataType(), variableDeclarationNode.getRhs());
        Integer id = addVariable(variableSymbolTableEntry);
        if (null == id) {
            // Duplicate id found
            // TODO: Throw exception
        } else {
            identifierNode.setSymbolTableId(id);
            this.variableDeclarations.add(variableDeclarationNode);
        }
    }

    public void addFunction(FunctionDeclarationNode functionDeclarationNode) {
        FunctionSymbolTableEntry functionSymbolTableEntry = new FunctionSymbolTableEntry(functionDeclarationNode.getFunctionName(), functionDeclarationNode.getArgumentList());
        Integer id = addFunction(functionSymbolTableEntry);
        if (null == id) {
            // Duplicate function found with same name and argument count
            // TODO: Throw Exception
        } else {
            functionDeclarationNode.setSymbolTableId(id);
            functionDeclarationNodes.add(functionDeclarationNode);
        }
    }
}
