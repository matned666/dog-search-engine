package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.interpreter.SearchType;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
@ToString
public class AdvancedOptionsVarchar implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private JCheckBox checkBox;

    public AdvancedOptionsVarchar(SearchType searchType, String fieldName) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        this.checkBox = new JCheckBox();
    }


    @Override
    public Component getFirst() {
        return checkBox;
    }

    //TODO -->  remove some methods that are not in use

    @Override
    public Component getSecond() {
        return null;
    }

    @Override
    public List getContainers() {
        return null;
    }

    @Override
    public List getContainerLabels() {
        return null;
    }
}
