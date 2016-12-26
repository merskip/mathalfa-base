package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentReader;

class ParenthesisFragmentReader implements FragmentReader {
    
    @Override
    public boolean fulfills(String buffer, char c) {
        return buffer.isEmpty()
                && (c == '(' || c == ')');
    }
    
    @Override
    public ParenthesisType getParenthesisType(Fragment fragment) {
        return fragment.getText().equals("(")
                ? ParenthesisType.OPENING
                : ParenthesisType.CLOSING;
    }
}
