package pl.merskip.mathalfa.elementary.fragment;

import pl.merskip.mathalfa.core.Operator;
import pl.merskip.mathalfa.core.Symbol;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.SymbolReader;
import pl.merskip.mathalfa.elementary.NumberSubtraction;

import java.util.Stack;

class SubtractionFragmentReader implements SymbolReader<Operator> {
    
    @Override
    public boolean fulfills(String buffer, char c) {
        return buffer.isEmpty() && c == '-';
    }
    
    @Override
    public int getPrecedence() {
        return 1;
    }
    
    @Override
    public Associative getAssociative() {
        return Associative.Left;
    }
    
    @Override
    public Operator create(Fragment fragment, Stack<Symbol> parameters) {
        Symbol secondParam = parameters.pop();
        Symbol firstParam = parameters.pop();
        return new NumberSubtraction(firstParam, secondParam);
    }
}
