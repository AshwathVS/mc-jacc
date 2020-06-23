import lexer.LexerWrapper;
import parser.ParseException;
import parser.Parser;
import parser.ProgramNode;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\Ashwath\\Documents\\Compiler Design\\lex\\inp.in");
        LexerWrapper lexer = new LexerWrapper(fileReader);
//        while(!lexer.eof()) {
//            Token token = lexer.nextToken();
//            if(token != null) {
//                System.out.println(token.getType() + " [" + token.getLineNumber() + ":" + token.getColumnNumber() + "]");
//            }
//        }
        Parser parser = new Parser(lexer);
        try {
            ProgramNode node = parser.parse();
            System.out.println(node);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
