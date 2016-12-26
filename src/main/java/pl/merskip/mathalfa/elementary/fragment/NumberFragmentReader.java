package pl.merskip.mathalfa.elementary.fragment;

import pl.merskip.mathalfa.core.Number;
import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.SymbolReader;
import pl.merskip.mathalfa.elementary.RationalNumber;

class NumberFragmentReader implements SymbolReader<Number> {
    
    @Override
    public boolean fulfills(String buffer, char c) {
        return buffer.isEmpty() && Character.isDigit(c);
    }
    
    @Override
    public Number create(Fragment fragment) {
        int numerator = Integer.parseInt(fragment.getText());
        return new RationalNumber(numerator);
    }
}
