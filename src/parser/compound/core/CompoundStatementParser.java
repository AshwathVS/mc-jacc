package parser.compound.core;

import core.Token;
import core.Type;
import lexer.LexerWrapper;
import parser.IParser;
import parser.common.CommonParser;
import parser.common.IdNode;
import parser.common.ParseResult;
import parser.common.TypeConverter;
import parser.compound.*;

public class CompoundStatementParser implements IParser {

    private LexerWrapper lexerWrapper;

    public CompoundStatementParser(LexerWrapper lexerWrapper) {
        this.lexerWrapper = lexerWrapper;
    }

    @Override
    public ParseResult parse() {

        CompoundExpression expression = null;

        int i = 0;
        while(i < CompoundStatementType.values().length) {
            lexerWrapper.snapshot();

            if(i == 0) expression = parseArithmeticStatement();
            else if(i == 1) expression = parseAssignmentStatement();
            else if(i == 2) expression = parseFunctionCallStatement();
            else if(i == 3) expression = parseUnaryStatement();
            else if(i == 4) expression = parseRelationalStatement();
            else if(i == 5) expression = parseLogicalStatement();

            if(expression != null) {
                return new ParseResult(expression);
            } else {
                lexerWrapper.restore();
            }
            i++;
        }
        return ParseResult.FAILED_PARSE;
    }

    /** Arithmetic Statement Start*/
    /**
     * Grammar followed is as follows:
     * E -> T | T + T | T - T
     * T -> F | F * F | F / F
     * F -> ID | NUMBER | STRING | (E)
     */
    private ArithmeticStatementNode parseArithmeticStatement() {
        ArithmeticStatementNode left = parseATerm();
        if(null == left) return null;

        while(lexerWrapper.match(Type.ADD, Type.SUBTRACT)) {
            Type operator = lexerWrapper.nextToken().getType();
            TypeConverter.ArithmeticOperator arithmeticOperator = TypeConverter.getArithmeticOperator(operator);
            ArithmeticStatementNode right = parseATerm();
            left = new ArithmeticStatementNode(arithmeticOperator, left, right);
        }
        return left;
    }

    private ArithmeticStatementNode parseATerm() {
        ArithmeticStatementNode left = parseAFactor();
        if(null == left) return null;

        while(lexerWrapper.match(Type.MULTIPLY, Type.DIVIDE)) {
            Type operator = lexerWrapper.nextToken().getType();
            TypeConverter.ArithmeticOperator arithmeticOperator = TypeConverter.getArithmeticOperator(operator);
            ArithmeticStatementNode right = parseAFactor();
            left = new ArithmeticStatementNode(arithmeticOperator, left, right);
        }
        return left;
    }

    private ArithmeticStatementNode parseAFactor() {

        if(lexerWrapper.match(Type.OPEN_BRACKET)) {
            return parseArithmeticStatement();
        } else {
             IdNode idNode = CommonParser.parseId(lexerWrapper);
             if(null != idNode) return new OperandNode(idNode);
             else return null;
        }
    }


    /** Arithmetic Statement End*/



    /** Assignment Statement Start*/
    private AssignmentStatementNode parseAssignmentStatement() {
        IdNode idNode = CommonParser.parseId(lexerWrapper);
        if(null != idNode) {
            if(lexerWrapper.match(Type.EQUAL_TO)) {
                Token equalTo = lexerWrapper.nextToken();
                ArithmeticStatementNode arithmeticStatementNode = parseArithmeticStatement();
                if(null != arithmeticStatementNode) {
                    return new AssignmentStatementNode(idNode, arithmeticStatementNode);
                }
            }
        }
        return null;
    }

    /** Assignment Statement End*/


    /** Function Call Statement Start*/
    private FunctionCallStatementNode parseFunctionCallStatement() {

    }
    /** Function Call Statement End*/



    /** Logical Statement Start*/
    private LogicalStatementNode parseLogicalStatement() {

    }

    /** Logical Statement End*/


    /** Relational Statement Start*/
    private RelationalStatementNode parseRelationalStatement() {

    }

    /** Relational Statement End*/


    /** Unary Statement Start*/
    private UnaryStatementNode parseUnaryStatement() {

    }
    /** Unary Statement End*/
}
