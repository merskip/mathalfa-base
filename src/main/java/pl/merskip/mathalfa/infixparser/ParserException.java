package pl.merskip.mathalfa.infixparser;

public class ParserException extends RuntimeException {
    
    private Token token;
    
    public ParserException(String message, Token token) {
        super(message);
        this.token = token;
    }
    
    public Token getToken() {
        return token;
    }
}
