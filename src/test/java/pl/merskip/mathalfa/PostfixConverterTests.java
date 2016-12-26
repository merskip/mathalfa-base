package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.elementary.fragment.ElementaryRegister;
import pl.merskip.mathalfa.infixparser.PostfixConverter;

import java.util.List;

public class PostfixConverterTests {

    @Test
    public void test1() {
        String plainText = "1+(3+4)+1";
        PostfixConverter postfixConverter =
                new PostfixConverter(
                        new FragmentsSplitter(
                                new ElementaryRegister(), plainText));
    
        List<Fragment> fragments = postfixConverter.convert();
        
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
