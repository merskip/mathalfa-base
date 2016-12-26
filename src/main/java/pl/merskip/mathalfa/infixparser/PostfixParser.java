package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.SymbolReader;

import java.util.List;
import java.util.Stack;

public class PostfixParser {
    
    private String plainText;
    private List<Fragment> fragments;
    
    public PostfixParser(PostfixConverter postfixConverter) {
        this.fragments = postfixConverter.convert();
        this.plainText = postfixConverter.getPlainText();
    }
    
    public Symbol parse() {
        Stack<Symbol> parameters = new Stack<>();
        
        for (Fragment fragment : fragments) {
        
            SymbolReader reader = (SymbolReader) fragment.getReader();
            parameters.push(reader.create(fragment, parameters));
        }
        
        assert parameters.size() == 1;
        return parameters.pop();
    }
    
}
