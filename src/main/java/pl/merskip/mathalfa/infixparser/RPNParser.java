package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.core.elementary.ReversePolishNotationExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RPNParser {
    
    private List<Token> tokens;
    
    public RPNParser(List<Token> tokens) {
        this.tokens = tokens;
    }
    
    public ReversePolishNotationExpression parse() {
        List<Symbol> output = new ArrayList<>(tokens.size());
        Stack<Token> operationsStack = new Stack<>();
        
        for (Token token : tokens) {
            if (isOperator(token)){
    
                if (!operationsStack.empty()) {
                    Token o2 = operationsStack.peek();
                    
                    while (isOperator(o2) &&
                            ((isLeftBiding(token) && comparePrecedence(token, o2) <= 0)
                                    || comparePrecedence(token, o2) < 0)) {
                        
                        operationsStack.pop();
                        output.add(tokenToSymbol(o2));
                        
                        if (operationsStack.empty()) {
                            break;
                        }
    
                        o2 = operationsStack.peek();
                    }
                }
                
                operationsStack.push(token);
            }
            else if (token.text.equals("(")) {
                operationsStack.push(token);
            }
            else if (token.text.equals(")")) {
                Token topOperation = operationsStack.peek();
                
                while (!topOperation.text.equals("(")) {
                    output.add(tokenToSymbol(topOperation));
                    operationsStack.pop();
                    
                    if (operationsStack.empty()) {
                        break;
                    }
                    
                    topOperation = operationsStack.pop();
                }
                
                if (!operationsStack.empty()) {
                    operationsStack.pop();
                }
                
                if (!topOperation.text.equals("(")) {
                    throw  new ParserException("Not found opening parenthesis", token);
                }
            }
            else {
                output.add(tokenToSymbol(token));
            }
        }
        
        while (!operationsStack.empty()) {
            Token token = operationsStack.peek();
            
            if (token.text.equals("(")) {
                throw new ParserException("Unexpected parenthesis", token);
            }
            
            output.add(tokenToSymbol(token));
            operationsStack.pop();
        }
        
        
        return new ReversePolishNotationExpression(output);
    }
    
    private Symbol tokenToSymbol(Token token) {
        System.out.println("token.text = " + token.text);
        return null;
    }
    
    private boolean isOperator(Token token) {
        if (token.text.length() > 1) return false;
        char symbol = token.text.charAt(0);
        return symbol == '+'
                || symbol == '-'
                || symbol == '*'
                || symbol == '/'
                || symbol == '~'
                || symbol == '^';
    }
    
    private boolean isLeftBiding(Token token) {
        return !token.text.equals("^");
    }
    
    private int comparePrecedence(Token token1, Token token2) {
        return precedenceForToken(token1) - precedenceForToken(token2);
    }
    
    private int precedenceForToken(Token token) {
        char symbol = token.text.charAt(0);
        switch (symbol) {
            case '-':
            case '+':
                return 1;
            
            case '*':
            case '/':
                return 2;
                
            case '^':
                return 3;
                
            default:
                throw new ParserException("Unknown token", token);
        }
    }
}
