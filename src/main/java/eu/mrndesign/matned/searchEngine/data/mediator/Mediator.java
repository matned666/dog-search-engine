package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.Select;

import java.util.List;

public interface Mediator {

    List getResultList(String item, List<OptionsInterface> list);
    String[] getListOfBasesFromDatabase();
    List<AdvancedSearchOption> getListedOptions();

}
