package pl.merskip.mathalfa.base.elementary.fragment;

import pl.merskip.mathalfa.base.core.Operator;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.SymbolReader;
import pl.merskip.mathalfa.base.elementary.NumberExponentiation;
import pl.merskip.mathalfa.base.shared.SharedFragmentReader;

import java.util.Stack;

public class ExponentationFragmentReader implements SymbolReader<Operator>, SharedFragmentReader {
    
    @Override
    public boolean fulfills(String buffer, char c) {
        return buffer.isEmpty() && c == '^';
    }
    
    @Override
    public int getPrecedence() {
        return 3;
    }
    
    @Override
    public Associative getAssociative() {
        return Associative.Right;
    }
    
    @Override
    public Operator create(Fragment fragment, Stack<Symbol> parameters) {
        Symbol power = parameters.pop();
        Symbol base = parameters.pop();
        return new NumberExponentiation(base, power);
    }
}
