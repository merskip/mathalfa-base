package pl.merskip.mathalfa.base.shared;

import pl.merskip.mathalfa.base.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.base.infixparser.PostfixConverter;
import pl.merskip.mathalfa.base.infixparser.PostfixParser;

public class SharedPostfixParser extends PostfixParser {
    
    public SharedPostfixParser() {
        super(new PostfixConverter(
                new FragmentsSplitter(
                        SharedFragmentsRegister.getInstance())));
    }
}
