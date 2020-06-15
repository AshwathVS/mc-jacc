package parser;

import lexer.LexerWrapper;
import parser.common.ParseTreePrinter;

import java.util.List;

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
        for(ParseTreePrinter child : (List<ParseTreePrinter>)parseTreePrinter.getChildren()) {
            printParseTree(child, tabSpace + 1);
        }
    }
}
