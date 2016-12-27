package pl.merskip.mathalfa.test;

import org.junit.Test;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentException;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;

import java.util.List;

import static org.junit.Assert.*;

public class FragmentsSplitterTest {
    
    @Test
    public void split_emptyString_returnsEmptyList() {
        FragmentsSplitter splitter = new FragmentsSplitter("");
        
        List<Fragment> fragments = splitter.split();
        
        assertNotNull(fragments);
        assertTrue(fragments.isEmpty());
    }
    
    @Test
    public void split_onlyWhitespace_returnsEmptyList() {
        FragmentsSplitter splitter = new FragmentsSplitter(" ");
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertTrue(fragments.isEmpty());
    }
    
    @Test
    public void split_multiWhitespaces_returnsEmptyList() {
        FragmentsSplitter splitter = new FragmentsSplitter("  ");
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertTrue(fragments.isEmpty());
    }
    
    @Test(expected = FragmentException.class)
    public void split_unknownCharOnStart_throwsException() {
        FragmentsSplitter splitter = new FragmentsSplitter("x");
        
        splitter.split();
    }
    
    @Test(expected = FragmentException.class)
    public void split_unknownCharAfterWhitespace_throwsException() {
        FragmentsSplitter splitter = new FragmentsSplitter(" x");
        splitter.addWhitespaces(' ');
        
        splitter.split();
    }
    
    @Test
    public void split_oneSymbol_returnsOneFragment() {
        FragmentsSplitter splitter = new FragmentsSplitter("1");
        splitter.addReaders((buffer, c) -> c == '1');
    
        List<Fragment> fragments = splitter.split();
        
        assertNotNull(fragments);
        assertEquals(1, fragments.size());
        assertEqualsFragment(0, "1", fragments.get(0));
    }
    
    @Test
    public void split_twoSymbol_returnsOneFragments() {
        FragmentsSplitter splitter = new FragmentsSplitter("++");
        splitter.addReaders((buffer, c) -> c == '+');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertEquals(1, fragments.size());
        assertEqualsFragment(0, "++", fragments.get(0));
    }
    
    @Test
    public void split_twoSymbolAndReaderAcceptOneChar_returnsTwoFragments() {
        FragmentsSplitter splitter = new FragmentsSplitter("++");
        splitter.addReaders((buffer, c) -> buffer.isEmpty() && c == '+');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(1, "+", fragments.get(1));
    }
    
    @Test
    public void split_twoDifferentSymbol_returnsTwoFragments() {
        FragmentsSplitter splitter = new FragmentsSplitter("+-");
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addReaders((buffer, c) -> c == '-');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(1, "-", fragments.get(1));
    }
    
    
    @Test
    public void split_twoSymbolSeparatedByWhitespace_returnsTwoFragments() {
        FragmentsSplitter splitter = new FragmentsSplitter("+ +");
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(2, "+", fragments.get(1));
    }
    
    @Test
    public void split_threeSymbolWithMultiWhitespace_returnsThreeFragments() {
        FragmentsSplitter splitter = new FragmentsSplitter("  +   + + ");
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertEquals(3, fragments.size());
        assertEqualsFragment(2, "+",fragments.get(0));
        assertEqualsFragment(6, "+", fragments.get(1));
        assertEqualsFragment(8, "+", fragments.get(2));
    }
    
    @Test
    public void split_twoDifferentSymbolSeparatedByWhitespace_returnsTwoSymbol() {
        FragmentsSplitter splitter = new FragmentsSplitter("+ -");
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addReaders((buffer, c) -> c == '-');
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split();
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(2, "-", fragments.get(1));
    }
    
    private static void assertEqualsFragment(int exceptedIndex, String exceptedText,
                                             Fragment fragment) {
        assertEquals(exceptedIndex, fragment.getIndex());
        assertEquals(exceptedText, fragment.getText());
    }
}
