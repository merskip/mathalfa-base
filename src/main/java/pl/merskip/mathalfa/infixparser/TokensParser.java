package pl.merskip.mathalfa.infixparser;

import java.util.LinkedList;
import java.util.List;

public class TokensParser {
    
    private String plainText;
    
    public TokensParser(String plainText) {
        this.plainText = plainText;
    }
    
    public List<Token> splitToTokens() {
        List<Token> tokens = new LinkedList<>();
        String token = "";
        
        for (int i = 0; i < plainText.length(); i++) {
            char symbol = plainText.charAt(i);
            int index = i - token.length();
            
            if (isOperator(symbol) || isParenthesis(symbol)) {
                if (!token.isEmpty()) {
                    tokens.add(new Token(token, index));
                    
                    token = "";
                }
                
                tokens.add(new Token(symbol, i));
            }
            else if (isWhitespaceChar(symbol)) {
                if (!token.isEmpty()) {
                    tokens.add(new Token(token, index));
                }
                token = "";
            }
            else {
                token += symbol;
            }
        }
        
        if (!token.isEmpty()) {
            tokens.add(new Token(token, plainText.length() - token.length()));
        }
        
        return tokens;
    }
    
    private boolean isOperator(char symbol) {
        return symbol == '+'
                || symbol == '-'
                || symbol == '*'
                || symbol == '/'
                || symbol == '~'
                || symbol == '^';
    }
    
    private boolean isParenthesis(char symbol) {
        return symbol == '(' || symbol == ')';
    }
    
    private boolean isWhitespaceChar(char symbol) {
        return symbol == ' ' || symbol == '\n';
    }
}
