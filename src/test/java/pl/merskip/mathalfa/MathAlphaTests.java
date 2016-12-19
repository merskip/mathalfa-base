package pl.merskip.mathalfa;


import org.junit.Assert;
import org.junit.Test;
import pl.merskip.mathalfa.core.elementary.RationalNumber;
import pl.merskip.mathalfa.infixparser.NumberParser;

public class MathAlphaTests {

    @Test
    public void testRationalNumber() {
        RationalNumber number = NumberParser.parse("1/2");
        Assert.assertEquals(1, number.getNumerator());
        Assert.assertEquals(2, number.getDenominator());
        Assert.assertEquals(0.5, number.toDouble(), Double.MIN_VALUE);
        Assert.assertEquals("1/2", number.toPlainText());
    }

}
