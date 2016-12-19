package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.infixparser.Token;
import pl.merskip.mathalfa.infixparser.TokensParser;

import java.util.List;
import java.util.stream.Collectors;

public class TokensParserTests {
    
    @Test
    public void splitToTokens() {
        String plainText = "1 + (3  +4) +1";
        List<Token> tokens = new TokensParser(plainText).splitToTokens();
    
        System.out.println("\"" + plainText + "\" -> " + tokens.stream()
                .map(token -> token.text)
                .collect(Collectors.joining(" ")));
        
        Assert.assertEquals(9, tokens.size());
        
        Assert.assertEquals(new Token('1', 0), tokens.get(0));
        Assert.assertEquals(new Token('+', 2), tokens.get(1));
        Assert.assertEquals(new Token('(', 4), tokens.get(2));
        Assert.assertEquals(new Token('3', 5), tokens.get(3));
        Assert.assertEquals(new Token('+', 8), tokens.get(4));
        Assert.assertEquals(new Token('4', 9), tokens.get(5));
        Assert.assertEquals(new Token(')', 10), tokens.get(6));
        Assert.assertEquals(new Token('+', 12), tokens.get(7));
        Assert.assertEquals(new Token('1', 13), tokens.get(8));
    }
}
