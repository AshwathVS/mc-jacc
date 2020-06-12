package parser.numerics;

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
        return null;
    }

    private NumericExpressionNode parseExpression() {
        NumericExpressionNode term = parseTerm();
        if() {

        }
    }

    private NumericExpressionNode parseTerm() {

    }
}
