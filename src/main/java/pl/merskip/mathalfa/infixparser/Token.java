package pl.merskip.mathalfa.infixparser;

public class Token {
    
    public String text;
    public int index;
    
    public Token(char symbol, int index) {
        this(String.valueOf(symbol), index);
    }
    
    Token(String text, int index) {
        this.text = text;
        this.index = index;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Token)) return false;
        Token otherToken = (Token) obj;
        return index == otherToken.index && text.equals(otherToken.text);
    }
}
