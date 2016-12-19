package pl.merskip.mathalfa.core;

import java.util.List;

public interface Operation extends Symbol {

    int argumentsCount();

    List<Symbol> arguments();

}
