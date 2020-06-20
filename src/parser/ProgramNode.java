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

    private List<VariableDeclaration> variableDeclarations;

    private List<FunctionDeclaration> functionDeclarationNodes;

    public ProgramNode() {
        super(null);
        variableDeclarations = new ArrayList<>(100);
        functionDeclarationNodes = new ArrayList<>(100);
    }

    public boolean addVariable(VariableDeclaration variableDeclarationNode) {
        IdentifierNode identifierNode = variableDeclarationNode.getLhs();
        VariableSymbolTableEntry variableSymbolTableEntry = new VariableSymbolTableEntry(identifierNode.getIdentifierName(), identifierNode.getDataType(), variableDeclarationNode.getRhs());
        Integer id = addVariable(variableSymbolTableEntry);
        if (null == id) {
            // Duplicate id found
            // TODO: Throw exception
            return false;
        } else {
            identifierNode.setSymbolTableReference(id);
            this.variableDeclarations.add(variableDeclarationNode);
            return true;
        }
    }

    public boolean addFunction(FunctionDeclaration functionDeclarationNode) {
        FunctionSymbolTableEntry functionSymbolTableEntry = new FunctionSymbolTableEntry(functionDeclarationNode.getFunctionName(), functionDeclarationNode.getArguments());
        Integer id = addFunction(functionSymbolTableEntry);
        if (null == id) {
            // Duplicate function found with same name and argument count
            // TODO: Throw Exception
            return false;
        } else {
            functionDeclarationNode.setSymbolTableReference(id);
            functionDeclarationNodes.add(functionDeclarationNode);
            return true;
        }
    }
}
