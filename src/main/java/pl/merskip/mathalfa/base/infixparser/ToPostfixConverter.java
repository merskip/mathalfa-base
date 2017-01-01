package pl.merskip.mathalfa.base.infixparser;

import pl.merskip.mathalfa.base.core.Operator;
import pl.merskip.mathalfa.base.core.Symbol;

import java.util.ArrayList;
import java.util.List;

public class ToPostfixConverter {
    
    public static List<String> toPostfix(Symbol rootSymbol) {
        List<String> rpn = new ArrayList<>();
        
        if (rootSymbol instanceof Operator) {
            Operator operator = (Operator) rootSymbol;
            operator.getArguments().forEach(symbol -> rpn.addAll(toPostfix(symbol)));
        }
        rpn.add(rootSymbol.toPlainText());
        
        return rpn;
    }
}
