package pl.merskip.mathalfa.base.test;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.FragmentException;
import pl.merskip.mathalfa.base.infixparser.PostfixParser;

import static org.junit.Assert.*;
import static pl.merskip.mathalfa.base.test.TestUtils.getParser;

public class PostfixParserTest {
    
    @Test
    public void parse_emptyString_returnsNull() {
        String plainText = "";
        PostfixParser parser = getParser();
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol(plainText);
        
        assertNull(rootSymbol);
    }
    
    @Test
    public void parse_onlyNumber_returnsIntegerNumber() {
        String plainText = "1";
        PostfixParser parser = getParser();
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol(plainText);
    
        assertNotNull(rootSymbol);
        assertTrue(rootSymbol instanceof TestUtils.IntegerNumber);
        Assert.assertEquals(1, ((TestUtils.IntegerNumber)rootSymbol).number);
    }
    
    @Test
    public void parse_simpleAddition_returnsAdditionAsRootSymbol() {
        String plainText = "1+2";
        PostfixParser parser = getParser();
        
        Symbol rootSymbol = parser.parseAndGetRootSymbol(plainText);
        
        assertNotNull(rootSymbol);
        assertTrue(rootSymbol instanceof TestUtils.AdditionOperator);
        TestUtils.AdditionOperator addition = (TestUtils.AdditionOperator) rootSymbol;
        assertTrue(addition.arg1 instanceof TestUtils.IntegerNumber);
        Assert.assertEquals(1, ((TestUtils.IntegerNumber)addition.arg1).number);
        Assert.assertEquals(2, ((TestUtils.IntegerNumber)addition.arg2).number);
    }
    
    @Test(expected = FragmentException.class)
    public void parse_missedArgument_throwsFragmentException() {
        String plainText = "1+";
        PostfixParser parser = getParser();
        
        parser.parseAndGetRootSymbol(plainText);
    }
}
