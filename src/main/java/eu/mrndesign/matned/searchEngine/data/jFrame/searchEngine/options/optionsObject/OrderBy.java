package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class OrderBy implements OptionsInterface {

    private String fieldName;
    private JCheckBox order;
    private JCheckBox desc;
    private boolean isChecked;
    private boolean isDesc;
    private List<OptionsInterface> options;

    public OrderBy(String fieldName) {
        this.fieldName = fieldName;
        order = new JCheckBox();
        desc = new JCheckBox();
        isChecked = false;
        isDesc = false;
    }


    public void createListeners(List<OptionsInterface> options) {
        this.options = options;
        this.order.addActionListener(e -> {
            for (OptionsInterface el1 : options) {
                JCheckBox boxEl = (JCheckBox) el1.getFirst();
                boxEl.setSelected(false);
                boxEl = (JCheckBox) el1.getSecond();
                boxEl.setSelected(false);
                el1.setChecked(false);
                el1.setDesc(false);
            }
            this.order.setSelected(true);
            this.isChecked = true;
        });
        this.desc.addActionListener(e -> {
            for (OptionsInterface el1 : options) {
                JCheckBox boxEl = (JCheckBox) el1.getFirst();
                boxEl.setSelected(false);
                boxEl = (JCheckBox) el1.getSecond();
                boxEl.setSelected(false);
                el1.setChecked(false);
                el1.setDesc(false);
            }
            this.desc.setSelected(true);
            this.isChecked = true;
            this.isDesc = true;
        });


    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Component getFirst() {
        return order;
    }

    @Override
    public Component getSecond() {
        return desc;
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
        return order.isSelected();
    }

    @Override
    public boolean isSecondSelected() {
        return desc.isSelected();
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
        return isChecked;
    }

    public boolean isDesc() {
        return isDesc;
    }

    @Override
    public void setChecked(boolean setter) {
        this.isChecked = setter;
    }

    @Override
    public void setDesc(boolean setter) {
        this.isDesc = setter;
    }
}
