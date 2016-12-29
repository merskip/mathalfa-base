package pl.merskip.mathalfa.base.infixparser;

import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.FragmentException;
import pl.merskip.mathalfa.base.core.fragment.SymbolReader;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class PostfixParser {
    
    private PostfixConverter postfixConverter;
    private Symbol rootSymbol;
    
    public PostfixParser(PostfixConverter postfixConverter) {
        this.postfixConverter = postfixConverter;
    }
    
    public Symbol getLastRootSymbol() {
        return rootSymbol;
    }
    
    public Symbol parseAndGetRootSymbol(String plainText) {
        List<Fragment> fragments = postfixConverter.convert(plainText);
    
        Stack<Symbol> parameters = new Stack<>();
        
        for (Fragment fragment : fragments) {
        
            SymbolReader reader = (SymbolReader) fragment.getReader();
            try {
                parameters.push(reader.create(fragment, parameters));
            } catch (EmptyStackException e) {
                throw new FragmentException("Too few arguments", fragment, e);
            }
        }
        
        rootSymbol = parameters.empty() ? null : parameters.pop();
        return rootSymbol;
    }
    
}
