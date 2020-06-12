import core.Token;

import lexer.Lexer;
import lexer.LexerWrapper;

import java.io.FileReader;


public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\Ashwath\\Documents\\Compiler Design\\lex\\inp.in");
        LexerWrapper lexer = new LexerWrapper(fileReader);
        while(!lexer.yyatEOF()) {
            Token token = lexer.nextToken();
            lexer.nextToken();
            if(null != token) System.out.println(token.getType() + ": " + token.getValue());
        }
    }
}
