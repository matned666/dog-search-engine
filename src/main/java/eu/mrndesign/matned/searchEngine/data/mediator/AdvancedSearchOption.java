package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.AdvancedSearchInterpreterInterface;
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

    public AdvancedSearchOption(String entityFieldInfo) {
        getAdvancedOptionsList(entityFieldInfo);
    }

    private void getAdvancedOptionsList(String entityFieldInfo){
        optionsList = new LinkedList<>();
        String[] temp = entityFieldInfo.split(_i_);
        searchType = findSearchType(temp[0]);
        fieldName = temp[1];
        optionsList.addAll(Arrays.asList(temp).subList(2, temp.length));
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
