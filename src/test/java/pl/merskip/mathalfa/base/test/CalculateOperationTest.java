package pl.merskip.mathalfa.base.test;

import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.base.core.Number;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.infixparser.PostfixParser;
import pl.merskip.mathalfa.base.operation.CalculateOperation;
import pl.merskip.mathalfa.base.shared.SharedPostfixParser;

public class CalculateOperationTest {
    
    @Test
    public void test1() {
        String plainText = "1+2";
        CalculateOperation operation = new CalculateOperation();
        PostfixParser parser = new SharedPostfixParser();
        
        Symbol rootSymbol = parser.parseAndGetRootSymbol(plainText);
        Number number = (Number) operation.executeForResult(rootSymbol);
        
        Assert.assertEquals(3.0, number.toDouble(), Double.MIN_VALUE);
    }
    
    @Test
    public void test2() {
        String plainText = "1-(2+3)";
        CalculateOperation operation = new CalculateOperation();
        PostfixParser parser = new SharedPostfixParser();
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol(plainText);
        Symbol result = operation.executeForResult(rootSymbol);
        
        Assert.assertTrue(result instanceof Number);
        Number number = (Number) result;
        Assert.assertEquals(-4.0, number.toDouble(), Double.MIN_VALUE);
    }
    
    @Test
    public void test3() {
        String plainText = "2*3";
        CalculateOperation operation = new CalculateOperation();
        PostfixParser parser = new SharedPostfixParser();
    
        Symbol rootSymbol = parser.parseAndGetRootSymbol(plainText);
        Symbol result = operation.executeForResult(rootSymbol);
    
        Assert.assertTrue(result instanceof Number);
        Number number = (Number) result;
        Assert.assertEquals(6.0, number.toDouble(), Double.MIN_VALUE);
    }
    
    @Test
    public void test4() {
        String plainText = "6/3";
        CalculateOperation operation = new CalculateOperation();
        PostfixParser parser = new SharedPostfixParser();
        
        Symbol rootSymbol = parser.parseAndGetRootSymbol(plainText);
        Symbol result = operation.executeForResult(rootSymbol);
        
        Assert.assertTrue(result instanceof Number);
        Number number = (Number) result;
        Assert.assertEquals(2.0, number.toDouble(), Double.MIN_VALUE);
    }
}
