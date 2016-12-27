package pl.merskip.mathalfa.shared;

import org.reflections.Reflections;
import pl.merskip.mathalfa.core.fragment.FragmentReader;
import pl.merskip.mathalfa.core.fragment.FragmentsRegister;

public class SharedFragmentsRegister extends FragmentsRegister {
    
    private static SharedFragmentsRegister instance = new SharedFragmentsRegister();
    
    public static SharedFragmentsRegister getInstance() {
        return instance;
    }
    
    private SharedFragmentsRegister() {
        
    }
    
    @Override
    protected void registerSymbols() {
        new Reflections("")
                .getSubTypesOf(SharedFragmentReader.class)
                .forEach(this::registerClass);
    }
    
    private void registerClass(Class<? extends FragmentReader> readerClass) {
        try {
            register(readerClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
