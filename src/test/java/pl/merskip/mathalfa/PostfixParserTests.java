package pl.merskip.mathalfa;

import org.junit.Test;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.elementary.fragment.ElementaryRegister;
import pl.merskip.mathalfa.infixparser.PostfixConverter;
import pl.merskip.mathalfa.infixparser.PostfixParser;

public class PostfixParserTests {
    
    @Test
    public void test1() {
        String plainText = "1+(2+3)+4";
        PostfixParser parser =
                new PostfixParser(
                        new PostfixConverter(
                                new FragmentsSplitter(
                                        new ElementaryRegister(), plainText)));
        
        Symbol rootSymbol = parser.parse();
    }
}
