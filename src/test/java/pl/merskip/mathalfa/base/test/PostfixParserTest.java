package pl.merskip.mathalfa.base.test;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.FragmentException;
import pl.merskip.mathalfa.base.infixparser.PostfixParser;

import static org.junit.Assert.*;

public class PostfixParserTest {
    
    @Test
    public void parse_emptyString_returnsNull() {
        PostfixParser parser = TestUtils.getParser("");
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol();
        
        assertNull(rootSymbol);
    }
    
    @Test
    public void parse_onlyNumber_returnsIntegerNumber() {
        PostfixParser parser = TestUtils.getParser("1");
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol();
    
        assertNotNull(rootSymbol);
        assertTrue(rootSymbol instanceof TestUtils.IntegerNumber);
        Assert.assertEquals(1, ((TestUtils.IntegerNumber)rootSymbol).number);
    }
    
    @Test
    public void parse_simpleAddition_returnsAdditionAsRootSymbol() {
        PostfixParser parser = TestUtils.getParser("1+2");
        
        Symbol rootSymbol = parser.parseAndGetRootSymbol();
        
        assertNotNull(rootSymbol);
        assertTrue(rootSymbol instanceof TestUtils.AdditionOperator);
        TestUtils.AdditionOperator addition = (TestUtils.AdditionOperator) rootSymbol;
        assertTrue(addition.arg1 instanceof TestUtils.IntegerNumber);
        Assert.assertEquals(1, ((TestUtils.IntegerNumber)addition.arg1).number);
        Assert.assertEquals(2, ((TestUtils.IntegerNumber)addition.arg2).number);
    }
    
    @Test(expected = FragmentException.class)
    public void parse_missedArgument_throwsFragmentException() {
        PostfixParser parser = TestUtils.getParser("1+");
        
        parser.parseAndGetRootSymbol();
    }
}
