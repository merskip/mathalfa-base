package pl.merskip.mathalfa.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class SymbolsRegister {
    
    private List<SymbolTextReader> symbolReaders;
    
    protected SymbolsRegister() {
        this.symbolReaders = new LinkedList<>();
        
        registerSymbols();
        
    }
    
    protected abstract void registerSymbols();
    
    public Optional<SymbolTextReader> readerFor(String buffer, char c) {
        for (SymbolTextReader reader : symbolReaders) {
            if (reader.fulfill(buffer, c))
                return Optional.of(reader);
        }
        return Optional.empty();
    }
    
}

