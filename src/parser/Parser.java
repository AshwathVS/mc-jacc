package parser;

import core.ParseTreePrinter;
import lexer.LexerWrapper;

public class Parser {

    protected LexerWrapper lexerWrapper;

    public Parser(LexerWrapper lexerWrapper) {
        this.lexerWrapper = lexerWrapper;
    }

    public Object parse() {
        return null;
    }

    public void printParseTree(ParseTreePrinter parseTreePrinter) {
        printParseTree(parseTreePrinter, 0);
    }

    private void printParseTree(ParseTreePrinter parseTreePrinter, int tabSpace) {
        if(null == parseTreePrinter) return;

        int i = 0;
        while(i++ < tabSpace) {
            System.out.print("\t|");
        }
        System.out.print("__ ");
        System.out.println(parseTreePrinter.getValue());
        printParseTree(parseTreePrinter.getLeft(), tabSpace + 1);
        printParseTree(parseTreePrinter.getRight(), tabSpace + 1);
    }
}
