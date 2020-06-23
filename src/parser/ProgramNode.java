package parser;

import core.Pair;
import parser.symbolTable.FunctionSymbolTableEntry;
import parser.symbolTable.SymbolTable;
import parser.symbolTable.VariableSymbolTableEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * ProgramNode is the root node and will have the global symbol tree which has no parents
 */
public class ProgramNode extends SymbolTable {

    private List<Pair<String, Integer>> variableDeclarations;

    private List<Pair<String, Integer>> functionDeclarationNodes;

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
            this.variableDeclarations.add(new Pair<>(variableDeclarationNode.getLhs().getIdentifierName(), id));
            return true;
        }
    }

    public boolean addFunction(FunctionDeclaration functionDeclarationNode) {
        FunctionSymbolTableEntry functionSymbolTableEntry = new FunctionSymbolTableEntry(functionDeclarationNode);
        Integer id = addFunction(functionSymbolTableEntry);
        if (null == id) {
            // Duplicate function found with same name and argument count
            // TODO: Throw Exception
            return false;
        } else {
            functionDeclarationNodes.add(new Pair<>(functionDeclarationNode.getFunctionName(), id));
            return true;
        }
    }
}
