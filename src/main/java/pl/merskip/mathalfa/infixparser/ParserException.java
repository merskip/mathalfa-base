package pl.merskip.mathalfa.infixparser;


import org.apache.commons.lang3.StringUtils;

public class ParserException extends RuntimeException {
    
    private String plainText;
    private Fragment fragment;
    
    public ParserException(String message, String plainText, Fragment fragment) {
        super(message);
        this.plainText = plainText;
        this.fragment = fragment;
    }
    
    public String getPlainText() {
        return plainText;
    }
    
    public Fragment getFragment() {
        return fragment;
    }
    
    @Override
    public String getMessage() {
        String message = super.getMessage();
        message += "\n" + plainText + "\n";
        message += StringUtils.repeat(' ', fragment.getIndex());
        message += StringUtils.repeat('^', fragment.getText().length());
        if (fragment.getText().isEmpty()) {
            message += "^";
        }
        return message;
    }
    
}
