package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Number;
import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.OperationDescriptor;
import pl.merskip.mathalfa.core.SymbolsRegister;

import static pl.merskip.mathalfa.core.OperationDescriptor.Associative.Left;

public class ElementaryRegister extends SymbolsRegister {
    
    @Override
    protected void registerSymbols() {
        
        registerNumberClass(RationalNumber.class);
    
        registerOperation(new OperationDescriptor("+", 1, Left,
                RationalNumberAddition.class));
        registerOperation(new OperationDescriptor("-", 1, Left,
                RationalNumberSubtraction.class));
    }
    
    @Override
    public boolean symbolIsNumber(String symbol) {
        for (char c : symbol.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
    
    @Override
    public Number numberFromSymbol(String symbol) {
        int numerator = Integer.parseInt(symbol);
        return new RationalNumber(numerator, 1);
    }
    
    @Override
    public Operation operationFromSymbol(String symbol, OperationDescriptor operation) {
        try {
            return operation.getOperationClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
