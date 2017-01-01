package pl.merskip.mathalfa.base.elementary.fragment;

import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.SymbolReader;
import pl.merskip.mathalfa.base.elementary.NumberDivision;
import pl.merskip.mathalfa.base.elementary.RationalNumber;
import pl.merskip.mathalfa.base.shared.SharedFragmentReader;

import java.util.Stack;

public class DivisionFragmentReader implements SymbolReader<Symbol>, SharedFragmentReader {
    
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
    public Symbol create(Fragment fragment, Stack<Symbol> parameters) {
        Symbol secondParam = parameters.pop();
        Symbol firstParam = parameters.pop();
        
        if (secondParam instanceof RationalNumber
                && firstParam instanceof RationalNumber) {
            RationalNumber secondNumber = (RationalNumber) secondParam;
            RationalNumber firstNumber = (RationalNumber) firstParam;

            if (secondNumber.isInteger() && firstNumber.isInteger()) {
                return new RationalNumber(firstNumber.getNumerator(),
                        secondNumber.getNumerator());
            }
        }
        return new NumberDivision(firstParam, secondParam);
    }
}
