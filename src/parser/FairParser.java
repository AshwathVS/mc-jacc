package parser;

import com.sun.security.auth.UnixNumericGroupPrincipal;
import core.Pair;
import core.Token;
import core.Type;
import lexer.LexerWrapper;
import parser.controlAndLoopStatements.*;
import parser.literal.Literal;
import parser.literal.LiteralType;
import parser.symbolTable.SymbolTable;
import parser.symbolTable.VariableSymbolTableEntry;

import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;
import java.util.List;

public class FairParser {
    private LexerWrapper lexer;

    public FairParser(LexerWrapper lexerWrapper) {
        this.lexer = lexerWrapper;
    }

    public ProgramNode parse() throws ParseException {
        ProgramNode programNode = new ProgramNode();
        while(!lexer.eof()) {
            Token token = lexer.top();

            // function definition
            if(Type.B_LEFT_SHIFT.equals(token.getType())) {
                FunctionDeclaration functionDeclarationNode = parseFunctionDeclaration(programNode);
                boolean addSuccess = programNode.addFunction(functionDeclarationNode);
                if(!addSuccess) {
                    throw new ParseException(ParseErrorMessages.DUPLICATE_FUNCTION_USED + " " + functionDeclarationNode.getFunctionName());
                }
            }
            // variable definition
            else if (Type.LESS_THAN.equals(token.getType())) {
                VariableDeclaration variableDeclaration = parseVariableDeclaration(programNode);
                boolean addVariableSuccess = programNode.addVariable(variableDeclaration);
                if(!addVariableSuccess) {
                    throw new ParseException(ParseErrorMessages.DUPLICATE_VARIABLE_USED + " " + variableDeclaration.getLhs().getIdentifierName());
                }
            } else {
                throw new ParseException("Expected character found at [" + token.getLineNumber() + ":" + token.getColumnNumber() + "]");
            }
        }
        return programNode;
    }

    private FunctionDeclaration parseFunctionDeclaration(SymbolTable symbolTable) throws ParseException {

        lexer.strictMatch(Type.B_LEFT_SHIFT);
        DataType dataType = parseDataType();
        String functionName = parseIdentifier().getValue();
        lexer.strictMatch(Type.OPEN_BRACKET);
        FunctionDeclaration functionDeclaration = new FunctionDeclaration(symbolTable, functionName, dataType);

        // fetch function signature
        while(!lexer.eof() && !Type.CLOSE_BRACKET.equals(lexer.top().getType())) {
            VariableDeclaration variableDeclaration = parseVariableDeclaration(functionDeclaration);
            functionDeclaration.addArgument(variableDeclaration);

            if(lexer.match(Type.COMMA)) lexer.nextToken();
        }

        lexer.match(Type.CLOSE_BRACKET);

        // fetch function definition
        lexer.strictMatch(Type.OPEN_CURLY_BRACE);
        StatementBlock statementBlock = parseBlock(functionDeclaration);
        lexer.strictMatch(Type.CLOSE_CURLY_BRACE);
        functionDeclaration.setFunctionBlock(statementBlock);
        return functionDeclaration;
    }

    private Token parseIdentifier() {
        return lexer.strictMatch(Type.ID);
    }

    private DataType parseDataType() {
        if(lexer.match(DataType.getDataTypeOperators())) {
            return DataType.getDataType(lexer.nextToken().getType());
        } else {
            if(null == lexer.top())
                lexer.addError("DataType expected, but found ");
            else
                lexer.addError("DataType expected, but found " + lexer.top().getType() + " at [" + lexer.top().getLineNumber() + ":" + lexer.top().getColumnNumber() + "]");
            return DataType.UNDEFINED;
        }
    }

    private VariableDeclaration parseVariableDeclaration(SymbolTable symbolTable) {
        BinaryExpression expression = null;
        lexer.strictMatch(Type.LESS_THAN);
        DataType dataType = parseDataType();
        String variableName = parseIdentifier().getValue();
        Token top = lexer.top();
        if(null == top) {
            lexer.addError("Abrupt end of variable declaration");
        } else {
            if (Type.GREATER_THAN.equals(top.getType())) {
                lexer.strictMatch(Type.GREATER_THAN);
            } else if (Type.OPEN_BRACKET.equals(top.getType())) {
                lexer.strictMatch(Type.OPEN_BRACKET);
                expression = parseBinaryExpression(symbolTable);
                lexer.strictMatch(Type.CLOSE_BRACKET);

            } else {
                lexer.addError("Invalid variable declaration found at: " + "[" + top.getLineNumber() + ":" + top.getColumnNumber() +"]");
            }
        }
        return new VariableDeclaration(new IdentifierNode(variableName, dataType, null), expression);
    }

    private BinaryExpression parseBinaryExpression(SymbolTable symbolTable) {
        Operator start = Operator.NULL;
        BinaryExpression left = parseBinaryTerm(symbolTable, start.getPrecedence());

        if(lexer.match(Operator.getOperatorsWithGreaterPrecedence(start.getPrecedence()))) {
            Token token = lexer.top();
            start = Operator.getPrecedenceForOperator(token.getType());
        }

        while(null != start && lexer.match(Operator.getOperatorsWithEqualPrecedence(start.getPrecedence()))) {
            Token token = lexer.nextToken();
            start = Operator.getPrecedenceForOperator(token.getType());
            BinaryExpression right = parseBinaryTerm(symbolTable, start.getPrecedence());
            left = new BinaryExpression(left, right, start);
        }
        return left;
    }

    private BinaryExpression parseBinaryTerm(SymbolTable symbolTable, int previousPrecedence) {
        BinaryExpression left = parseFactor(symbolTable);
        if(null == left) {
            return null;
        }
        Operator start = null;
        if(lexer.match(Operator.getOperatorsWithGreaterPrecedence(previousPrecedence))) {
            Token token = lexer.top();
            start = Operator.getPrecedenceForOperator(token.getType());
        }
        while(null != start && lexer.match(Operator.getOperatorsWithEqualPrecedence(start.getPrecedence()))) {
            Token token = lexer.nextToken();
            start = Operator.getPrecedenceForOperator(token.getType());
            BinaryExpression right = parseBinaryTerm(symbolTable, start.getPrecedence());
            left = new BinaryExpression(left, right, start);
        }
        return left;
    }

    /**
     * FUNCTION_CALL,
     * IDENTIFIER,
     * LITERAL,
     * BINARY_EXPRESSION;
     * @param symbolTable
     * @return
     */
    private BinaryExpression parseFactor(SymbolTable symbolTable) {

        Token top = lexer.top();
        if(top == null) {
            lexer.addError("Abrupt definition of binary expression");
            return null;
        }

        // literal match
        else if(lexer.match(LiteralType.getLiteralTypes())) {
            Literal literal = Literal.parseTokenToLiteral(lexer.nextToken());
            return new BinaryExpression(literal);
        }
        // Function call
        else if (null != top && Type.DOLLAR.equals(top.getType())) {
            FunctionCall functionCall = parseFunctionCall(symbolTable);
            return new BinaryExpression(functionCall);
        }

        // Bracketed expression
        else if (null != top && Type.OPEN_BRACKET.equals(top.getType())) {
            lexer.strictMatch(Type.OPEN_BRACKET);
            BinaryExpression expression = parseBinaryExpression(symbolTable);
            lexer.strictMatch(Type.CLOSE_BRACKET);
            return expression;
        }

        // Identifier usage
        else {
            Token variable = parseIdentifier();
            IdentifierNode identifierNode = getIdentifierNode(variable, symbolTable);
            return new BinaryExpression(identifierNode);
        }
    }

    private FunctionCall parseFunctionCall(SymbolTable symbolTable) {
        lexer.strictMatch(Type.DOLLAR);
        lexer.strictMatch(Type.DOT);
        Token functionName = parseIdentifier();
        lexer.strictMatch(Type.OPEN_BRACKET);
        List<IdentifierNode> passedArguments = new ArrayList<>(10);
        while(!lexer.eof() && !lexer.match(Type.CLOSE_BRACKET)) {
            Token identifierToken = parseIdentifier();
            IdentifierNode identifierNode = getIdentifierNode(identifierToken, symbolTable);
            passedArguments.add(identifierNode);
            if(lexer.match(Type.COMMA)) lexer.nextToken();
        }

        lexer.match(Type.CLOSE_BRACKET);
        return new FunctionCall(functionName.getValue(), passedArguments);
    }

    private StatementBlock parseBlock(SymbolTable symbolTable) throws ParseException {
        lexer.match(Type.OPEN_CURLY_BRACE);
        Token top = lexer.top();
        if (top == null) throw new ParseException("Abrupt end of program detected");
        List<BaseStatement> statements = new ArrayList<>(20);
        StatementBlock statementBlock = new StatementBlock(symbolTable);
        while(Type.CLOSE_CURLY_BRACE.equals(top.getType())) {
            BaseStatement baseStatement = null;

            // variable assignment
            if(Type.ID.equals(top.getType())) {
                baseStatement = parseVariableAssignment(statementBlock);
            }
            // variable declaration
            else if(lexer.match(DataType.getDataTypeOperators())) {
                baseStatement = parseVariableDeclaration(statementBlock);
            }
            else if(Type.AT.equals(top.getType())) {
                // control statement
                lexer.strictMatch(Type.AT);
                lexer.strictMatch(Type.DOT);
                if(lexer.top() == null) throw new ParseException("Abrupt end of program detected");

                switch (lexer.top().getType()) {

                    // Decision Statement
                    case IF: {
                        IfNode ifNode = parseIfStatement(statementBlock);
                        baseStatement = ifNode;
                        break;
                    }


                    // Looping Statement
                    case WHILE: {
                        WhileNode whileNode = parseWhileStatement(statementBlock, false);
                        baseStatement = whileNode;
                        break;
                    }
                    case DO_WHILE: {
                        WhileNode doWhileNode = parseWhileStatement(statementBlock, true);
                        baseStatement = doWhileNode;
                        break;
                    }
                    case FOR: {
                        ForNode forNode = parseForStatement(statementBlock);
                        baseStatement = forNode;
                        break;
                    }
                    default: {
                        throw new ParseException("Unrecognized system function used at ");
                    }
                }
            }

            // return statement
            else if(Type.RETURN.equals(top.getType())) {
                FunctionReturn functionReturn = parseFunctionReturn(statementBlock);
                baseStatement = functionReturn;
            }

            // function call
            else if(Type.DOLLAR.equals(top.getType())) {
                FunctionCall functionCall = parseFunctionCall(statementBlock);
                baseStatement = functionCall;
            } else if (Type.BREAK.equals(top.getType())) {
                lexer.strictMatch(Type.BREAK);
                baseStatement = new SkipStatement(SkipStatementType.BREAK);
            } else if (Type.CONTINUE.equals(top.getType())) {
                lexer.strictMatch(Type.CONTINUE);
                baseStatement = new SkipStatement(SkipStatementType.CONTINUE);
            } else {
                throw new ParseException("Unexpected character found at [" + top.getLineNumber() + ":" + top.getColumnNumber() + "]");
            }

            statements.add(baseStatement);
            top = lexer.top();
            if(top == null) {
                throw new ParseException("Abrupt end of program detected");
            }
        }
        lexer.strictMatch(Type.OPEN_CURLY_BRACE);
        statementBlock.setStatements(statements);

        return statementBlock;
    }

    private FunctionReturn parseFunctionReturn(SymbolTable symbolTable) {
        lexer.strictMatch(Type.RETURN);
        RhsExpression returnValue = parseBinaryExpression(symbolTable);
        return new FunctionReturn(returnValue);
    }

    private ForNode parseForStatement(SymbolTable symbolTable) throws ParseException {
        lexer.strictMatch(Type.FOR);
        lexer.strictMatch(Type.OPEN_BRACKET);
        Token top = lexer.top();
        if(null == top) {
            throw new ParseException("Abrupt end of program detected");
        } else {
            BaseStatement init = null;
            BinaryExpression condition = null;
            VariableAssignment increment = null;
            if(Type.LESS_THAN.equals(top.getType())) {
                VariableDeclaration declaration = parseVariableDeclaration(symbolTable);
                symbolTable.addVariable(new VariableSymbolTableEntry(declaration));
                init = declaration;
            } else {
                init = parseVariableAssignment(symbolTable);
            }
            lexer.strictMatch(Type.SEMI_COLON);

            condition = parseBinaryExpression(symbolTable);

            lexer.strictMatch(Type.SEMI_COLON);

            increment = parseVariableAssignment(symbolTable);

            lexer.strictMatch(Type.CLOSE_BRACKET);

            StatementBlock block = parseBlock(symbolTable);

            return new ForNode(init, condition, increment, block);
        }
    }

    private WhileNode parseWhileStatement(SymbolTable symbolTable, boolean isDoWhile) throws ParseException {
        if(isDoWhile) lexer.strictMatch(Type.DO_WHILE);
        else lexer.strictMatch(Type.WHILE);

        lexer.strictMatch(Type.OPEN_BRACKET);
        BinaryExpression conditionStatement = parseBinaryExpression(symbolTable);
        lexer.strictMatch(Type.CLOSE_BRACKET);
        StatementBlock statementBlock = parseBlock(symbolTable);
        return new WhileNode(conditionStatement, statementBlock, isDoWhile);
    }

    private IfNode parseIfStatement(SymbolTable symbolTable) throws ParseException {
        lexer.strictMatch(Type.IF);
        lexer.strictMatch(Type.OPEN_BRACKET);
        BinaryExpression conditionStatement = parseBinaryExpression(symbolTable);
        lexer.strictMatch(Type.CLOSE_BRACKET);
        StatementBlock ifBlock = parseBlock(symbolTable);
        IfNode rootElseBlock = null;
        if (lexer.match(Type.ELSE)) {
            lexer.strictMatch(Type.ELSE);
            StatementBlock elseBlock = parseBlock(symbolTable);
            rootElseBlock = new IfNode(null, elseBlock, null);
        } else if (lexer.match(Type.ELSE_IF)) {
            IfNode leafElseIf = null;
            while(lexer.match(Type.ELSE_IF)) {
                lexer.strictMatch(Type.ELSE_IF);
                lexer.strictMatch(Type.OPEN_BRACKET);
                BinaryExpression elseIfExpression = parseBinaryExpression(symbolTable);
                lexer.strictMatch(Type.CLOSE_BRACKET);
                StatementBlock statementBlock = parseBlock(symbolTable);
                if(rootElseBlock == null) {
                    rootElseBlock = new IfNode(conditionStatement, statementBlock, null);
                    leafElseIf = rootElseBlock;
                } else {
                    IfNode inner = new IfNode(elseIfExpression, statementBlock, null);
                    leafElseIf.setElseBlock(inner);
                    leafElseIf = inner;
                }
            }
            lexer.strictMatch(Type.ELSE);
            StatementBlock elseBlock = parseBlock(symbolTable);
            IfNode finalElse = new IfNode(null, elseBlock, null);
            leafElseIf.setElseBlock(finalElse);
        }
        return new IfNode(conditionStatement, ifBlock, rootElseBlock);
    }

    private VariableAssignment parseVariableAssignment(SymbolTable symbolTable) {
        Token identifier = parseIdentifier();
        IdentifierNode identifierNode = getIdentifierNode(identifier, symbolTable);
        lexer.strictMatch(Type.EQUAL_TO);
        RhsExpression rhs = parseBinaryExpression(symbolTable);
        return new VariableAssignment(identifierNode, rhs);
    }

    private IdentifierNode getIdentifierNode(Token identifierToken, SymbolTable symbolTable) {
        String identifierName = identifierToken.getValue();
        if(symbolTable.checkVariableInScope(identifierName)) {
            Pair<Integer, VariableSymbolTableEntry> variableSymbolTableEntryPair = symbolTable.getVariable(identifierName);
            return new IdentifierNode(variableSymbolTableEntryPair.value.getVariableName(), variableSymbolTableEntryPair.value.getDataType(), variableSymbolTableEntryPair.key);
        } else {
            lexer.addError("Undefined variable (" + identifierName + ") found at [" + identifierToken.getLineNumber() + ":" + identifierToken.getColumnNumber() + "]");
            return new IdentifierNode(identifierName, DataType.UNDEFINED, null);
        }
    }
}
