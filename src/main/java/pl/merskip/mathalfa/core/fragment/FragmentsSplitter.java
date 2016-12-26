package pl.merskip.mathalfa.core.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FragmentsSplitter {
    
    private static final Character[] DEFAULT_WHITESPACES =
            new Character[] {' ', '\t', '\n'};
    
    private String plainText;
    private List<FragmentReader> readers;
    private List<Character> whitespaces;
    
    private Fragment.Builder fragmentBuilder;
    private List<Fragment> fragments;
    
    public FragmentsSplitter(FragmentsRegister register, String plainText) {
        this(plainText);
        this.readers.addAll(register.getFragmentReaders());
        this.whitespaces.addAll(Arrays.asList(DEFAULT_WHITESPACES));
    }
    
    public FragmentsSplitter(String plainText) {
        this.plainText = plainText;
        this.readers = new ArrayList<>();
        this.whitespaces = new ArrayList<>();
    }
    
    public String getPlainText() {
        return plainText;
    }
    
    public void addReaders(FragmentReader... readers) {
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
