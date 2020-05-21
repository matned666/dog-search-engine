package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;

@Data
@ToString
public class AdvancedOptionsVarchar implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private JCheckBox checkBox;
    private boolean isChecked;

    public AdvancedOptionsVarchar(SearchType searchType, String fieldName) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        this.checkBox = new JCheckBox();
        isChecked = false;
        this.checkBox.addChangeListener(e -> {
            if (isChecked) isChecked = true;
            else isChecked = false;
        });
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

    @Override
    public boolean isFirstSelected() {
        return isChecked;
    }

    @Override
    public boolean isSecondSelected() {
        return false;
    }

    @Override
    public List<Boolean> getContainersChecks() {
        return null;
    }

}
