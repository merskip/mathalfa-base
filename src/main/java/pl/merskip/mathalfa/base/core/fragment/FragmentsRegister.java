package pl.merskip.mathalfa.base.core.fragment;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class FragmentsRegister {
    
    private List<FragmentReader> fragmentReaders;
    private List<Character> whitespaces;
    
    protected FragmentsRegister() {
        this.fragmentReaders = new LinkedList<>();
        this.whitespaces = new LinkedList<>();
        
        registerSymbols();
    }
    
    protected abstract void registerSymbols();
    
    protected void register(FragmentReader reader) {
        fragmentReaders.add(reader);
    }
    
    protected void addWhitespaces(Character... whitespace) {
        Collections.addAll(whitespaces, whitespace);
    }
    
    List<FragmentReader> getFragmentReaders() {
        return fragmentReaders;
    }
    
    List<Character> getWhitespaces() {
        return whitespaces;
    }
}

