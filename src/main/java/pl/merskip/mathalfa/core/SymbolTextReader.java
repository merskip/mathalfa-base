package pl.merskip.mathalfa.core;

public interface SymbolTextReader<T extends Symbol> {
    
    boolean fulfill(String buffer, char c);
    
//    T create(Fragment fragment);
    
}
