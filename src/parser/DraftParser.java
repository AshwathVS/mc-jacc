package parser;

import core.Pair;
import core.Token;
import core.Type;
import lexer.LexerWrapper;
import parser.controlAndLoopStatements.DoWhileNode;
import parser.controlAndLoopStatements.ForNode;
import parser.controlAndLoopStatements.IfNode;
import parser.controlAndLoopStatements.WhileNode;
import parser.literal.Literal;
import parser.literal.LiteralType;
import parser.symbolTable.FunctionSymbolTableEntry;
import parser.symbolTable.SymbolTable;
import parser.symbolTable.VariableSymbolTableEntry;

import java.util.ArrayList;
import java.util.List;

public class DraftParser {
    protected LexerWrapper lexer;

    public DraftParser(LexerWrapper lexerWrapper) {
        this.lexer = lexerWrapper;
    }

    public ProgramNode parse() {
        return parseProgram();
    }

    private ProgramNode parseProgram() {
        ProgramNode programNode = new ProgramNode();
        while(!lexer.match(Type.EOF)) {
            VariableDeclarationNode variableDeclarationNode = parseVariableDeclaration(programNode, true, true);
            if(null != variableDeclarationNode) {
                programNode.addVariable(variableDeclarationNode);
            } else {
                FunctionDeclarationNode functionDeclarationNode = parseFunctionDeclaration(programNode);
                if(null != functionDeclarationNode) {
                    programNode.addFunction(functionDeclarationNode);
                } else {
                    //TODO: Throw exception here
                }
            }
        }
        return programNode;
    }

    private VariableDeclarationNode parseVariableDeclaration(SymbolTable symbolTable, boolean tryParseRhs, boolean parseDataType) {
        lexer.snapshot();
        IdentifierNode lhs = parseIdentifierDeclarationNode(symbolTable, parseDataType);
        if(null == lhs) {
            lexer.restore();
            return null;
        }

        if(tryParseRhs) {
            if (!lexer.match(Type.EQUAL_TO)) {
                // TODO: Throw no equal to operator found;
            }

            // removing equal_to token
            lexer.nextToken();

            RhsValueNode rhs = parseBinaryExpression(symbolTable);
            if(rhs == null) {
                return new VariableDeclarationNode(lhs, null);
            } else {
                if (validateDataTypeEquivalence(lhs, rhs)) {
                    return new VariableDeclarationNode(lhs, rhs);
                } else {
                    //TODO: Throw exception (rhs value does not match the lhs value)
                }
            }
        } else {
            return new VariableDeclarationNode(lhs, null);
        }

        return null;
    }

    private boolean validateDataTypeEquivalence(IdentifierNode lhs, RhsValueNode rhs) {
        return false;
    }

    private IdentifierNode parseIdentifierDeclarationNode(SymbolTable symbolTable, boolean parseDataType) {
        lexer.snapshot();
        DataType dataType = null;
        dataType = parseDataType();
        if (parseDataType && null == dataType) {
            // TODO: Throw exception (no datatype found)
        }

        String identifier = parseIdentifierName();
        if (null != identifier) {
            Pair<Integer, VariableSymbolTableEntry> variableSymbolTableEntry = symbolTable.getVariable(identifier);
            if (null == variableSymbolTableEntry) {
                // TODO: Throw Declaration missing exception for the variable
            } else {
                return new IdentifierNode(variableSymbolTableEntry.getValue().getDataType(), identifier, variableSymbolTableEntry.getKey());
            }
        }

        lexer.restore();
        return null;
    }

    private DataType parseDataType() {
        lexer.snapshot();
        if(lexer.match(Type.getDataTypeOperators())) {
            return DataType.getDataType(lexer.nextToken().getType());
        }
        lexer.restore();
        return null;
    }

    private String parseIdentifierName() {
        lexer.snapshot();
        if(lexer.match(Type.ID)) {
            return lexer.nextToken().getValue();
        }
        lexer.restore();
        return null;
    }

    /**
     * Grammar followed is as follows:
     * E -> T | T + T | T - T
     * T -> F | F * F | F / F
     * F -> ID | LITERAL | FUNCTION_CALL | (E)
     */
    private RhsValueNode parseBinaryExpression(SymbolTable symbolTable) {
        lexer.snapshot();
        Operator start = Operator.NULL;
        RhsValueNode left = parseBinaryTerm(symbolTable, start.getPrecedence());
        if(null == left) {
            lexer.restore();
            return left;
        }
        if(lexer.match(Operator.getOperatorsWithGreaterPrecedence(start.getPrecedence()))) {
            Token token = lexer.peek();
            start = Operator.getPrecedenceForOperator(token.getType());
        }
        while(null != start && lexer.match(Operator.getOperatorsWithEqualPrecedence(start.getPrecedence()))) {
            Token token = lexer.nextToken();
            start = Operator.getPrecedenceForOperator(token.getType());
            RhsValueNode right = parseBinaryTerm(symbolTable, start.getPrecedence());
            left = new BinaryExpressionNode(left, right, start);
        }
        return left;
    }

    private RhsValueNode parseBinaryTerm(SymbolTable symbolTable, int previousPrecedence) {
        lexer.snapshot();
        RhsValueNode left = parseBinaryFactor(symbolTable);
        if(null == left) {
            lexer.restore();
            return null;
        }
        Operator start = null;
        if(lexer.match(Operator.getOperatorsWithGreaterPrecedence(previousPrecedence))) {
            Token token = lexer.peek();
            start = Operator.getPrecedenceForOperator(token.getType());
        }
        while(null != start && lexer.match(Operator.getOperatorsWithEqualPrecedence(start.getPrecedence()))) {
            Token token = lexer.nextToken();
            start = Operator.getPrecedenceForOperator(token.getType());
            RhsValueNode right = parseBinaryTerm(symbolTable, start.getPrecedence());
            left = new BinaryExpressionNode(left, right, start);
        }
        return left;
    }

    private RhsValueNode parseBinaryFactor(SymbolTable symbolTable) {
        lexer.snapshot();
        FunctionCallNode functionCallNode = parseFunctionCall(symbolTable);
        if(null == functionCallNode) {
            lexer.restore();
            lexer.snapshot();
            RhsValueNode bracketedExpression = parseBracketedExpression(symbolTable);
            if(null == bracketedExpression) {
                lexer.restore();
                lexer.snapshot();
                IdentifierNode identifier = parseIdentifierNode(symbolTable);
                if(null == identifier) {
                    lexer.restore();
                    lexer.snapshot();
                    Literal literal = parseLiteral();
                    if(null == literal) {

                        // TODO: Throw exception (no rhs value found) check if lexer restore is needed

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
        lexer.snapshot();
        if (lexer.match(LiteralType.getLiteralTypes())) {
            return Literal.parseTokenToLiteral(lexer.nextToken());
        }
        lexer.restore();
        return null;
    }

    private RhsValueNode parseBracketedExpression(SymbolTable symbolTable) {
        lexer.snapshot();
        if(lexer.match(Type.OPEN_BRACKET)) {
            lexer.nextToken();
            RhsValueNode expression = parseBinaryExpression(symbolTable);
            if (!lexer.match(Type.CLOSE_BRACKET)) {
                // TODO: Throw exception (no closing bracket found)
            }
            lexer.nextToken();
            return expression;
        }
        lexer.restore();
        return null;
    }

    private FunctionCallNode parseFunctionCall(SymbolTable symbolTable) {
        lexer.snapshot();
        if(lexer.match(Type.ID)) {
            String functionName = parseIdentifierName();
            if(lexer.match(Type.OPEN_BRACKET)) {
                lexer.nextToken();
                List<IdentifierNode> identifierNodes = new ArrayList<>(10);
                while(lexer.match(Type.ID)) {
                    IdentifierNode identifierNode = parseIdentifierNode(symbolTable);
                    identifierNodes.add(identifierNode);

                    if(lexer.match(Type.CLOSE_BRACKET)) {
                        lexer.nextToken();
                        Pair<Integer, FunctionSymbolTableEntry> definedFunction = symbolTable.getFunction(functionName, identifierNodes.size());
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
        lexer.restore();
        return null;
    }

    private IdentifierNode parseIdentifierNode(SymbolTable symbolTable) {
        lexer.snapshot();
        String identifier = parseIdentifierName();
        if (null != identifier) {
            Pair<Integer, VariableSymbolTableEntry> variableSymbolTableEntry = symbolTable.getVariable(identifier);
            if (null == variableSymbolTableEntry) {
                // TODO: Throw Declaration missing exception for the variable
            } else {
                return new IdentifierNode(variableSymbolTableEntry.getValue().getDataType(), identifier, variableSymbolTableEntry.getKey());
            }
        }
        lexer.restore();
        return null;
    }

    private FunctionDeclarationNode parseFunctionDeclaration(SymbolTable symbolTable) {
        lexer.snapshot();
        DataType returnType = parseDataType();
        if (null == returnType) {
            lexer.restore();
            return null;
        } else {
            lexer.snapshot();
            String functionName = parseIdentifierName();
            List<IdentifierNode> variableDeclarationNodes = new ArrayList<>(10);
            if(lexer.match(Type.OPEN_BRACKET)) {
                lexer.nextToken();
                while(true) {
                    VariableDeclarationNode variableDeclarationNode = parseVariableDeclaration(symbolTable, false, true);
                    if(null != variableDeclarationNode) {
                        variableDeclarationNodes.add(variableDeclarationNode.getLhs());
                    }
                    if(lexer.match(Type.CLOSE_BRACKET)) {
                        lexer.nextToken();
                        FunctionDeclarationNode functionDeclarationNode = new FunctionDeclarationNode(symbolTable, functionName, variableDeclarationNodes, returnType);
                        BlockNode blockNode = parseBlockNode(functionDeclarationNode);
                        if(blockNode == null) {
                            // TODO: Throw exception (no function block found)
                        } else {
                            functionDeclarationNode.setBlockNode(blockNode);
                            return functionDeclarationNode;
                        }
                    } else if(lexer.match(Type.COMMA)) {
                        lexer.nextToken();
                    } else {
                        // TODO: Throw exception (unknown token found)
                    }
                }
            }
        }
        lexer.restore();
        return null;
    }

    private BlockNode parseBlockNode(SymbolTable symbolTable) {
        lexer.snapshot();
        // OPEN BRACKET
        if(lexer.match(Type.OPEN_CURLY_BRACE)) {
            BlockNode blockNode = new BlockNode(symbolTable);
            while(!lexer.match(Type.CLOSE_BRACKET)) {
                StatementNode statement = parseGenericStatement(blockNode);
                blockNode.addStatementNode(statement);
            }

            // discard close brace
            lexer.nextToken();
            return blockNode;
        } else {
            lexer.restore();
            return null;
        }
    }

    private StatementNode parseGenericStatement(SymbolTable symbolTable) {
        // if
        lexer.snapshot();
        IfNode ifNode = parseIfStatement(symbolTable);
        if(null != ifNode) {
            return ifNode;
        }

        // do while
        lexer.restore();
        lexer.snapshot();
        DoWhileNode doWhileNode = parseDoWhileStatement(symbolTable);
        if(null != doWhileNode) {

        }

        // while
        lexer.restore();
        lexer.snapshot();
        WhileNode whileNode = parseWhileNode(symbolTable);
        if(null != whileNode) {
            return whileNode;
        }

        // for
        lexer.restore();
        lexer.snapshot();
        ForNode forNode = parseForNode(symbolTable);
        if(null != forNode) {
            return forNode;
        }

        // expression
        lexer.restore();
        lexer.snapshot();
        RhsValueNode binaryExpressionNode = parseBinaryExpression(symbolTable);
        if(null != binaryExpressionNode) {
            return binaryExpressionNode;
        }

        // return node
        lexer.restore();
        lexer.snapshot();
        FunctionReturnNode functionReturnNode = parseFunctionReturn(symbolTable);
        if(null != functionReturnNode) {
            return functionReturnNode;
        }

        lexer.restore();
        // TODO: Throw incorrect statement error

        return null;
    }

    private FunctionReturnNode parseFunctionReturn(SymbolTable symbolTable) {
        if(lexer.match(Type.RETURN)) {
            lexer.nextToken();
            String returnValue = parseIdentifierName();
            if(null == returnValue) {
                // TODO: Throw exception (no return value found / blank return statement)
            }

            Pair<Integer, VariableSymbolTableEntry> variableSymbolTableEntry = symbolTable.getVariable(returnValue);
            if(null == variableSymbolTableEntry) {
                // TODO: Throw exception (return undeclared variable);
            } else {
                return new FunctionReturnNode(new IdentifierNode(variableSymbolTableEntry.getValue(), variableSymbolTableEntry.getKey()));
            }
        }
        return null;
    }

    private ForNode parseForNode(SymbolTable symbolTable) {
        if(lexer.match(Type.FOR)) {
            lexer.nextToken();


            if(lexer.match(Type.OPEN_BRACKET)) {
                lexer.nextToken();
                VariableDeclarationNode initNode = parseVariableDeclaration(symbolTable, true, false);


                if(lexer.match(Type.SEMI_COLON)) {
                    lexer.nextToken();
                    RhsValueNode conditionNode = parseBinaryExpression(symbolTable);


                    if(lexer.match(Type.SEMI_COLON)) {
                        lexer.nextToken();
                        RhsValueNode incrementNode = parseBinaryExpression(symbolTable);


                        if(lexer.match(Type.CLOSE_BRACKET)) {
                            symbolTable.addVariable(new VariableSymbolTableEntry(initNode.getLhs().getIdentifierName(), initNode.getLhs().getDataType(), initNode.getRhs()));
                            BlockNode blockNode = parseBlockNode(symbolTable);
                            return new ForNode(initNode, conditionNode, incrementNode, blockNode);
                        } else {
                            // TODO: Throw exception (no closing bracket found)
                        }
                    } else {
                        // TODO: Throw exception (no second semi colon found)
                    }
                } else {
                    // TODO: Throw exception (no first semi colon found)
                }
            } else {
                // TODO: Throw no open bracket found
            }
        }
        return null;
    }

    private WhileNode parseWhileNode(SymbolTable symbolTable) {
        if(lexer.match(Type.WHILE)) {
            lexer.nextToken();
            if(lexer.match(Type.OPEN_BRACKET)) {
                lexer.nextToken();
                RhsValueNode variableDeclarationNode = parseBinaryExpression(symbolTable);
                if(null != variableDeclarationNode) {
                    if(lexer.match(Type.CLOSE_BRACKET)) {
                        lexer.nextToken();
                        BlockNode blockNode = parseBlockNode(symbolTable);
                        if(blockNode != null) {
                            return new WhileNode(blockNode, variableDeclarationNode);
                        } else {
                            // TODO: Throw exception (no function block found for while loop)
                        }
                    } else {
                        // TODO: Throw exception (no closing brace found)
                    }
                } else {
                    // TODO: Throw exception (no variable declaration found)
                }
            } else {
                // TODO: Throw exception (no opening brace found)
            }
        }
        return null;
    }

    private DoWhileNode parseDoWhileStatement(SymbolTable symbolTable) {
        if (lexer.match(Type.DO)) {
            lexer.nextToken();
            BlockNode blockNode = parseBlockNode(symbolTable);
            if (null != blockNode) {
                if (lexer.match(Type.WHILE)) {
                    lexer.nextToken();
                    if (lexer.match(Type.OPEN_BRACKET)) {
                        lexer.nextToken();
                        RhsValueNode binaryExpression = parseBinaryExpression(symbolTable);
                        if (null != binaryExpression) {
                            if (lexer.match(Type.CLOSE_BRACKET)) {
                                lexer.nextToken();
                                return new DoWhileNode(blockNode, binaryExpression);
                            } else {
                                // TODO: Throw exception (no closing bracket found)
                            }
                        } else {
                            // TODO: Throw exception (no binary expression found)
                        }
                    } else {
                        // TODO: Throw exception (no opening bracket found)
                    }
                } else {
                    // TODO: Throw exception (no ending while statement found)
                }
            } else {
                // TODO: Throw exception (no do while block found)
            }
        }
        return null;
    }

    private IfNode parseIfStatement(SymbolTable symbolTable) {
        if(lexer.match(Type.IF)) {
            lexer.nextToken();
            if(lexer.match(Type.OPEN_BRACKET)) {
                lexer.nextToken();
                RhsValueNode conditionStatement = parseBinaryExpression(symbolTable);
                if(null != conditionStatement) {
                    if(lexer.match(Type.CLOSE_BRACKET)) {
                        lexer.nextToken();
                        BlockNode blockNode = parseBlockNode(symbolTable);
                        if(null != blockNode) {
                            IfNode elseIfNode = parseElseIfStatement(symbolTable);
                            if(elseIfNode != null) {
                                return new IfNode(conditionStatement, blockNode, elseIfNode);
                            } else {
                                IfNode elseNode = parseElseStatement(symbolTable);
                                if(null != elseIfNode) {
                                    return new IfNode(conditionStatement, blockNode, elseNode);
                                } else {
                                    return new IfNode(conditionStatement, blockNode, null);
                                }
                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            }
        }
        return null;
    }

    private IfNode parseElseStatement(SymbolTable symbolTable) {
        if(lexer.match(Type.ELSE)) {
            lexer.nextToken();
            BlockNode elseBlock = parseBlockNode(symbolTable);
            if(null != elseBlock) {
                return new IfNode(null, elseBlock, null);
            } else {
                // TODO: Throw Exception (no else block found but else statement found)
            }
        }
        return null;
    }

    private IfNode parseElseIfStatement(SymbolTable symbolTable) {
        if(lexer.match(Type.ELSE)) {
            lexer.nextToken();
            return parseIfStatement(symbolTable);
        } else {
            return null;
        }
    }
}
