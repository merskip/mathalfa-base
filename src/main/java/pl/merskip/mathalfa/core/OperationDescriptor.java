package pl.merskip.mathalfa.core;

public class OperationDescriptor {
    
    public enum Associative {
        Left,
        Right
    }
    
    private String symbol;
    private int precedence;
    private Associative associative;
    private Class<? extends Operation> operationClass;
    
    public OperationDescriptor(String symbol, int precedence, Associative associative,
                               Class<? extends Operation> operationClass) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.associative = associative;
        this.operationClass = operationClass;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public int getPrecedence() {
        return precedence;
    }
    
    public Associative getAssociative() {
        return associative;
    }
    
    public Class<? extends Operation> getOperationClass() {
        return operationClass;
    }
}
