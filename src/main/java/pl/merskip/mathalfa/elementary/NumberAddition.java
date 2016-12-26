package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.Symbol;

public class NumberAddition implements Operation {

    private Symbol firstArgument;
    private Symbol secondArgument;
    
    public NumberAddition(Symbol firstArgument, Symbol secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }
    
    @Override
    public String toPlainText() {
        return String.format("(%s) + (%s)",
                firstArgument.toPlainText(), secondArgument.toPlainText());
    }
}
