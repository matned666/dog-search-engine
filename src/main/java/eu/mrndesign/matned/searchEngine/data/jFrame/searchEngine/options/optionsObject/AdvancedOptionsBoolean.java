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
public class AdvancedOptionsBoolean implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private JCheckBox checkBoxTrue;
    private boolean isCheckBoxTrue;
    private JCheckBox checkBoxFalse;
    private boolean isCheckBoxFalse;



    public AdvancedOptionsBoolean(SearchType searchType, String fieldName) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        this.checkBoxTrue = new JCheckBox();
        isCheckBoxTrue = false;
        isCheckBoxFalse = false;
        this.checkBoxTrue.addChangeListener(e -> {
            if (isCheckBoxTrue) isCheckBoxTrue = true;
            else isCheckBoxTrue = false;
        });
        this.checkBoxFalse.addChangeListener(e -> {
            if (isCheckBoxFalse) isCheckBoxFalse = true;
            else isCheckBoxFalse = false;
        });
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

    @Override
    public boolean isFirstSelected() {
        return isCheckBoxTrue;
    }

    @Override
    public boolean isSecondSelected() {
        return isCheckBoxFalse;
    }

    @Override
    public List<Boolean> getContainersChecks() {
        return null;
    }
}
