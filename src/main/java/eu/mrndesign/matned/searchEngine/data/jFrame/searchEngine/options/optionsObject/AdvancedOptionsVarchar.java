package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Data
public class AdvancedOptionsVarchar implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private JCheckBox checkBox;
    private boolean isChecked;
    private List<OptionsInterface> options;

    public AdvancedOptionsVarchar(SearchType searchType, String fieldName) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        this.checkBox = new JCheckBox();
        isChecked = false;
    }

    public void createListener(List<OptionsInterface> options) {
        this.options = options;
        this.checkBox.addActionListener(e -> {
            for (OptionsInterface el1 : options) {
                if (el1.getSearchType() == SearchType.VARCHAR){
                    JCheckBox boxEl = (JCheckBox) el1.getFirst();
                    boxEl.setSelected(false);
                    isChecked = false;
                }
            }
            this.isChecked = true;
            this.checkBox.setSelected(true);
        });
    }


    @Override
    public Component getFirst() {
        return checkBox;
    }

    //TODO -->  try to remove some methods that are not in use

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
        return new LinkedList();
    }

    @Override
    public List getContainerLabels() {
        return new LinkedList();
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
        return new LinkedList();
    }

    @Override
    public String getEnumChoice() {
        return null;
    }

    @Override
    public void createListeners(List<OptionsInterface> options) {

    }

    @Override
    public boolean isDesc() {
        return false;
    }

    @Override
    public void setDesc(boolean setter) {

    }

}
