package pl.merskip.mathalfa.base.core;

import java.util.Collections;
import java.util.List;

public interface Operator extends Symbol {

    default List<Symbol> getArguments() {
        return Collections.emptyList();
    }
    
}
