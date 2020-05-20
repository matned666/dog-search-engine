package eu.mrndesign.matned.searchEngine.data.interpreter;

import java.util.List;

public interface Interpreter {

    List getResultList(String item);
    String[] getListOfBasesFromDatabase();
    List<AdvancedSearchOption> getListedOptions();

}
