package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.Operator;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.operation.CalculateOperation.Calculable;

public class NumberAddition implements Operator, Calculable {

    private Symbol firstArgument;
    private Symbol secondArgument;
    
    public NumberAddition(Symbol firstArgument, Symbol secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    
    @Override
    public Symbol calculate(Operation operation) {
        Symbol calculatedFirstArgument = operation.executeForResult(firstArgument);
        Symbol calculatedSecondArgument = operation.executeForResult(secondArgument);

        if (calculatedFirstArgument instanceof RationalNumber
                && calculatedSecondArgument instanceof RationalNumber) {
            RationalNumber firstNumber = (RationalNumber) calculatedFirstArgument;
            RationalNumber secondNumber = (RationalNumber) calculatedSecondArgument;

            int numerator = firstNumber.getNumerator() * secondNumber.getDenominator()
                    + firstNumber.getDenominator() * secondNumber.getNumerator();
            int denominator = firstNumber.getDenominator() * secondNumber.getDenominator();
            return new RationalNumber(numerator, denominator);
        }
        else {
            return new NumberAddition(calculatedFirstArgument, calculatedSecondArgument);
        }
    }
    
    @Override
    public String toPlainText() {
        return String.format("(%s) + (%s)",
                firstArgument.toPlainText(), secondArgument.toPlainText());
    }
}
