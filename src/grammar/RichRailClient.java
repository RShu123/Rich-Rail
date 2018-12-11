package grammar;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class RichRailClient {
    public static void main(String[] args) throws Exception {

        // create a CharStream that reads from standard input
        // CharStream input = CharStreams.fromStream(System.in);
    	
        CharStream is = CharStreams.fromString("delete train jeroen");
        
        RichRailLexer lexer = new RichRailLexer(is);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        RichRailParser parser = new RichRailParser(tokens);
        
        ParserRuleContext commandContext = parser.command();
        
        ParseTreeWalker walker = new ParseTreeWalker();
        RichRailCommand listener = new RichRailCommand();

        walker.walk(listener, commandContext);
    }

}

