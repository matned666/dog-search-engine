package eu.mrndesign.matned.searchEngine.data.mediator;

import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
public class AdvancedSearchOption{

    private SearchType searchType;
    private String fieldName;
    private List<String> optionsList;

    public AdvancedSearchOption(String entityFieldInfo) {
        getAdvancedOptionsList(entityFieldInfo);
    }

    private void getAdvancedOptionsList(String entityFieldInfo){
        optionsList = new LinkedList<>();
        String[] temp = entityFieldInfo.split("::");
        searchType = findSearchType(temp[0]);
        fieldName = temp[1];
        optionsList.addAll(Arrays.asList(temp).subList(2, temp.length));
    }

    private SearchType findSearchType(String s) {
        switch(s){
            case "CHECKBOX": return SearchType.CHECKBOX;
            case "NUMBER": return SearchType.NUMBER;
            case "BOOLEAN": return SearchType.BOOLEAN;
            case "ENUM": return SearchType.ENUM;
            default: return SearchType.VARCHAR;
        }
    }
}
