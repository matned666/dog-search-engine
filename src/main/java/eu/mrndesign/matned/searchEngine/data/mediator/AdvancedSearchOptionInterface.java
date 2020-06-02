package eu.mrndesign.matned.searchEngine.data.mediator;

import java.util.List;

public interface AdvancedSearchOptionInterface {

    SearchType getSearchType();
    String getFieldName();
    List<String> getOptionsList();

}
