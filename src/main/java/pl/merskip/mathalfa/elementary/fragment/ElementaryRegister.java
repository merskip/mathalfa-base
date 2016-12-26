package pl.merskip.mathalfa.elementary.fragment;

import pl.merskip.mathalfa.core.fragment.FragmentsRegister;

public class ElementaryRegister extends FragmentsRegister {
    
    @Override
    protected void registerSymbols() {
        register(new NumberFragmentReader());
        register(new AdditionFragmentReader());
        register(new SubtractionFragmentReader());
    }
    
}
