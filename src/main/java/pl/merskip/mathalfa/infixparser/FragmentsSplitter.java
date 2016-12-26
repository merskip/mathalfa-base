package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.SymbolTextReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FragmentsSplitter {
    
    private String plainText;
    private List<SymbolTextReader> readers;
    private List<Character> whitespaces;
    
    private Fragment.Builder fragmentBuilder;
    private List<Fragment> fragments;
    
    public FragmentsSplitter(String plainText) {
        this.plainText = plainText;
        this.readers = new ArrayList<>();
        this.whitespaces = new ArrayList<>();
    }
    
    public void addReaders(SymbolTextReader... readers) {
        this.readers.addAll(Arrays.asList(readers));
    }
    
    public void addWhitespaces(Character... whitespaces) {
        this.whitespaces.addAll(Arrays.asList(whitespaces));
    }
    
    public List<Fragment> split() {
        fragments = new LinkedList<>();
        
        
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
    
            if (isWhitespace(c)) {
                endFragment();
                continue;
            }
    
            if (fragmentBuilder == null) {
                startFragment(i);
            }
    
            if (!fragmentBuilder.append(c)) {
                endFragment();
                i--; // Przy następnym obrocie pętli, ten sam znak
            }
        }
        endFragment();
        
        return fragments;
    }
    
    private void startFragment(int index) {
        assert fragmentBuilder == null;
        fragmentBuilder = new Fragment.Builder(plainText, index, readers);
    }
    
    private void endFragment() {
        if (fragmentBuilder != null) {
            fragments.add(fragmentBuilder.build());
        }
        fragmentBuilder = null;
    }
    
    private boolean isWhitespace(char c) {
        return whitespaces.contains(c);
    }
}
