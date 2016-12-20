package pl.merskip.mathalfa.elementary;

import pl.merskip.mathalfa.core.Expression;
import pl.merskip.mathalfa.core.Symbol;

import java.util.List;

public class ReversePolishNotationExpression implements Expression {

    private List<Symbol> symbols;

    public ReversePolishNotationExpression(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public List<Symbol> symbolsList() {
        return symbols;
    }
}
