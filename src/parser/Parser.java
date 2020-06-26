package parser;

import core.Pair;
import core.Token;
import core.Type;
import lexer.LexerWrapper;
import parser.controlAndLoopStatements.*;
import parser.literal.Literal;
import parser.literal.LiteralType;
import parser.symbolTable.FunctionSymbolTableEntry;
import parser.symbolTable.SymbolTable;
import parser.symbolTable.VariableSymbolTableEntry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {
    private LexerWrapper lexer;

    public Parser(LexerWrapper lexerWrapper) {
        this.lexer = lexerWrapper;
    }

    public ProgramNode parse() throws Exception {
        try {
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
                    throw new ParseException("Unexpected character found at [" + token.getLineNumber() + ":" + token.getColumnNumber() + "]");
                }
            }
            if(null != lexer.getErrors() && !lexer.getErrors().isEmpty()) throw new ParseException(lexer.getErrors());
            return programNode;
        } catch(ParseException | NullPointerException ex) {
            if(ex instanceof ParseException) {
                ParseException parseException = (ParseException) ex;
                Set<String> errors = new HashSet<>(50);
                errors.add(parseException.getMessage());
                if(!lexer.getErrors().isEmpty()) {
                    errors.addAll(lexer.getErrors());
                }
                throw new ParseException(errors);
            } else {
                throw new NullPointerException("Abrupt end of program detected!");
            }
        }
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
            functionDeclaration.addVariable(new VariableSymbolTableEntry(variableDeclaration));
            if(lexer.isMatch(Type.COMMA)) lexer.nextToken();
        }

        lexer.strictMatch(Type.CLOSE_BRACKET);
        lexer.strictMatch(Type.B_RIGHT_SHIFT);

        // fetch function definition
        StatementBlock statementBlock = parseBlock(functionDeclaration);
        functionDeclaration.setFunctionBlock(statementBlock);
        return functionDeclaration;
    }

    private Token parseIdentifier() {
        return lexer.strictMatch(Type.ID);
    }

    private DataType parseDataType() {
        if(lexer.isMatch(DataType.getDataTypeOperators())) {
            return DataType.getDataType(lexer.nextToken().getType());
        } else {
            if(null == lexer.top())
                lexer.addError("DataType expected, but found ");
            else
                lexer.addError("DataType expected, but found " + lexer.top().getType() + " at [" + lexer.top().getLineNumber() + ":" + lexer.top().getColumnNumber() + "]");
            return DataType.UNDEFINED;
        }
    }

    private VariableDeclaration parseVariableDeclaration(SymbolTable symbolTable) throws ParseException {
        RhsExpression expression = null;
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
                if(DataType.BOOLEAN.equals(dataType)) {
                    expression = parseBooleanExpression(symbolTable);
                } else {
                    expression = parseBinaryExpression(symbolTable);
                }

                lexer.strictMatch(Type.CLOSE_BRACKET);
                lexer.strictMatch(Type.GREATER_THAN);
            } else {
                lexer.addError("Invalid variable declaration found at: " + "[" + top.getLineNumber() + ":" + top.getColumnNumber() +"]");
            }
        }
        if(symbolTable.checkVariableInScope(variableName)) lexer.addError("Duplicate variable name found (" + variableName + ")");
        return new VariableDeclaration(new IdentifierNode(variableName, dataType, null), expression);
    }

    private BooleanExpression parseBooleanExpression(SymbolTable symbolTable) throws ParseException {
        BooleanExpression left = parseBooleanTerm(symbolTable);

        while(lexer.isMatch(Type.OR)) {
            Token token = lexer.nextToken();
            BooleanOperator op = BooleanOperator.getOperator(token.getType());
            BooleanExpression right = parseBooleanTerm(symbolTable);
            left = new BooleanExpression(left, right, op);
        }
        return left;
    }

    private BooleanExpression parseBooleanTerm(SymbolTable symbolTable) throws ParseException {
        BooleanExpression left = parseBooleanFactor(symbolTable);

        while(lexer.isMatch(Type.AND)) {
            Token token = lexer.nextToken();
            BooleanOperator op = BooleanOperator.getOperator(token.getType());
            BooleanExpression right = parseBooleanTerm(symbolTable);
            left = new BooleanExpression(left, right, op);
        }
        return left;
    }

    private BooleanExpression parseBooleanFactor(SymbolTable symbolTable) throws ParseException {
        if(lexer.isMatch(Type.OPEN_BRACKET)) {
            lexer.strictMatch(Type.OPEN_BRACKET);
            BooleanExpression booleanExpression = parseBooleanExpression(symbolTable);
            lexer.strictMatch(Type.CLOSE_BRACKET);
            return booleanExpression;
        } else if (lexer.isMatch(Type.NOT)) {
            lexer.nextToken();
            BooleanOperator op = BooleanOperator.NOT;
            BooleanExpression booleanExpression = parseBooleanExpression(symbolTable);
            return new BooleanExpression(booleanExpression, null, op);
        } else {
            return parseRelationalExpression(symbolTable);
        }
    }

    private RelationalExpression parseRelationalExpression(SymbolTable symbolTable) throws ParseException {
        BinaryExpression rLeft = null, rRight = null;
        RelationalOperator operator = null;

        rLeft = parseBinaryOrBooleanExpBasedOnLookup(symbolTable);

        if(lexer.isMatch(RelationalOperator.getTypes())) {
            operator = RelationalOperator.getOperator(lexer.nextToken().getType());
            rRight = parseBinaryOrBooleanExpBasedOnLookup(symbolTable);
        } else {

            // if there is no relational operator, left should be a boolean literal or a function call returning boolean or a boolean identifier
            boolean validBooleanExp = false;
            RhsExpression left = rLeft.getLeft();
            BinaryExpressionType type = left.getExpressionType();
            if(BinaryExpressionType.LITERAL.equals(type)) {
                if(LiteralType.BOOLEAN_LITERAL.equals(((Literal) left).getLiteralType())) {
                    validBooleanExp = true;
                }
            } else if(BinaryExpressionType.FUNCTION_CALL.equals(type)) {
                FunctionCall functionCall = (FunctionCall) left;
                FunctionSymbolTableEntry functionDeclaration = symbolTable.getFunction(functionCall.getFunctionName(), functionCall.getArguments().size()).value;
                if(functionDeclaration.getReturnType().equals(DataType.BOOLEAN)) {
                    validBooleanExp = true;
                }
            } else if(BinaryExpressionType.IDENTIFIER.equals(type)) {
                IdentifierNode identifierNode = (IdentifierNode) left;
                if(DataType.BOOLEAN.equals(identifierNode.getDataType())) {
                    validBooleanExp = true;
                }
            }

            if(!validBooleanExp) {
                throw new ParseException("Invalid boolean expression defined at [" + lexer.top().getLineNumber() + ":" + lexer.top().getLineNumber() + "]");
            }
        }
        return new RelationalExpression(rLeft, rRight, operator);
    }

    private BinaryExpression parseBinaryOrBooleanExpBasedOnLookup(SymbolTable symbolTable) {
        if(lexer.isMatch(Type.BOOLEAN_LITERAL)) {
            return new BinaryExpression(Literal.parseTokenToLiteral(lexer.nextToken()));
        } else {
            return parseBinaryExpression(symbolTable);
        }
    }

    private BinaryExpression parseBinaryExpression(SymbolTable symbolTable) {
        ArithmeticOperator start = ArithmeticOperator.NULL;
        BinaryExpression left = parseBinaryTerm(symbolTable, start.getPrecedence());

        if(lexer.isMatch(ArithmeticOperator.getOperatorsWithGreaterPrecedence(start.getPrecedence()))) {
            Token token = lexer.top();
            start = ArithmeticOperator.getOperatorFromType(token.getType());
        }

        return leftRecurseForExpression(symbolTable, start, left);
    }

    private BinaryExpression leftRecurseForExpression(SymbolTable symbolTable, ArithmeticOperator start, BinaryExpression left) {
        while(null != start && lexer.isMatch(ArithmeticOperator.getOperatorsWithEqualPrecedence(start.getPrecedence()))) {
            Token token = lexer.nextToken();
            start = ArithmeticOperator.getOperatorFromType(token.getType());
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
        ArithmeticOperator start = null;
        if(lexer.isMatch(ArithmeticOperator.getOperatorsWithGreaterPrecedence(previousPrecedence))) {
            Token token = lexer.top();
            start = ArithmeticOperator.getOperatorFromType(token.getType());
        }
        return leftRecurseForExpression(symbolTable, start, left);
    }

    private BinaryExpression parseFactor(SymbolTable symbolTable) {

        boolean isSignedFactorFound = false;
        Token sign = null;
        BinaryExpression returnExp = null;

        Token top = lexer.top();
        if(top == null) {
            lexer.addError("Abrupt definition of binary expression");
            return null;
        } else {
            if(lexer.isMatch(Type.ADD, Type.SUBTRACT)) {
                isSignedFactorFound = true;
                sign = lexer.nextToken();
            }
        }

        // literal match
        if(lexer.isMatch(LiteralType.getArithmeticLiteralTypes())) {
            Literal literal = Literal.parseTokenToLiteral(lexer.nextToken());
            returnExp = new BinaryExpression(literal);
        }
        // Function call
        else if (null != top && Type.FUNCTION_CALL_PREFIX.equals(top.getType())) {
            FunctionCall functionCall = parseFunctionCall(symbolTable);
            returnExp = new BinaryExpression(functionCall);
        }

        // Bracketed expression
        else if (null != top && Type.OPEN_BRACKET.equals(top.getType())) {
            lexer.strictMatch(Type.OPEN_BRACKET);
            BinaryExpression expression = parseBinaryExpression(symbolTable);
            lexer.strictMatch(Type.CLOSE_BRACKET);
            returnExp = expression;
        }

        // Identifier usage
        else {
            Token variable = parseIdentifier();
            IdentifierNode identifierNode = getIdentifierNode(variable, symbolTable);
            returnExp = new BinaryExpression(identifierNode);
        }

        if(isSignedFactorFound) {
            returnExp.setOperator(ArithmeticOperator.getOperatorFromType(sign.getType()));
        }
        return returnExp;
    }

    private FunctionCall parseFunctionCall(SymbolTable symbolTable) {
        lexer.strictMatch(Type.FUNCTION_CALL_PREFIX);
        Token functionName = parseIdentifier();
        lexer.strictMatch(Type.OPEN_BRACKET);
        List<BinaryExpression> passedArguments = new ArrayList<>(10);
        while(!lexer.eof() && !lexer.isMatch(Type.CLOSE_BRACKET)) {
            BinaryExpression binaryExpression = parseBinaryExpression(symbolTable);
            passedArguments.add(binaryExpression);
            if(lexer.isMatch(Type.COMMA)) lexer.nextToken();
        }

        lexer.strictMatch(Type.CLOSE_BRACKET);
        return new FunctionCall(functionName.getValue(), passedArguments);
    }

    private StatementBlock parseBlock(SymbolTable symbolTable) throws ParseException {
        lexer.strictMatch(Type.OPEN_CURLY_BRACE);
        Token top = lexer.top();
        if (top == null) throw new ParseException("Abrupt end of program detected");
        List<BaseStatement> statements = new ArrayList<>(20);
        StatementBlock statementBlock = new StatementBlock(symbolTable);
        boolean isReturnStatementReached = false;
        while(!Type.CLOSE_CURLY_BRACE.equals(top.getType())) {
            BaseStatement baseStatement = null;

            // variable assignment
            if(Type.ID.equals(top.getType())) {
                baseStatement = parseVariableAssignment(statementBlock);
            }
            // variable declaration
            else if(lexer.isMatch(Type.LESS_THAN)) {
                VariableDeclaration variableDeclaration = parseVariableDeclaration(statementBlock);
                statementBlock.addVariable(new VariableSymbolTableEntry(variableDeclaration));
                baseStatement = variableDeclaration;
            }
            else if(Type.INTERNAL_CALL_PREFIX.equals(top.getType())) {
                // control statement
                lexer.strictMatch(Type.INTERNAL_CALL_PREFIX);
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
                isReturnStatementReached = true;
            }

            // function call
            else if(Type.FUNCTION_CALL_PREFIX.equals(top.getType())) {
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

            if(isReturnStatementReached) break;

            top = lexer.top();
            if(top == null) {
                throw new ParseException("Abrupt end of program detected");
            } else if (Type.CLOSE_CURLY_BRACE.equals(top.getType())) {
                break;
            }
        }
        if(isReturnStatementReached) {
            if (!lexer.isMatch(Type.CLOSE_CURLY_BRACE)) {
                lexer.addError("Unreachable code found at [" + lexer.top().getLineNumber() + ":" + lexer.top().getColumnNumber() + "]");
            }
        }
        lexer.strictMatch(Type.CLOSE_CURLY_BRACE);
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
            BaseStatement init;
            BooleanExpression condition;
            VariableAssignment increment;
            if(Type.LESS_THAN.equals(top.getType())) {
                VariableDeclaration declaration = parseVariableDeclaration(symbolTable);
                symbolTable.addVariable(new VariableSymbolTableEntry(declaration));
                init = declaration;
            } else {
                init = parseVariableAssignment(symbolTable);
            }
            lexer.strictMatch(Type.SEMI_COLON);

            condition = parseBooleanExpression(symbolTable);

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
        BooleanExpression conditionStatement = parseBooleanExpression(symbolTable);
        lexer.strictMatch(Type.CLOSE_BRACKET);
        StatementBlock statementBlock = parseBlock(symbolTable);
        return new WhileNode(conditionStatement, statementBlock, isDoWhile);
    }

    private IfNode parseIfStatement(SymbolTable symbolTable) throws ParseException {
        lexer.strictMatch(Type.IF);
        lexer.strictMatch(Type.OPEN_BRACKET);
        BooleanExpression conditionStatement = parseBooleanExpression(symbolTable);
        lexer.strictMatch(Type.CLOSE_BRACKET);
        StatementBlock ifBlock = parseBlock(symbolTable);
        IfNode rootElseBlock = null;
        if(lexer.isMatch(Type.INTERNAL_CALL_PREFIX)) {
            lexer.strictMatch(Type.INTERNAL_CALL_PREFIX);

            if (lexer.isMatch(Type.ELSE)) {
                lexer.strictMatch(Type.ELSE);
                StatementBlock elseBlock = parseBlock(symbolTable);
                rootElseBlock = new IfNode(null, elseBlock, null);
            } else if (lexer.isMatch(Type.ELSE_IF)) {
                IfNode leafElseIf = null;
                while (lexer.isMatch(Type.ELSE_IF)) {
                    lexer.strictMatch(Type.INTERNAL_CALL_PREFIX);
                    lexer.strictMatch(Type.ELSE_IF);
                    lexer.strictMatch(Type.OPEN_BRACKET);
                    BooleanExpression elseIfExpression = parseBooleanExpression(symbolTable);
                    lexer.strictMatch(Type.CLOSE_BRACKET);
                    StatementBlock statementBlock = parseBlock(symbolTable);
                    if (rootElseBlock == null) {
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

    /**
     * This method is used to find identifiers that have already been declared.
     * @param identifierToken
     * @param symbolTable
     * @return
     */
    private IdentifierNode getIdentifierNode(Token identifierToken, SymbolTable symbolTable) {
        String identifierName = identifierToken.getValue();
        if(symbolTable.checkVariable(identifierName)) {
            Pair<Integer, VariableSymbolTableEntry> variableSymbolTableEntryPair = symbolTable.getVariable(identifierName);
            return new IdentifierNode(variableSymbolTableEntryPair.value.getVariableName(), variableSymbolTableEntryPair.value.getDataType(), variableSymbolTableEntryPair.key);
        } else {
            lexer.addError("Undefined variable (" + identifierName + ") found at [" + identifierToken.getLineNumber() + ":" + identifierToken.getColumnNumber() + "]");
            return new IdentifierNode(identifierName, DataType.UNDEFINED, null);
        }
    }
}
