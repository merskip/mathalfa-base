package pl.merskip.mathalfa.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class SymbolsRegister {
    
    private Class<? extends Number> numberClass;
    private List<OperationDescriptor> operationClasses;
    
    protected SymbolsRegister() {
        this.operationClasses = new LinkedList<>();
        
        registerSymbols();
    }
    
    protected abstract void registerSymbols();
    
    public abstract boolean symbolIsNumber(String symbol);
    
    public abstract Number numberFromSymbol(String symbol);
    
    public abstract Operation operationFromSymbol(String symbol, OperationDescriptor operation);
    
    protected void registerNumberClass(Class<? extends Number> numberClass) {
        this.numberClass = numberClass;
    }
    
    protected void registerOperation(OperationDescriptor operationDescriptor) {
        operationClasses.add(operationDescriptor);
    }
    
    public Class<? extends Number> classForNumber() {
        return numberClass;
    }
    
    public Optional<OperationDescriptor> operationForSymbol(String symbol) {
        for (OperationDescriptor operation : operationClasses) {
            if (operation.getSymbol().equals(symbol)) {
                return Optional.of(operation);
            }
        }
        return Optional.empty();
    }
    
}

