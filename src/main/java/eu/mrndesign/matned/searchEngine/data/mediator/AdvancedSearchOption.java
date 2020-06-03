package eu.mrndesign.matned.searchEngine.data.mediator;

import lombok.Data;
import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
public class AdvancedSearchOption implements AdvancedSearchOptionInterface {

    private SearchType searchType;
    private String fieldName;
    private List<String> optionsList;

    AdvancedSearchOption(String entityFieldInfo) {
        getAdvancedOptionsList(entityFieldInfo);
    }

    private void getAdvancedOptionsList(String entityFieldInfo){
        optionsList = new LinkedList<>();
        List<String> temporarySeparatedFieldInfoStringList = Arrays.asList(entityFieldInfo.split(_i_));
        if (temporarySeparatedFieldInfoStringList.size() > 1) {
            searchType = findSearchType(temporarySeparatedFieldInfoStringList.get(0));
            fieldName = temporarySeparatedFieldInfoStringList.get(1);
            optionsList.addAll(temporarySeparatedFieldInfoStringList.subList(2, temporarySeparatedFieldInfoStringList.size()));
        }else {
            fieldName = EMPTY;
            searchType = SearchType.VARCHAR;
        }
    }

    private SearchType findSearchType(String searchOption) {
        switch(searchOption){
            case CHECKBOX_: return SearchType.CHECKBOX;
            case NUMBER_: return SearchType.NUMBER;
            case BOOLEAN_: return SearchType.BOOLEAN;
            case ENUM_: return SearchType.ENUM;
            default: return SearchType.VARCHAR;
        }
    }
}
