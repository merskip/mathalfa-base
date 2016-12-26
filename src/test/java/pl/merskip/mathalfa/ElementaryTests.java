package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.core.fragment.SymbolReader;
import pl.merskip.mathalfa.elementary.ElementaryRegister;
import pl.merskip.mathalfa.elementary.RationalNumber;

import java.util.List;

public class ElementaryTests {
    
    @Test
    public void test1() {
        String plainText = "1 + 2 - 3";
        FragmentsSplitter splitter = new FragmentsSplitter(plainText);
        splitter.addReadersFromRegister(new ElementaryRegister());
        splitter.addWhitespaces(FragmentsSplitter.DEFAULT_WHITESPACES);
        
        List<Fragment> fragments = splitter.split();
        
        Assert.assertEquals(5, fragments.size());
        
        Fragment firstFragment = fragments.get(0);
        Assert.assertTrue(firstFragment.getReader() instanceof SymbolReader);
        SymbolReader firstFragmentReader = (SymbolReader) firstFragment.getReader();
        Assert.assertEquals(RationalNumber.class, firstFragmentReader.create(firstFragment).getClass());
    }
    
    
}
