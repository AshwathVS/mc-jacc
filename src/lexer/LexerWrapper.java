package lexer;

import core.Token;
import core.Type;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * Wrapper over the auto-generated Lexer class
 */
public class LexerWrapper {

    private Vector<Token> tokenList;

    private int currentIndex = 0;

    private Stack<Integer> history;

    public LexerWrapper(Reader in) throws IOException {
        Lexer lexer = new Lexer(in);
        tokenList = new Vector<>(500);
        while(!lexer.yyatEOF()) {
            Token token = lexer.next();
            if(null != token) tokenList.add(token);
        }

        history = new Stack<>();
    }

    private boolean checkIndexAccess() {
        return currentIndex < tokenList.size();
    }

    public Token peek() {
        if(checkIndexAccess()) {
            return tokenList.get(currentIndex);
        } else return null;
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
        if(checkIndexAccess()) {
            return tokenList.get(currentIndex++);
        } else return null;
    }

    /**
     * Saves the current index so that we can get back to this state later
     */
    public void snapshot() {
        history.add(currentIndex);
    }

    /**
     * Restores the current index to the last saved index
     */
    public void restore() {
        if(!history.isEmpty()) {
            currentIndex = history.pop();
        }
    }

}
