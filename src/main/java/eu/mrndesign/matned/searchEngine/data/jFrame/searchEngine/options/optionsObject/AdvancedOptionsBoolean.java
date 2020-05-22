package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
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
        this.checkBoxFalse = new JCheckBox();
        isCheckBoxTrue = false;
        isCheckBoxFalse = false;
        this.checkBoxTrue.addActionListener(e -> isCheckBoxTrue = !isCheckBoxTrue);
        this.checkBoxFalse.addActionListener(e -> isCheckBoxFalse = !isCheckBoxFalse);
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
    public Integer getFirstNumber() {
        return null;
    }

    @Override
    public Integer getSecondNumber() {
        return null;
    }

    @Override
    public List getContainers() {
        return new LinkedList();
    }

    @Override
    public List getContainerLabels() {
        return new LinkedList();
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
        return new LinkedList();
    }

    @Override
    public String getEnumChoice() {
        return null;
    }
}
