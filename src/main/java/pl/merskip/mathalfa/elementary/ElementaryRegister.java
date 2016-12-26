package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Number;
import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentsRegister;
import pl.merskip.mathalfa.core.fragment.SymbolReader;

public class ElementaryRegister extends FragmentsRegister {
    
    @Override
    protected void registerSymbols() {
        register(new RationalNumberReader());
        register(new AdditionReader());
        register(new SubtractionReader());
    }
    
    private class RationalNumberReader implements SymbolReader<Number> {
    
        @Override
        public boolean fulfills(String buffer, char c) {
            return buffer.isEmpty()
                    && Character.isDigit(c);
        }
    
        @Override
        public Number create(Fragment fragment) {
            int numerator = Integer.parseInt(fragment.getText());
            return new RationalNumber(numerator);
        }
    }
    
    private class AdditionReader implements SymbolReader<Operation> {
    
        @Override
        public boolean fulfills(String buffer, char c) {
            return buffer.isEmpty()
                    && c == '+';
        }
    
        @Override
        public Operation create(Fragment fragment) {
            return new RationalNumberAddition();
        }
    }
    
    private class SubtractionReader implements SymbolReader<Operation> {
        
        @Override
        public boolean fulfills(String buffer, char c) {
            return buffer.isEmpty()
                    && c == '-';
        }
    
        @Override
        public Operation create(Fragment fragment) {
            return new RationalNumberSubtraction();
        }
    }
    
}
