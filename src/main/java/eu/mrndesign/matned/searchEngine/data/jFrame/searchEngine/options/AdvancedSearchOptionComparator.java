package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.mediator.AdvancedSearchOption;
import eu.mrndesign.matned.searchEngine.data.mediator.AdvancedSearchOptionInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;

import java.util.Comparator;

public class AdvancedSearchOptionComparator implements Comparator<AdvancedSearchOptionInterface> {

    @Override
    public int compare(AdvancedSearchOptionInterface o1, AdvancedSearchOptionInterface o2) {
        if (o1.getSearchType() == SearchType.VARCHAR && (o2.getSearchType() == SearchType.NUMBER || o2.getSearchType() == SearchType.CHECKBOX || o2.getSearchType() == SearchType.BOOLEAN || o2.getSearchType() == SearchType.ENUM))
            return -1;
        else if (o1.getSearchType() == SearchType.ENUM && (o2.getSearchType() == SearchType.NUMBER || o2.getSearchType() == SearchType.CHECKBOX || o2.getSearchType() == SearchType.BOOLEAN))
            return -1;
        else if (o1.getSearchType() == SearchType.NUMBER && (o2.getSearchType() == SearchType.CHECKBOX || o2.getSearchType() == SearchType.BOOLEAN))
        return -1;
        else if (o1.getSearchType() == SearchType.CHECKBOX && o2.getSearchType() == SearchType.BOOLEAN)
            return -1;
         else if (o1.getSearchType() == SearchType.ENUM && o2.getSearchType() == SearchType.VARCHAR)
            return 1;
        else if (o1.getSearchType() == SearchType.NUMBER && (o2.getSearchType() == SearchType.VARCHAR|| o2.getSearchType() == SearchType.ENUM))
        return 1;
        else if(o1.getSearchType() == SearchType.CHECKBOX && (o2.getSearchType() == SearchType.NUMBER || o2.getSearchType() == SearchType.VARCHAR || o2.getSearchType() == SearchType.ENUM))
            return 1;
        else if (o1.getSearchType() == SearchType.BOOLEAN && (o2.getSearchType() == SearchType.NUMBER || o2.getSearchType() == SearchType.CHECKBOX || o2.getSearchType() == SearchType.VARCHAR || o2.getSearchType() == SearchType.ENUM))
            return 1;
        else if (o1.getSearchType() == SearchType.VARCHAR && o2.getSearchType() == SearchType.VARCHAR)
            return 0;
        else if (o1.getSearchType() == SearchType.ENUM && o2.getSearchType() == SearchType.ENUM)
            return 0;
        else if (o1.getSearchType() == SearchType.NUMBER && o2.getSearchType() == SearchType.NUMBER)
            return 0;
        else if (o1.getSearchType() == SearchType.CHECKBOX && o2.getSearchType() == SearchType.CHECKBOX)
            return 0;
        else if (o1.getSearchType() == SearchType.BOOLEAN && o2.getSearchType() == SearchType.BOOLEAN)
            return 0;
        else return 0;

    }
}
