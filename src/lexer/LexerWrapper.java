package lexer;

import core.Token;
import core.Type;

import javax.print.FlavorException;
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

    private List<String> errors;

    public LexerWrapper(Reader in) throws IOException {
        Lexer lexer = new Lexer(in);
        tokenList = new Vector<>(500);
        while(!lexer.yyatEOF()) {
            Token token = lexer.next();
            if(null != token && !token.getType().equals(Type.WHITE_SPACE)) tokenList.add(token);
        }
        tokenList.add(new Token(Type.EOF, -1, -1));

        history = new Stack<>();
        errors = new ArrayList<>(50);
    }

    private boolean checkIndexAccess() {
        return currentIndex < tokenList.size();
    }

    public Token top() {
        if(checkIndexAccess()) {
            return tokenList.get(currentIndex);
        } else return null;
    }

    public Token strictMatch(Type type) {
        Token front = top();
        if(null != front && type.equals(front.getType())) return nextToken();
        else {
            // Since we are sure what should come next, we will send the desired token,
            // so that we can continue parsing and will log this as error.
            int lineNumber = 0, columnNumber = 0;
            if(currentIndex > 0) {
                lineNumber = tokenList.get(currentIndex - 1).getLineNumber();
                columnNumber = tokenList.get(currentIndex - 1).getColumnNumber();
            }

            // log error
            this.errors.add("Expected token of type (" + type.toString() + "), but found (" + front.getType() + ") at [" + lineNumber + ":" + columnNumber + "]");

            // return false token
            return new Token(type, lineNumber, columnNumber);
        }
    }

    public boolean match(Type... types) {
        Token front = top();
        if(null != front) {
            for(Type type : types) {
                if(front.getType().equals(type)) return true;
            }
        }
        return false;
    }

    public boolean match(List<Type> types) {
        Token front = top();
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

    public boolean eof() {
        return Type.EOF.equals(tokenList.get(currentIndex).getType());
    }

    public Token[] getFewSurroundingTokens() {
        int index = currentIndex - 2, iter = 0, i = 0;
        if(index < 0) index = 0;
        else if (index > tokenList.size()) index = tokenList.size() - 6;

        Token[] tokens = new Token[5];
        while(iter < 5) {
            tokens[i] = tokenList.get(index);
            index++;
            iter++;
        }
        return tokens;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
