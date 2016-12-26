package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.infixparser.Fragment;
import pl.merskip.mathalfa.infixparser.FragmentsSplitter;

import java.util.List;

public class TokensParserTests {
    
    @Test
    public void splitToTokens() {
        String plainText = "1 + (3 \t+4)\n--1 ";
        FragmentsSplitter splitter = new FragmentsSplitter(plainText);
        splitter.addWhitespaces(' ', '\t', '\n');
        splitter.addReaders((buffer, c) -> buffer.isEmpty() && c == '(' || c == ')');
        splitter.addReaders((buffer, c) -> Character.isDigit(c));
        splitter.addReaders((buffer, c) -> buffer.isEmpty() && c == '+');
        splitter.addReaders((buffer, c) -> buffer.isEmpty() && c == '-');
    
        List<Fragment> fragments = splitter.split();
        
        Assert.assertEquals(10, fragments.size());
        System.out.printf("\"%s\" -> ", plainText);
        for (Fragment fragment : fragments) {
            Assert.assertNotNull(fragment);
            System.out.print(" " + fragment.getText());
            
            Assert.assertNotEquals(0, fragment.getText().length());
        }
        System.out.print("\n");
        
    }
}
