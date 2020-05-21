package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;

import java.util.List;

public interface Mediator {

    List getResultList(String item, List<OptionsInterface> advanced, List<OptionsInterface> order, List<OptionsInterface> selects);
    String[] getListOfBasesFromDatabase();
    List<AdvancedSearchOption> getListedOptions();

}
