package pl.merskip.mathalfa.base.elementary;

import pl.merskip.mathalfa.base.core.Number;
import pl.merskip.mathalfa.base.core.Operation;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.operation.CalculateOperation.Calculable;

import static java.lang.Math.abs;

public class RationalNumber implements Number, Calculable {

    private int numerator;
    private int denominator;

    public RationalNumber(int numerator) {
        this(numerator, 1);
    }
    
    public RationalNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
    
    @Override
    public Symbol calculate(Operation operation) {
        return this;
    }
    
    public RationalNumber simplify() {
        int sign = numerator * denominator >= 0 ? 1 : -1;
        int gcd = getGCD(abs(numerator), abs(denominator));
        if (gcd > 1) {
            return new RationalNumber(abs(numerator) / gcd * sign, abs(denominator) / gcd);
        }
        else {
            int numerator = abs(this.numerator) * sign;
            int denominator = abs(this.denominator);
            if (numerator == this.numerator && denominator == this.denominator) {
                return this;
            }
            else {
                return new RationalNumber(numerator, denominator);
            }
        }
    }
    
    private int getGCD(int a, int b) {
        if (b == 0)
            return a;
        return getGCD(b, a % b);
    }
    
    public String toPlainText() {
        if (denominator != 1)
            return String.format("%d/%d", numerator, denominator);
        else
            return String.valueOf(numerator);
    }

    public double toDouble() {
        return (double) numerator / (double) denominator;
    }
    
}
