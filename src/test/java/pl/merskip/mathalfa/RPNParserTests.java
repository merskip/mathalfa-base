package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.elementary.fragment.ElementaryRegister;
import pl.merskip.mathalfa.infixparser.ReversePolishNotationConverter;

import java.util.List;

public class RPNParserTests {

    @Test
    public void rpnParser() {
        String plainText = "1+(3+4)+1";
        ReversePolishNotationConverter reversePolishNotationConverter = new ReversePolishNotationConverter(new FragmentsSplitter(plainText, new ElementaryRegister()));
    
        List<Fragment> fragments = reversePolishNotationConverter.convert();
        
        System.out.print("\"" + plainText + "\" ->");
        fragments.forEach(fragment -> System.out.print(" " + fragment.getText()));
        System.out.println();
    
        Assert.assertEquals(7, fragments.size());
        fragments.forEach(Assert::assertNotNull);

        Assert.assertEquals("1", fragments.get(0).getText());
        Assert.assertEquals("3", fragments.get(1).getText());
        Assert.assertEquals("4", fragments.get(2).getText());
        Assert.assertEquals("+", fragments.get(3).getText());
        Assert.assertEquals("+", fragments.get(4).getText());
        Assert.assertEquals("1", fragments.get(5).getText());
        Assert.assertEquals("+", fragments.get(6).getText());
    }
}
