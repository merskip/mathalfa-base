package pl.merskip.mathalfa.core.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fragment {
    
    private String text;
    private int index;
    private FragmentReader reader;
    
    private Fragment(String text, int index, FragmentReader reader) {
        this.text = text;
        this.index = index;
        this.reader = reader;
    }
    
    public String getText() {
        return text;
    }
    
    public int getIndex() {
        return index;
    }
    
    public FragmentReader getReader() {
        return reader;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Fragment)) return false;
        Fragment otherFragment = (Fragment) obj;
        return index == otherFragment.index && text.equals(otherFragment.text);
    }
    
    static class Builder {
        
        private String plainText;
        private int index;
        private String buffer;
        private List<FragmentReader> readers;
    
        Builder(String plainText, int index, List<FragmentReader> readers) {
            this.plainText = plainText;
            this.index = index;
            this.buffer = "";
            this.readers = new ArrayList<>(readers);
        }
        
        private boolean isEmpty() {
            return buffer.isEmpty();
        }
    
        boolean append(char c) {
            List<FragmentReader> readers = new ArrayList<>(this.readers.size());
            for (FragmentReader reader : this.readers) {
                if (reader.fulfills(buffer, c)) {
                    readers.add(reader);
                }
            }
            
            if (!readers.isEmpty()) {
                buffer += c;
                this.readers = readers;
                return true;
            }
            else {
                if (buffer.isEmpty()) {
                    buffer += c;
                    this.readers = Collections.emptyList();
                }
                
                return false;
            }
        }
        
        Fragment build() {
            Fragment fragment = new Fragment(buffer, index, null);
            
            if (readers.size() > 1)
                throw new FragmentException("Fragment is ambiguous" +
                        " (" + readers.size() + " readers)", plainText, fragment);
            
            if (readers.isEmpty())
                throw new FragmentException("Unknown fragment", plainText, fragment);
            
            fragment.reader = readers.get(0);
            return fragment;
        }
    }
}
