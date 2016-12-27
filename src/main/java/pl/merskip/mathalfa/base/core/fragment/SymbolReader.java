package pl.merskip.mathalfa.base.core.fragment;

import pl.merskip.mathalfa.base.core.Symbol;

import java.util.Stack;

public interface SymbolReader<T extends Symbol> extends FragmentReader {
    
    T create(Fragment fragment, Stack<Symbol> parameters);
    
}
