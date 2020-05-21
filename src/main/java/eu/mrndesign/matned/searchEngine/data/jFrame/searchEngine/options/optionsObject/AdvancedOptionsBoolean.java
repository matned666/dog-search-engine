package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.interpreter.SearchType;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
@ToString
public class AdvancedOptionsBoolean implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private JCheckBox checkBoxTrue;
    private JCheckBox checkBoxFalse;



    public AdvancedOptionsBoolean(SearchType searchType, String fieldName) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        this.checkBoxTrue = new JCheckBox();
        this.checkBoxFalse = new JCheckBox();
    }

    @Override
    public Component getFirst() {
        return checkBoxTrue;
    }

    @Override
    public Component getSecond() {
        return checkBoxFalse;
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
