package pl.merskip.mathalfa.elementary;

import org.apache.commons.lang3.NotImplementedException;
import pl.merskip.mathalfa.core.Number;
import pl.merskip.mathalfa.core.Operation;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentsRegister;
import pl.merskip.mathalfa.core.fragment.SymbolReader;

public class ElementaryRegister extends FragmentsRegister {
    
    @Override
    protected void registerSymbols() {
        register(new SymbolReader<Number>() {
            
            @Override
            public Number create(Fragment fragment) {
                throw new NotImplementedException("TODO");
            }
    
            @Override
            public boolean fulfills(String buffer, char c) {
                return buffer.isEmpty()
                        && Character.isDigit(c);
            }
        });
        
        register(new SymbolReader<Operation>() {
            
            @Override
            public Operation create(Fragment fragment) {
                throw  new NotImplementedException("TODO");
            }
    
            @Override
            public boolean fulfills(String buffer, char c) {
                return buffer.isEmpty()
                        && c == '+';
            }
        });
    
        register(new SymbolReader<Operation>() {
        
            @Override
            public Operation create(Fragment fragment) {
                throw  new NotImplementedException("TODO");
            }
        
            @Override
            public boolean fulfills(String buffer, char c) {
                return buffer.isEmpty()
                        && c == '-';
            }
        });
    
    }
    
}
