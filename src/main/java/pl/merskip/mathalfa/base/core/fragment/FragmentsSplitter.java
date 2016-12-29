package pl.merskip.mathalfa.base.core.fragment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FragmentsSplitter {
    
    private List<FragmentReader> readers;
    private List<Character> whitespaces;
    
    private String plainText;
    private Fragment.Builder fragmentBuilder;
    private List<Fragment> fragments;
    
    public FragmentsSplitter() {
        this.readers = new LinkedList<>();
        this.whitespaces = new LinkedList<>();
    }
    
    public FragmentsSplitter(FragmentsRegister register) {
        this();
        this.readers.addAll(register.getFragmentReaders());
        this.whitespaces.addAll(register.getWhitespaces());
    }
    
    public void addReaders(FragmentReader... readers) {
        this.readers.addAll(Arrays.asList(readers));
    }
    
    public void addWhitespaces(Character... whitespaces) {
        this.whitespaces.addAll(Arrays.asList(whitespaces));
    }
    
    public String getLastPlainText() {
        return plainText;
    }
    
    public List<Fragment> getLastFragments() {
        return fragments;
    }
    
    public List<Fragment> split(String plainText) {
        this.plainText = plainText;
        fragments = new LinkedList<>();
        fragmentBuilder = null;
        
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
