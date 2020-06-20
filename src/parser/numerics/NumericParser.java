package parser.numerics;

import core.Token;
import core.Type;
import lexer.LexerWrapper;
import parser.Parser;

/**
 * Grammar followed is as follows:
 * E -> T | T + T | T - T
 * T -> F | F * F | F / F
 * F -> INT | (E)
 */
public class NumericParser extends Parser {

    public NumericParser(LexerWrapper lexerWrapper) {
        super(lexerWrapper);
    }

    @Override
    public NumericExpressionNode parse() {
        return parseExpression();
    }

    public NumericExpressionNode parseExpression() {
        NumericExpressionNode left = parseTerm();
        while(lexerWrapper.match(Type.ADD, Type.SUBTRACT)) {
            Token operator = lexerWrapper.nextToken();
            NumericExpressionNode right = parseTerm();
            left = new NumericExpressionNode(operator.getType(), left, right);
        }
        return left;
    }

    public NumericExpressionNode parseTerm() {
        NumericExpressionNode left = parseFactor();
        while(lexerWrapper.match(Type.MULTIPLY, Type.DIVIDE)) {
            Token operator = lexerWrapper.nextToken();
            NumericExpressionNode right = parseFactor();
            left = new NumericExpressionNode(operator.getType(), left, right);
        }
        return left;
    }

    public NumericExpressionNode parseFactor() {
        if(lexerWrapper.strictMatch(Type.OPEN_BRACKET)) {
            Token openBracket = lexerWrapper.nextToken();
            NumericExpressionNode expressionNode = parseExpression();
            if(lexerWrapper.strictMatch(Type.CLOSE_BRACKET)) {
                // Todo: Throw exception
            }
            Token closeBracket = lexerWrapper.nextToken();
            return expressionNode;
        } else if (lexerWrapper.match(Type.INTEGER_LITERAL, Type.DOUBLE_LITERAL)) {
            return new NumericNode(Double.parseDouble(lexerWrapper.nextToken().getValue()));
        } else return null; // TODO: Throw exception
    }
}
