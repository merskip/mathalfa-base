package pl.merskip.mathalfa.core.fragment;

import pl.merskip.mathalfa.core.Symbol;

public interface SymbolReader<T extends Symbol> extends FragmentReader {
    
    T create(Fragment fragment);
    
}
