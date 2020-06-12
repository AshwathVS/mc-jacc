package parser.numerics;

import core.Token;
import core.Type;
import lexer.LexerWrapper;
import parser.Parser;

/**
 * Grammar followed is as follows:
 * E -> T | T + E | T - E
 * T -> int | int * T | int / T
 */
public class NumericParser extends Parser {
    public NumericParser(LexerWrapper lexerWrapper) {
        super(lexerWrapper);
    }

    @Override
    public NumericExpressionNode parse() {
        return parseExpression();
    }

    private NumericExpressionNode parseExpression() {
        NumericExpressionNode left = null, right = null;
        left = parseTerm();
        Token token = lexerWrapper.match(Type.ADD, Type.SUBTRACT);
        if(null != token) right = parseExpression();
        return new NumericExpressionNode(token.getType(), left, right);
    }

    private NumericExpressionNode parseTerm() {
        NumericExpressionNode left = null, right = null;
        left = parseNumber();
        Token token = lexerWrapper.match(Type.MULTIPLY, Type.DIVIDE);
        if(null != token) right = parseTerm();
        return new NumericExpressionNode(token.getType(), left, right);
    }

    private NumericNode parseNumber() {
        Token token = lexerWrapper.match(Type.NUMBER);
        if(null != token) {
            return new NumericNode(Double.parseDouble(token.getValue()));
        } else return null;
    }
}
