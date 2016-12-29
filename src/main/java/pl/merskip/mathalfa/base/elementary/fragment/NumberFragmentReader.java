package pl.merskip.mathalfa.base.elementary.fragment;

import pl.merskip.mathalfa.base.core.Number;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.SymbolReader;
import pl.merskip.mathalfa.base.elementary.RationalNumber;
import pl.merskip.mathalfa.base.shared.SharedFragmentReader;

import java.util.Stack;

public class NumberFragmentReader implements SymbolReader<Number>, SharedFragmentReader {
    
    @Override
    public boolean fulfills(String buffer, char c) {
        return Character.isDigit(c);
    }
    
    @Override
    public Number create(Fragment fragment, Stack<Symbol> parameters) {
        int numerator = Integer.parseInt(fragment.getFragmentText());
        return new RationalNumber(numerator);
    }
}
