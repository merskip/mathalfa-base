package pl.merskip.mathalfa.base.elementary.fragment;

import pl.merskip.mathalfa.base.core.Operator;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.SymbolReader;
import pl.merskip.mathalfa.base.elementary.NumberDivision;
import pl.merskip.mathalfa.base.shared.SharedFragmentReader;

import java.util.Stack;

public class DivisionFragmentReader implements SymbolReader<Operator>, SharedFragmentReader {
    
    @Override
    public boolean fulfills(String buffer, char c) {
        return buffer.isEmpty() && c == '/';
    }
    
    @Override
    public int getPrecedence() {
        return 2;
    }
    
    @Override
    public Associative getAssociative() {
        return Associative.Left;
    }
    
    @Override
    public Operator create(Fragment fragment, Stack<Symbol> parameters) {
        Symbol secondParam = parameters.pop();
        Symbol firstParam = parameters.pop();
        return new NumberDivision(firstParam, secondParam);
    }
}
