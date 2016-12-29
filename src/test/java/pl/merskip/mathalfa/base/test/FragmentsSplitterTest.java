package pl.merskip.mathalfa.base.test;

import org.junit.Test;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.FragmentException;
import pl.merskip.mathalfa.base.core.fragment.FragmentsSplitter;

import java.util.List;

import static org.junit.Assert.*;
import static pl.merskip.mathalfa.base.test.TestUtils.assertEqualsFragment;

public class FragmentsSplitterTest {
    
    @Test
    public void split_emptyString_returnsEmptyList() {
        String plainText = "";
        FragmentsSplitter splitter = new FragmentsSplitter();
        
        List<Fragment> fragments = splitter.split(plainText);
        
        assertNotNull(fragments);
        assertTrue(fragments.isEmpty());
    }
    
    @Test
    public void split_onlyWhitespace_returnsEmptyList() {
        String plainText = " ";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertTrue(fragments.isEmpty());
    }
    
    @Test
    public void split_multiWhitespaces_returnsEmptyList() {
        String plainText = "  ";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertTrue(fragments.isEmpty());
    }
    
    @Test(expected = FragmentException.class)
    public void split_unknownCharOnStart_throwsException() {
        String plainText= "x";
        FragmentsSplitter splitter = new FragmentsSplitter();
        
        splitter.split(plainText);
    }
    
    @Test(expected = FragmentException.class)
    public void split_unknownCharAfterWhitespace_throwsException() {
        String plainText = " x";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addWhitespaces(' ');
        
        splitter.split(plainText);
    }
    
    @Test
    public void split_oneSymbol_returnsOneFragment() {
        String plainText = "1";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addReaders((buffer, c) -> c == '1');
    
        List<Fragment> fragments = splitter.split(plainText);
        
        assertNotNull(fragments);
        assertEquals(1, fragments.size());
        assertEqualsFragment(0, "1", fragments.get(0));
    }
    
    @Test
    public void split_twoSymbol_returnsOneFragments() {
        String plainText = "++";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addReaders((buffer, c) -> c == '+');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertEquals(1, fragments.size());
        assertEqualsFragment(0, "++", fragments.get(0));
    }
    
    @Test
    public void split_twoSymbolAndReaderAcceptOneChar_returnsTwoFragments() {
        String plainText = "++";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addReaders((buffer, c) -> buffer.isEmpty() && c == '+');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(1, "+", fragments.get(1));
    }
    
    @Test
    public void split_twoDifferentSymbol_returnsTwoFragments() {
        String plainText = "+-";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addReaders((buffer, c) -> c == '-');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(1, "-", fragments.get(1));
    }
    
    
    @Test
    public void split_twoSymbolSeparatedByWhitespace_returnsTwoFragments() {
        String plainText = "+ +";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(2, "+", fragments.get(1));
    }
    
    @Test
    public void split_threeSymbolWithMultiWhitespace_returnsThreeFragments() {
        String plainText = "  +   + + ";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertEquals(3, fragments.size());
        assertEqualsFragment(2, "+",fragments.get(0));
        assertEqualsFragment(6, "+", fragments.get(1));
        assertEqualsFragment(8, "+", fragments.get(2));
    }
    
    @Test
    public void split_twoDifferentSymbolSeparatedByWhitespace_returnsTwoSymbol() {
        String plainText = "+ -";
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addReaders((buffer, c) -> c == '+');
        splitter.addReaders((buffer, c) -> c == '-');
        splitter.addWhitespaces(' ');
    
        List<Fragment> fragments = splitter.split(plainText);
    
        assertNotNull(fragments);
        assertEquals(2, fragments.size());
        assertEqualsFragment(0, "+",fragments.get(0));
        assertEqualsFragment(2, "-", fragments.get(1));
    }
}
