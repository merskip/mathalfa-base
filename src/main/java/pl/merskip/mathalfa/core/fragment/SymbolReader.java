package pl.merskip.mathalfa.core.fragment;

import pl.merskip.mathalfa.core.Symbol;

import java.util.Stack;

public interface SymbolReader<T extends Symbol> extends FragmentReader {
    
    T create(Fragment fragment, Stack<Symbol> parameters);
    
}
