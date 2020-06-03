package eu.mrndesign.matned.searchEngine.data.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedSearchOptionTest {

    private AdvancedSearchOption advancedSearchOption;
    private StringBuilder sb;

    @BeforeEach
    void setUp(){
        sb = new StringBuilder();
    }

    @Test
    void enumCheck(){
        sb.append("ENUM::dogRace::SHEPPARD::TERRIER");
        advancedSearchOption = new AdvancedSearchOption(sb.toString());
        assertEquals(advancedSearchOption.getFieldName(), "dogRace");
        assertEquals(advancedSearchOption.getSearchType(), SearchType.ENUM);
        assertEquals(advancedSearchOption.getOptionsList().get(0),"SHEPPARD");
        assertEquals(advancedSearchOption.getOptionsList().get(1),"TERRIER");
    }

    @Test
    void checkboxCheck(){
        sb.append("CHECKBOX::gender::Male::Female");
        advancedSearchOption = new AdvancedSearchOption(sb.toString());
        assertEquals(advancedSearchOption.getFieldName(), "gender");
        assertEquals(advancedSearchOption.getSearchType(), SearchType.CHECKBOX);
        assertEquals(advancedSearchOption.getOptionsList().get(0),"Male");
        assertEquals(advancedSearchOption.getOptionsList().get(1),"Female");
    }

    @Test
    void booleanCheck(){
        sb.append("BOOLEAN::isPotato::");
        advancedSearchOption = new AdvancedSearchOption(sb.toString());
        assertEquals(advancedSearchOption.getFieldName(), "isPotato");
        assertEquals(advancedSearchOption.getSearchType(), SearchType.BOOLEAN);
        assertEquals(0, advancedSearchOption.getOptionsList().size());

    }

    @Test
    void onWrongInputFormat(){
        sb.append("asjdhfglhjxchgjge:ooopp");
        advancedSearchOption = new AdvancedSearchOption(sb.toString());
        assertEquals(advancedSearchOption.getFieldName(), "");
        assertEquals(advancedSearchOption.getSearchType(), SearchType.VARCHAR);
        assertEquals(0, advancedSearchOption.getOptionsList().size());
    }

    @Test
    void varcharCheck(){
        sb.append("VARCHAR::ownerName::");
        advancedSearchOption = new AdvancedSearchOption(sb.toString());
        assertEquals(advancedSearchOption.getFieldName(), "ownerName");
        assertEquals(advancedSearchOption.getSearchType(), SearchType.VARCHAR);
        assertEquals(0, advancedSearchOption.getOptionsList().size());
    }

}