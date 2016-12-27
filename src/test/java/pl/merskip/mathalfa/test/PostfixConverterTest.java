package pl.merskip.mathalfa.test;

import org.junit.Test;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentException;
import pl.merskip.mathalfa.infixparser.PostfixConverter;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static pl.merskip.mathalfa.test.TestUtils.*;

public class PostfixConverterTest {

    @Test
    public void convert_emptyString_returnsEmptyList() {
        PostfixConverter converter = getConverter("");
    
        List<Fragment> fragments = converter.convert();
        
        assertNotNull(fragments);
        assertTrue(fragments.isEmpty());
    }
    
    @Test
    public void convert_oneSymbol_returnsOneSymbol() {
        PostfixConverter converter = getConverter("1");
        
        List<Fragment> fragments = converter.convert();
        
        assertEquals(1, fragments.size());
        assertEqualsFragment(0, "1", fragments.get(0));
    }
    
    @Test
    public void convert_simpleAddition_returnsTwoNumberThenAddition() {
        PostfixConverter converter = getConverter("1+2");
    
        List<Fragment> fragments = converter.convert();
        print(fragments);
    
        assertEquals(3, fragments.size());
        assertEqualsFragment(0, "1", fragments.get(0));
        assertEqualsFragment(2, "2", fragments.get(1));
        assertEqualsFragment(1, "+", fragments.get(2));
    }
    
    @Test
    public void convert_twoAddition_returnsCorrectResult() {
        PostfixConverter converter = getConverter("1+2+3");
    
        List<Fragment> fragments = converter.convert();
        print(fragments);
    
        assertEquals(5, fragments.size());
        Iterator<Fragment> fragment = fragments.iterator();
        assertEqualsFragment(0, "1", fragment.next());
        assertEqualsFragment(2, "2", fragment.next());
        assertEqualsFragment(1, "+", fragment.next());
        assertEqualsFragment(4, "3", fragment.next());
        assertEqualsFragment(3, "+", fragment.next());
    }
    
    @Test
    public void convert_additionWithParenthesis_returnsCorrectResult() {
        PostfixConverter converter = getConverter("1+(2+3)");
    
        List<Fragment> fragments = converter.convert();
        print(fragments);
    
        assertEquals(5, fragments.size());
        Iterator<Fragment> fragment = fragments.iterator();
        assertEqualsFragment(0, "1", fragment.next());
        assertEqualsFragment(3, "2", fragment.next());
        assertEqualsFragment(5, "3", fragment.next());
        assertEqualsFragment(4, "+", fragment.next());
        assertEqualsFragment(1, "+", fragment.next());
    }
    
    @Test
    public void convert_additionWithTwoParenthesis_returnsCorrectResult() {
        PostfixConverter converter = getConverter("1+((2+3)+4)");
        
        List<Fragment> fragments = converter.convert();
        print(fragments);
        
        assertEquals(7, fragments.size());
        Iterator<Fragment> fragment = fragments.iterator();
        assertEqualsFragment(0, "1", fragment.next());
        assertEqualsFragment(4, "2", fragment.next());
        assertEqualsFragment(6, "3", fragment.next());
        assertEqualsFragment(5, "+", fragment.next());
        assertEqualsFragment(9, "4", fragment.next());
        assertEqualsFragment(8, "+", fragment.next());
        assertEqualsFragment(1, "+", fragment.next());
    }
    
    @Test(expected = FragmentException.class)
    public void convert_missedClosingParenthesis_throwsFragmentException() {
        PostfixConverter converter = getConverter("1+(2+3");
    
        converter.convert();
    }
    
    @Test(expected = FragmentException.class)
    public void convert_missedOpeningParenthesis_throwsFragmentException() {
        PostfixConverter converter = getConverter("1+2+3)");
    
        converter.convert();
    }
    
    @Test(expected = FragmentException.class)
    public void convert_missedOneClosingParenthesis_throwsFragmentException() {
        PostfixConverter converter = getConverter("1+((2+3)+4");
    
        converter.convert();
    }
    
    
    @Test(expected = FragmentException.class)
    public void convert_missedOneOpeningParenthesis_throwsFragmentException() {
        PostfixConverter converter = getConverter("1+(2+3)+4)");
        
        converter.convert();
    }
}
