package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Select implements OptionsInterface{
    private String fieldName;
    private JCheckBox check;
    boolean isChecked;


    public Select(String fieldName) {
        this.fieldName = fieldName;
        check = new JCheckBox();
        isChecked = false;
        isChecked = false;

        this.check.addActionListener(e -> {
            if (!isChecked) {
                isChecked = true;
            }
            else {
                isChecked = false;
            }
        });
    }


    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Component getFirst() {
        return check;
    }

    @Override
    public Component getSecond() {
        return null;
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
    public SearchType getSearchType() {
        return null;
    }

    @Override
    public List<Boolean> getContainersChecks() {
        return null;
    }

    @Override
    public String getEnumChoice() {
        return null;
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void createListeners(List<OptionsInterface> options) {

    }

    @Override
    public boolean isDesc() {
        return false;
    }

    @Override
    public void setChecked(boolean setter) {

    }

    @Override
    public void setDesc(boolean setter) {

    }
}
