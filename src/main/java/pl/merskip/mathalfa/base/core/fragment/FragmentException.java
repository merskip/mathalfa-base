package pl.merskip.mathalfa.base.core.fragment;


import org.apache.commons.lang3.StringUtils;

public class FragmentException extends RuntimeException {
    
    private Fragment fragment;
    
    public FragmentException(String message, Fragment fragment) {
        super(message);
        this.fragment = fragment;
    }
    
    public FragmentException(String message, Fragment fragment, Throwable cause) {
        super(message, cause);
        this.fragment = fragment;
    }
    
    public String getPlainText() {
        return fragment.getPlainText();
    }
    
    public Fragment getFragment() {
        return fragment;
    }
    
    @Override
    public String toString() {
        String message = super.toString();
        message += "\n" + getPlainText() + "\n";
        message += StringUtils.repeat(' ', fragment.getIndex());
        message += StringUtils.repeat('^', fragment.getFragmentText().length());
        if (fragment.getFragmentText().isEmpty()) {
            message += "^";
        }
        return message;
    }
    
}
