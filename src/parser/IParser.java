package parser;

import lexer.LexerWrapper;
import parser.common.ParseResult;

public interface IParser<T> {

    ParseResult<T> parse();
}
