package pl.merskip.mathalfa.base.elementary;

import pl.merskip.mathalfa.base.core.Operation;
import pl.merskip.mathalfa.base.core.Operator;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.operation.CalculateOperation.Calculable;

public class NumberDivision implements Operator, Calculable {
    
    private Symbol firstArgument;
    private Symbol secondArgument;
    
    public NumberDivision(Symbol firstArgument, Symbol secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    
    public Symbol getFirstArgument() {
        return firstArgument;
    }
    
    public Symbol getSecondArgument() {
        return secondArgument;
    }
    
    @Override
    public Symbol calculate(Operation operation) {
        Symbol calculatedFirstArgument = operation.executeForResult(firstArgument);
        Symbol calculatedSecondArgument = operation.executeForResult(secondArgument);
        
        if (calculatedFirstArgument instanceof RationalNumber
                && calculatedSecondArgument instanceof RationalNumber) {
            RationalNumber firstNumber = (RationalNumber) calculatedFirstArgument;
            RationalNumber secondNumber = (RationalNumber) calculatedSecondArgument;
            
            int numerator = firstNumber.getNumerator() * secondNumber.getDenominator();
            int denominator = firstNumber.getDenominator() * secondNumber.getNumerator();
            return new RationalNumber(numerator, denominator);
        }
        else {
            return new NumberDivision(calculatedFirstArgument, calculatedSecondArgument);
        }
    }
    
    @Override
    public String toPlainText() {
        return String.format("(%s) / (%s)",
                firstArgument.toPlainText(), secondArgument.toPlainText());
    }
}