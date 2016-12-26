package pl.merskip.mathalfa.core.fragment;

import java.util.LinkedList;
import java.util.List;

public abstract class FragmentsRegister {
    
    private List<FragmentReader> fragmentReaders;
    
    protected FragmentsRegister() {
        this.fragmentReaders = new LinkedList<>();
        
        registerSymbols();
    }
    
    protected abstract void registerSymbols();
    
    protected void register(FragmentReader reader) {
        fragmentReaders.add(reader);
    }
    
    public List<FragmentReader> getFragmentReaders() {
        return fragmentReaders;
    }
}

