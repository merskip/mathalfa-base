package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.SymbolTextReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fragment {
    
    private String text;
    private int index;
    private SymbolTextReader reader;
    
    private Fragment(String text, int index, SymbolTextReader reader) {
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
    
    public SymbolTextReader getReader() {
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
        private List<SymbolTextReader> readers;
    
        Builder(String plainText, int index, List<SymbolTextReader> readers) {
            this.plainText = plainText;
            this.index = index;
            this.buffer = "";
            this.readers = new ArrayList<>(readers);
        }
        
        private boolean isEmpty() {
            return buffer.isEmpty();
        }
    
        boolean append(char c) {
            List<SymbolTextReader> readers = new ArrayList<>(this.readers.size());
            for (SymbolTextReader reader : this.readers) {
                if (reader.fulfill(buffer, c)) {
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
                throw new ParserException("Symbol is ambiguous" +
                        " (" + readers.size() + " readers)", plainText, fragment);
            
            if (readers.isEmpty())
                throw new ParserException("Unknown symbol", plainText, fragment);
            
            fragment.reader = readers.get(0);
            return fragment;
        }
    }
}
