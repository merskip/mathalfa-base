package pl.merskip.mathalfa;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.core.Number;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.infixparser.PostfixParser;
import pl.merskip.mathalfa.operation.CalculateOperation;

public class CalculateOperationTest {
    
    @Test
    public void test1() {
        String plainText = "1+2";
        CalculateOperation operation = new CalculateOperation();
        
        Number number = (Number) operation.executeForResult(PostfixParser.parser(plainText));
        
        Assert.assertEquals(3.0, number.toDouble(), Double.MIN_VALUE);
    }
    
    @Test
    public void test2() {
        String plainText = "1-(2+3)";
        CalculateOperation operation = new CalculateOperation();
    
        Symbol result = operation.executeForResult(PostfixParser.parser(plainText));
        
        Assert.assertTrue(result instanceof Number);
        Number number = (Number) result;
        Assert.assertEquals(-4.0, number.toDouble(), Double.MIN_VALUE);
    }
    
}
