package pl.merskip.mathalfa.test;

import org.junit.Test;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.core.fragment.FragmentException;
import pl.merskip.mathalfa.infixparser.PostfixParser;

import static org.junit.Assert.*;
import static pl.merskip.mathalfa.test.TestUtils.*;

public class PostfixParserTest {
    
    @Test
    public void parse_emptyString_returnsNull() {
        PostfixParser parser = getParser("");
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol();
        
        assertNull(rootSymbol);
    }
    
    @Test
    public void parse_onlyNumber_returnsIntegerNumber() {
        PostfixParser parser = getParser("1");
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol();
    
        assertNotNull(rootSymbol);
        assertTrue(rootSymbol instanceof IntegerNumber);
        assertEquals(1, ((IntegerNumber)rootSymbol).number);
    }
    
    @Test
    public void parse_simpleAddition_returnsAdditionAsRootSymbol() {
        PostfixParser parser = getParser("1+2");
        
        Symbol rootSymbol = parser.parseAndGetRootSymbol();
        
        assertNotNull(rootSymbol);
        assertTrue(rootSymbol instanceof AdditionOperator);
        AdditionOperator addition = (AdditionOperator) rootSymbol;
        assertTrue(addition.arg1 instanceof IntegerNumber);
        assertEquals(1, ((IntegerNumber)addition.arg1).number);
        assertEquals(2, ((IntegerNumber)addition.arg2).number);
    }
    
    @Test(expected = FragmentException.class)
    public void parse_missedArgument_throwsFragmentException() {
        PostfixParser parser = getParser("1+");
        
        parser.parseAndGetRootSymbol();
    }
}
