package pl.merskip.mathalfa.base.elementary;

import com.google.common.collect.ImmutableList;
import pl.merskip.mathalfa.base.core.Operation;
import pl.merskip.mathalfa.base.core.Operator;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.operation.CalculateOperation.Calculable;

import java.math.BigInteger;
import java.util.List;

public class NumberAddition implements Operator, Calculable {

    private Symbol firstArgument;
    private Symbol secondArgument;
    
    public NumberAddition(Symbol firstArgument, Symbol secondArgument) {
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

            BigInteger numerator = firstNumber.getNumerator().multiply(secondNumber.getDenominator())
                    .add(firstNumber.getDenominator().multiply(secondNumber.getNumerator()));
            BigInteger denominator = firstNumber.getDenominator()
                    .multiply(secondNumber.getDenominator());
            return new RationalNumber(numerator, denominator);
        }
        else {
            return new NumberAddition(calculatedFirstArgument, calculatedSecondArgument);
        }
    }
    
    @Override
    public List<Symbol> getArguments() {
        return ImmutableList.of(firstArgument, secondArgument);
    }
    
    @Override
    public String toPlainText() {
        return "+";
    }
}
