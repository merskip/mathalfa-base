package pl.merskip.mathalfa;

import org.junit.Test;
import pl.merskip.mathalfa.core.Expression;
import pl.merskip.mathalfa.infixparser.RPNParser;
import pl.merskip.mathalfa.infixparser.Token;
import pl.merskip.mathalfa.infixparser.TokensParser;

import java.util.List;

public class RPNParserTests {
    
    @Test
    public void rpnParser() {
        String plainText = "1+(3+4)+1";
        List<Token> tokens = new TokensParser(plainText).splitToTokens();
        Expression expression = new RPNParser(tokens).parse();
        
        
    }
}
