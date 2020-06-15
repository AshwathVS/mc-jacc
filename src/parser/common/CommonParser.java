package parser.common;

import core.Token;
import core.Type;
import lexer.LexerWrapper;

public class CommonParser {

    public static IdNode parseId(LexerWrapper lexerWrapper) {
        lexerWrapper.snapshot();
        if(lexerWrapper.match(Type.ID)) {
            new IdNode(TypeConverter.DataType.UNKNOWN, )
        }
    }
}
