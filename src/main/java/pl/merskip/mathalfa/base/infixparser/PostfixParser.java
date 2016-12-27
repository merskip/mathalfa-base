package pl.merskip.mathalfa.base.infixparser;

import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.FragmentException;
import pl.merskip.mathalfa.base.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.base.core.fragment.SymbolReader;
import pl.merskip.mathalfa.base.shared.SharedFragmentsRegister;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class PostfixParser {
    
    private String plainText;
    private List<Fragment> fragments;
    
    public PostfixParser(PostfixConverter postfixConverter) {
        this.fragments = postfixConverter.convert();
        this.plainText = postfixConverter.getPlainText();
    }
    
    static public Symbol parser(String plainText) {
        return new PostfixParser(
                new PostfixConverter(
                        new FragmentsSplitter(
                                SharedFragmentsRegister.getInstance(), plainText)))
                .parseAndGetRootSymbol();
    }
    
    public Symbol parseAndGetRootSymbol() {
        Stack<Symbol> parameters = new Stack<>();
        
        for (Fragment fragment : fragments) {
        
            SymbolReader reader = (SymbolReader) fragment.getReader();
            try {
                parameters.push(reader.create(fragment, parameters));
            } catch (EmptyStackException e) {
                throw new FragmentException("Too few arguments", plainText, fragment, e);
            }
        }
        
        return parameters.empty() ? null : parameters.pop();
    }
    
}