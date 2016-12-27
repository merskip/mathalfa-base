package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Number;
import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.operation.CalculateOperation.Calculable;

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

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }
    
    @Override
    public Symbol calculate(Operation operation) {
        return this;
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
