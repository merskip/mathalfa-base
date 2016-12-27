package pl.merskip.mathalfa.test;

import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentReader;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.infixparser.PostfixConverter;

import java.util.List;

import static org.junit.Assert.assertEquals;

class TestUtils {
    
    private static String lastConvertedPlainTest = null;
    
    private TestUtils() {
        
    }
    
    static void print(List<Fragment> fragments) {
        if (lastConvertedPlainTest != null)
            System.out.print("\"" + lastConvertedPlainTest + "\" ->");
        fragments.forEach(fragment ->
                System.out.print(" \033[4m" + fragment.getText() + "\033[0m"));
        System.out.println();
    }
    
    static void assertEqualsFragment(int exceptedIndex, String exceptedText,
                                             Fragment fragment) {
        assertEquals(exceptedIndex, fragment.getIndex());
        assertEquals(exceptedText, fragment.getText());
    }
    
    
    static PostfixConverter getConverter(String plainText) {
        lastConvertedPlainTest = plainText;
        FragmentsSplitter splitter = new FragmentsSplitter(plainText);
        splitter.addWhitespaces(' ');
        splitter.addReaders(numberReader(), additionReader());
        return new PostfixConverter(splitter);
    }
    
    private static FragmentReader numberReader() {
        return (buffer, c) -> Character.isDigit(c);
    }
    
    private static FragmentReader additionReader() {
        return new FragmentReader() {
            @Override
            public boolean fulfills(String buffer, char c) {
                return buffer.isEmpty()
                        && c == '+';
            }
            
            @Override
            public int getPrecedence() {
                return 1;
            }
            
            @Override
            public Associative getAssociative() {
                return Associative.Left;
            }
        };
    }
    
}
