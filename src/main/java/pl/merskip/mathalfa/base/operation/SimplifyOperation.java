package pl.merskip.mathalfa.base.operation;

import pl.merskip.mathalfa.base.core.Operation;
import pl.merskip.mathalfa.base.core.Symbol;

public class SimplifyOperation implements Operation {
    
    public interface Simplifiable {
        
        Symbol simplify(Operation operation);
    }
    
    @Override
    public Symbol executeForResult(Symbol symbol) {
        if (symbol instanceof Simplifiable) {
            Simplifiable simplifiableSymbol = (Simplifiable) symbol;
            return simplifiableSymbol.simplify(this);
        }
        else {
            return symbol;
        }
    }
}
