package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Number;

public class RationalNumber implements Number {

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

    public String toPlainText() {
        return String.format("%d/%d", numerator, denominator);
    }

    public double toDouble() {
        return (double) numerator / (double) denominator;
    }

}
