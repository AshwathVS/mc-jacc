import core.Token;
import lexer.Lexer;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\Ashwath\\Documents\\Compiler Design\\lex\\inp.in");
        Lexer lexer = new Lexer(fileReader);
        while(!lexer.yyatEOF()) {
            Token token = lexer.next();
            if(token != null) {
                System.out.println(token.getType() + " [" + token.getLineNumber() + ":" + token.getColumnNumber() + "]");
            }
        }
//        DraftParser parser = new DraftParser(lexer);
//        ProgramNode node = parser.parse();
////        parser.printParseTree(node);
////        System.out.println("Evaluated output: " + node.evaluate());
//        System.out.println(node);
    }
}
