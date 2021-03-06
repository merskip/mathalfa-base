package pl.merskip.mathalfa.base.elementary;

import com.google.common.collect.ImmutableList;
import pl.merskip.mathalfa.base.core.Operation;
import pl.merskip.mathalfa.base.core.Operator;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.operation.CalculateOperation.Calculable;

import java.math.BigInteger;
import java.util.List;

public class NumberExponentiation implements Operator, Calculable {
    
    private Symbol base;
    private Symbol power;
    
    public NumberExponentiation(Symbol base, Symbol power) {
        this.base = base;
        this.power = power;
    }
    
    public Symbol getBase() {
        return base;
    }
    
    public Symbol getPower() {
        return power;
    }
    
    @Override
    public Symbol calculate(Operation operation) {
        Symbol calculatedBase = operation.executeForResult(base);
        Symbol calculatedPower = operation.executeForResult(power);
    
        if (calculatedBase instanceof RationalNumber
                && calculatedPower instanceof RationalNumber) {
            
            RationalNumber base = (RationalNumber) calculatedBase;
            RationalNumber power = (RationalNumber) calculatedPower;
            
            if (power.isInteger()) {
                if (power.getNumerator().signum() == 0) {
                    return new RationalNumber(BigInteger.ONE, BigInteger.ONE);
                }
                else if (power.getNumerator().signum() == -1) {
                    base = base.inverted();
                }
                
                BigInteger numerator = pow(base.getNumerator(), power.getNumerator());
                BigInteger denominator = pow(base.getDenominator(), power.getNumerator());
                
                return new RationalNumber(numerator, denominator);
            }
            else if (power.getNumerator().compareTo(BigInteger.ONE) != 0
                    && power.getDenominator().compareTo(BigInteger.ZERO) == 1) {
                BigInteger numerator = pow(base.getNumerator(), power.getNumerator());
                BigInteger denominator = pow(base.getDenominator(), power.getNumerator());
                
                RationalNumber newBase = new RationalNumber(numerator, denominator);
                RationalNumber newPower = new RationalNumber(BigInteger.ONE, power.getDenominator());
                
                return new NumberExponentiation(newBase, newPower).calculate(operation);
            }
        }
        
        return new NumberExponentiation(calculatedBase, calculatedPower);
    }
    
    BigInteger pow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }
    
    
    @Override
    public List<Symbol> getArguments() {
        return ImmutableList.of(base, power);
    }
    
    @Override
    public String toPlainText() {
        return "^";
    }
    
}
