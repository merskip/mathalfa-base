package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.OperationDescriptor;
import pl.merskip.mathalfa.core.OperationDescriptor.Associative;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.core.SymbolsRegister;
import pl.merskip.mathalfa.elementary.ReversePolishNotationExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;


public class RPNParser {
    
    private List<Token> tokens;
    private SymbolsRegister symbolsRegister;
    
    
    public RPNParser(List<Token> tokens, SymbolsRegister symbolsRegister) {
        this.tokens = tokens;
        this.symbolsRegister = symbolsRegister;
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
                    
                    topOperation = operationsStack.peek();
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
        
        System.out.println(token.text);
        
        if (symbolsRegister.symbolIsNumber(token.text)) {
            return symbolsRegister.numberFromSymbol(token.text);
        }
        else if (isOperator(token)) {
            return symbolsRegister.operationFromSymbol(token.text, operationForToken(token));
        }
        else {
            throw new ParserException("Unknown symbol: " + token.text, token);
        }
    }
    
    private boolean isOperator(Token token) {
        if (token.text.length() > 1) return false;
        String symbol = token.text;
        return symbolsRegister.operationForSymbol(symbol).isPresent();
    }
    
    private boolean isLeftBiding(Token token) {
        return operationForToken(token).getAssociative() == Associative.Left;
    }
    
    private int comparePrecedence(Token token1, Token token2) {
        return precedenceForToken(token1) - precedenceForToken(token2);
    }
    
    private int precedenceForToken(Token token) {
        return operationForToken(token).getPrecedence();
    }
    
    private OperationDescriptor operationForToken(Token token) {
        Optional<OperationDescriptor> operation = symbolsRegister.operationForSymbol(token.text);
        if (!operation.isPresent())
            throw new ParserException("Unknown symbol: " + token.text, token);
    
        return operation.get();
    }
}
