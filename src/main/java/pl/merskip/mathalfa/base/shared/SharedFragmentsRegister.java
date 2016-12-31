package pl.merskip.mathalfa.base.shared;

import org.reflections.Reflections;
import pl.merskip.mathalfa.base.core.fragment.FragmentReader;
import pl.merskip.mathalfa.base.core.fragment.FragmentsRegister;

public class SharedFragmentsRegister extends FragmentsRegister {
    
    private static SharedFragmentsRegister instance = new SharedFragmentsRegister();
    
    public static SharedFragmentsRegister getInstance() {
        return instance;
    }
    
    private SharedFragmentsRegister() {
        
    }
    
    @Override
    protected void registerSymbols() {
        new Reflections("pl.merskip.mathalfa")
                .getSubTypesOf(SharedFragmentReader.class)
                .forEach(this::registerClass);
        
        addWhitespaces(' ', '\t', '\n');
    }
    
    private void registerClass(Class<? extends FragmentReader> readerClass) {
        try {
            register(readerClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
