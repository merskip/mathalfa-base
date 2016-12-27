package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.elementary.NumberAddition;
import pl.merskip.mathalfa.infixparser.PostfixConverter;
import pl.merskip.mathalfa.infixparser.PostfixParser;
import pl.merskip.mathalfa.shared.SharedFragmentsRegister;

public class PostfixParserTests {
    
    @Test
    public void test1() {
        String plainText = "1+(2+3)+4";
        PostfixParser parser =
                new PostfixParser(
                        new PostfixConverter(
                                new FragmentsSplitter(
                                        SharedFragmentsRegister.getInstance(), plainText)));
        
        Symbol rootSymbol = parser.parseAndGetRootSymbol();
    
        Assert.assertTrue(rootSymbol instanceof NumberAddition);
    }
}
