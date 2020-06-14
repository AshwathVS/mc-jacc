package lexer;

import core.Token;
import core.Type;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Wrapper over the auto-generated Lexer class
 */
public class LexerWrapper {

    Deque<Token> tokenList;

    public LexerWrapper(Reader in) throws IOException {
        Lexer lexer = new Lexer(in);
        tokenList = new ArrayDeque<>(500);
        while(!lexer.yyatEOF()) {
            Token token = lexer.next();
            if(null != token) tokenList.add(token);
        }
    }

    public Token peek() {
        return tokenList.peek();
    }

    public boolean match(Type... types) {
        Token front = peek();
        if(null != front) {
            for(Type type : types) {
                if(front.getType().equals(type)) return true;
            }
        }
        return false;
    }

    public Token nextToken() {
        return tokenList.pollFirst();
    }

}
