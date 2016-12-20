package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.core.Expression;
import pl.merskip.mathalfa.elementary.ElementaryRegister;
import pl.merskip.mathalfa.elementary.RationalNumber;
import pl.merskip.mathalfa.elementary.RationalNumberAddition;
import pl.merskip.mathalfa.infixparser.RPNParser;
import pl.merskip.mathalfa.infixparser.Token;
import pl.merskip.mathalfa.infixparser.TokensParser;

import java.util.List;

public class RPNParserTests {
    
    @Test
    public void rpnParser() {
        String plainText = "1+(3+4)+1";
        List<Token> tokens = new TokensParser(plainText).splitToTokens();
        Expression expression = new RPNParser(tokens, new ElementaryRegister()).parse();
    
        Assert.assertEquals(7, expression.symbolsList().size());
        expression.symbolsList().forEach(Assert::assertNotNull);
        
        Class[] exceptedClasses= new Class[] {
                RationalNumber.class,
                RationalNumber.class,
                RationalNumber.class,
                RationalNumberAddition.class,
                RationalNumberAddition.class,
                RationalNumber.class,
                RationalNumberAddition.class
        };
        for (int i = 0; i < expression.symbolsList().size(); i++) {
            Assert.assertEquals(exceptedClasses[i], expression.symbolsList().get(i).getClass());
        }
    }
}
