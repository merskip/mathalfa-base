package pl.merskip.mathalfa.base.infixparser;

import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.FragmentReader;

class ParenthesisFragmentReader implements FragmentReader {
    
    @Override
    public boolean fulfills(String buffer, char c) {
        return buffer.isEmpty()
                && (c == '(' || c == ')');
    }
    
    @Override
    public ParenthesisType getParenthesisType(Fragment fragment) {
        return fragment.getFragmentText().equals("(")
                ? ParenthesisType.OPENING
                : ParenthesisType.CLOSING;
    }
}
