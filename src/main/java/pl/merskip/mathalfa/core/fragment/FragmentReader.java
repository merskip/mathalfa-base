package pl.merskip.mathalfa.core.fragment;

import org.apache.commons.lang3.NotImplementedException;

public interface FragmentReader {
    
    enum ParenthesisType {
        OPENING,
        CLOSING
    }
    
    enum Associative {
        Left,
        Right
    }
    
    boolean fulfills(String buffer, char c);
    
    default int getPrecedence() {
        return 0;
    }
    
    default Associative getAssociative() {
        if (getPrecedence() != 0)
            throw new NotImplementedException("If class implements isOperation" +
                    " and not returns zero, you must also implement getAssociative");
        return null;
    }
    
    default ParenthesisType getParenthesisType(Fragment fragment) {
        return null;
    }
}
