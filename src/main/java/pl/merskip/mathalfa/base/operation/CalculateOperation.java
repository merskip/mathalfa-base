package pl.merskip.mathalfa.base.operation;

import org.apache.commons.lang3.NotImplementedException;
import pl.merskip.mathalfa.base.core.Operation;
import pl.merskip.mathalfa.base.core.Symbol;

public class CalculateOperation implements Operation {
    
    public interface Calculable {
        
        Symbol calculate(Operation operation);
    }
    
    @Override
    public Symbol executeForResult(Symbol symbol) {
    
        if (symbol instanceof Calculable) {
            Calculable calculableRootSymbol = (Calculable) symbol;
            return calculableRootSymbol.calculate(this);
        }
        else if (symbol == null) {
            return null;
        }
        else {
            throw new NotImplementedException(symbol.getClass()
                    + " does not implemented " + Calculable.class);
        }
    }
    
}
