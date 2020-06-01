import core.Token;

import lexer.Lexer;

import java.io.FileReader;


public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\Ashwath\\Documents\\Compiler Design\\lex\\inp.in");
        Lexer lexer = new Lexer(fileReader);
        while(!lexer.yyatEOF()) {
            Token token = lexer.nextToken();
            if(null != token) System.out.println(token.getType() + ": " + token.getValue());
        }
    }
}
