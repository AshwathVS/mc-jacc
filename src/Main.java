import lexer.LexerWrapper;
import parser.numerics.NumericExpressionNode;
import parser.numerics.NumericParser;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\Ashwath\\Documents\\Compiler Design\\lex\\inp.in");
        LexerWrapper lexer = new LexerWrapper(fileReader);
        NumericParser parser = new NumericParser(lexer);
        NumericExpressionNode node = parser.parse();
        parser.printParseTree(node);
        System.out.println("Evaluated output: " + node.evaluate());
    }
}
