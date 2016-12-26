package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.Symbol;

public class NumberSubtraction implements Operation {
    
    private Symbol firstArgument;
    private Symbol secondArgument;
    
    public NumberSubtraction(Symbol firstArgument, Symbol secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    
    
    public String toPlainText() {
        return firstArgument.toPlainText() + " + " + secondArgument.toPlainText();
    }
}
