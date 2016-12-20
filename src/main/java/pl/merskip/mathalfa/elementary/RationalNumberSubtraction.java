package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.Symbol;

import java.util.ArrayList;
import java.util.List;

public class RationalNumberSubtraction implements Operation {

    private RationalNumber firstNumber;
    private RationalNumber secondNumber;

    private ArrayList<Symbol> arguments;
    
    public RationalNumberSubtraction() {
    }
    
    public RationalNumberSubtraction(RationalNumber firstNumber, RationalNumber secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;

        this.arguments = new ArrayList<Symbol>(2);
        this.arguments.add(firstNumber);
        this.arguments.add(secondNumber);
    }

    public int argumentsCount() {
        return 2;
    }

    public List<Symbol> arguments() {
        return this.arguments;
    }

    public String toPlainText() {
        return "-";
    }
}
