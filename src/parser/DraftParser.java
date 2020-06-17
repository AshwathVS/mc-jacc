package parser;

import core.Pair;
import core.Token;
import core.Type;
import lexer.LexerWrapper;
import parser.literal.Literal;
import parser.literal.LiteralType;
import parser.symbolTable.FunctionSymbolTableEntry;
import parser.symbolTable.VariableSymbolTableEntry;

import java.util.ArrayList;
import java.util.List;

public class DraftParser {
    protected LexerWrapper lexer;

    protected ProgramNode programNode;

    public DraftParser(LexerWrapper lexerWrapper) {
        this.lexer = lexerWrapper;
        this.programNode = new ProgramNode();
    }

    public ProgramNode parse() {
        this.programNode = parseProgram();
        return this.programNode;
    }

    private ProgramNode parseProgram() {
        while(!lexer.match(Type.EOF)) {
            VariableDeclarationNode variableDeclarationNode = parseVariableDeclaration();
            if(null != variableDeclarationNode) {
                programNode.addVariable(variableDeclarationNode);
            } else {
                FunctionDeclarationNode functionDeclarationNode = parseFunctionDeclaration();
                if(null != functionDeclarationNode) {
                    programNode.addFunction(functionDeclarationNode);
                } else {
                    //TODO: Throw exception here
                }
            }
        }
        return programNode;
    }

    private VariableDeclarationNode parseVariableDeclaration() {
        IdentifierNode lhs = parseIdentifierDeclarationNode();
        RhsValueNode rhs = parseBinaryExpression();
        if(null == lhs) return null;
        else {
            if (!lexer.match(Type.EQUAL_TO)) {
                // TODO: Throw no equal to operator found;
            }

            // equal to token
            lexer.nextToken();

            if(validateDataTypeEquivalence(lhs, rhs)) {
                return new VariableDeclarationNode(lhs, rhs);
            }
        }
        return null;
    }

    private boolean validateDataTypeEquivalence(IdentifierNode lhs, RhsValueNode rhs) {
        return false;
    }

    private IdentifierNode parseIdentifierDeclarationNode() {
        DataType dataType = parseDataType();
        if(null != dataType) {
            String identifier = parseIdentifierName();
            if (null != identifier) {
                Pair<Integer, VariableSymbolTableEntry> variableSymbolTableEntry = this.programNode.getVariable(identifier);
                if (null == variableSymbolTableEntry) {
                    // TODO: Throw Declaration missing exception for the variable
                } else {
                    return new IdentifierNode(variableSymbolTableEntry.getValue().getDataType(), identifier, variableSymbolTableEntry.getKey());
                }
            }
        }
        return null;
    }

    private DataType parseDataType() {
        if(lexer.match(Type.getDataTypeOperators())) {
            return DataType.getDataType(lexer.nextToken().getType());
        }
        return null;
    }

    private String parseIdentifierName() {
        if(lexer.match(Type.ID)) {
            return lexer.nextToken().getValue();
        }
        return null;
    }

    /**
     * Grammar followed is as follows:
     * E -> T | T + T | T - T
     * T -> F | F * F | F / F
     * F -> ID | LITERAL | FUNCTION_CALL | (E)
     */
    private RhsValueNode parseBinaryExpression() {
        Operator start = Operator.NULL;
        RhsValueNode left = parseBinaryTerm(start.getPrecedence());
        if(lexer.match(Operator.getOperatorsWithGreaterPrecedence(start.getPrecedence()))) {
            Token token = lexer.peek();
            start = Operator.getPrecedenceForOperator(token.getType());
        }
        while(null != start && lexer.match(Operator.getOperatorsWithEqualPrecedence(start.getPrecedence()))) {
            Token token = lexer.nextToken();
            start = Operator.getPrecedenceForOperator(token.getType());
            RhsValueNode right = parseBinaryTerm(start.getPrecedence());
            left = new BinaryExpressionNode(left, right, start);
        }
        return left;
    }

    private RhsValueNode parseBinaryTerm(int previousPrecedence) {
        RhsValueNode left = parseBinaryFactor();
        Operator start = null;
        if(lexer.match(Operator.getOperatorsWithGreaterPrecedence(previousPrecedence))) {
            Token token = lexer.peek();
            start = Operator.getPrecedenceForOperator(token.getType());
        }
        while(null != start && lexer.match(Operator.getOperatorsWithEqualPrecedence(start.getPrecedence()))) {
            Token token = lexer.nextToken();
            start = Operator.getPrecedenceForOperator(token.getType());
            RhsValueNode right = parseBinaryTerm(start.getPrecedence());
            left = new BinaryExpressionNode(left, right, start);
        }
        return left;
    }

    private RhsValueNode parseBinaryFactor() {
        FunctionCallNode functionCallNode = parseFunctionCall();
        if(null == functionCallNode) {
            RhsValueNode bracketedExpression = parseBracketedExpression();
            if(null == bracketedExpression) {
                IdentifierNode identifier = parseIdentifierNode();
                if(null == identifier) {
                    Literal literal = parseLiteral();
                    if(null == literal) {
                        // TODO: Throw exception (no rhs value found)

                    } else {
                        // success literal found
                        return literal;
                    }
                } else {
                    // success identifier node found
                    return identifier;
                }
            } else {
                // success bracketed expression node found
                return bracketedExpression;
            }
        } else {
            // success function call node found
            return functionCallNode;
        }
        return null;
    }

    private Literal parseLiteral() {
        if (lexer.match(LiteralType.getLiteralTypes())) {
            return Literal.parseTokenToLiteral(lexer.nextToken());
        }
        return null;
    }

    private RhsValueNode parseBracketedExpression() {
        if(lexer.match(Type.OPEN_BRACKET)) {
            lexer.nextToken();
            RhsValueNode expression = parseBinaryExpression();
            if (!lexer.match(Type.CLOSE_BRACKET)) {
                // TODO: Throw exception (no closing bracket found)
            }
            lexer.nextToken();
            return expression;
        }
        return null;
    }

    private FunctionCallNode parseFunctionCall() {
        if(lexer.match(Type.ID)) {
            String functionName = parseIdentifierName();
            if(lexer.match(Type.OPEN_BRACKET)) {
                lexer.nextToken();
                List<IdentifierNode> identifierNodes = new ArrayList<>(10);
                while(lexer.match(Type.ID)) {
                    IdentifierNode identifierNode = parseIdentifierNode();
                    identifierNodes.add(identifierNode);

                    if(lexer.match(Type.CLOSE_BRACKET)) {
                        lexer.nextToken();
                        Pair<Integer, FunctionSymbolTableEntry> definedFunction = programNode.getFunction(functionName, identifierNodes.size());
                        if(null == definedFunction) {
                            //TODO: Throw exception (unknown function call made)
                        } else {
                            return new FunctionCallNode(functionName, identifierNodes);
                        }
                    } else if(lexer.match(Type.COMMA)) {
                        lexer.nextToken();
                    } else {
                        // TODO: Throw exception (no closing bracket found for function call or unexpected character found in function call)
                    }
                }
            }
        }
        return null;
    }

    private IdentifierNode parseIdentifierNode() {
        String identifier = parseIdentifierName();
        if (null != identifier) {
            Pair<Integer, VariableSymbolTableEntry> variableSymbolTableEntry = this.programNode.getVariable(identifier);
            if (null == variableSymbolTableEntry) {
                // TODO: Throw Declaration missing exception for the variable
            } else {
                return new IdentifierNode(variableSymbolTableEntry.getValue().getDataType(), identifier, variableSymbolTableEntry.getKey());
            }
        }
        return null;
    }

    private FunctionDeclarationNode parseFunctionDeclaration() {
        return null;
    }
}
