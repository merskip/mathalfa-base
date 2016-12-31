package pl.merskip.mathalfa.base.elementary;

import pl.merskip.mathalfa.base.core.Number;
import pl.merskip.mathalfa.base.core.Operation;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.operation.CalculateOperation.Calculable;
import pl.merskip.mathalfa.base.operation.SimplifyOperation.Simplifiable;

import java.math.BigInteger;

public class RationalNumber implements Number, Calculable, Simplifiable {

    private BigInteger numerator;
    private BigInteger denominator;

    public RationalNumber(long numerator, long denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }
    
    public RationalNumber(String numerator) {
        this(new BigInteger(numerator), BigInteger.ONE);
    }
    
    public RationalNumber(String numerator, String denominator) {
        this(new BigInteger(numerator), new BigInteger(denominator));
    }
    
    public RationalNumber(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }
    
    @Override
    public Symbol calculate(Operation operation) {
        return this;
    }
    
    @Override
    public Symbol simplify(Operation operation) {
        int sign = numerator.signum() * denominator.signum() >= 0 ? 1 : -1;
        BigInteger gcd = getGCD(numerator.abs(), denominator.abs());
        if (gcd.compareTo(BigInteger.ONE) == 1) {
            BigInteger numerator = this.numerator.abs().divide(gcd);
            BigInteger denominator = this.denominator.abs().divide(gcd);
            if (sign == -1) numerator = numerator.negate();
            
            return new RationalNumber(numerator, denominator);
        }
        else {
            BigInteger numerator = this.numerator.abs();
            BigInteger denominator = this.denominator.abs();
            if (sign == -1) numerator = numerator.negate();
            
            if (numerator.equals(this.numerator)
                    && denominator.equals(this.denominator)) {
                return this;
            }
            else {
                return new RationalNumber(numerator, denominator);
            }
        }
    }
    
    private BigInteger getGCD(BigInteger a, BigInteger b) {
        if (b.compareTo(BigInteger.ZERO) == 0)
            return a;
        return getGCD(b, a.mod(b));
    }
    
    @Override
    public String toPlainText() {
        if (!isInteger())
            return String.format("%d/%d", numerator, denominator);
        else
            return String.valueOf(numerator);
    }
    
    public boolean isInteger() {
        return denominator.compareTo(BigInteger.ONE) == 0;
    }

    public double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }
    
}
