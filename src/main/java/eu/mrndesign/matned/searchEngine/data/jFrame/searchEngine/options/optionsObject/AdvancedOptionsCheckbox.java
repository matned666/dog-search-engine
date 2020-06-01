package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;


public class AdvancedOptionsCheckbox implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private List<String> containersLabels;
    private List<JCheckBox> containers;
    private List<Boolean> checks;

    public AdvancedOptionsCheckbox(SearchType searchType, String fieldName, List labels) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        containers = new LinkedList<>();
        containersLabels = new LinkedList<>();
        checks = new LinkedList<>();
        containersLabels.addAll(labels);
        initializeLists();
    }

    private void initializeLists() {
        for (String ignored : containersLabels) {
            containers.add(new JCheckBox());
            checks.add(false);
        }
    }


    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Component getFirst() {
        return containers.get(0);
    }

    @Override
    public Component getSecond() {
        return containers.get(1);
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
        return containers;
    }

    @Override
    public List getContainerLabels() {
        return containersLabels;
    }

    @Override
    public boolean isFirstSelected() {
        return containers.get(0).isSelected();
    }

    @Override
    public boolean isSecondSelected() {
        return containers.get(1).isSelected();
    }

    @Override
    public SearchType getSearchType() {
        return searchType;
    }

    @Override
    public List<Boolean> getContainersChecks() {
        return checks;
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
