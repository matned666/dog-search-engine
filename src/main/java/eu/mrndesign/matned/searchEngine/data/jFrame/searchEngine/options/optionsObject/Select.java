package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Select implements OptionsInterface{
    private String fieldName;
    private JCheckBox check;
    private boolean isChecked;


    public Select(String fieldName) {
        this.fieldName = fieldName;
        check = new JCheckBox();
        isChecked = false;
        if (fieldName.equals("ALL FIELDS")) {
            check.setSelected(true);
            isChecked = true;
        }


    }

    @Override
    public void createListeners(List<OptionsInterface> options) {
        this.check.addActionListener(e -> {
            if(this.fieldName.equals(options.get(0).getFieldName())){
                setAllFalse(options);
                check.setSelected(true);
                isChecked = true;
            }else {
                options.get(0).setChecked(false);
                JCheckBox boxEl = (JCheckBox) options.get(0).getFirst();
                boxEl.setSelected(false);
                if (isChecked && isOnlyOneChecked(options)) {
                    JCheckBox boxE2 = (JCheckBox) options.get(0).getFirst();
                    boxE2.setSelected(true);
                    options.get(0).setChecked(true);
                }
                isChecked = !isChecked;

            }
        });
    }

    private boolean isOnlyOneChecked(List<OptionsInterface> options) {
        int counter = 0;
        for (OptionsInterface el :
                options) {
            if (el.isChecked()) counter++;
        }
        return counter <= 1;
    }

    private void setAllFalse(List<OptionsInterface> options) {
        for (OptionsInterface el : options) {
            JCheckBox boxEl = (JCheckBox) el.getFirst();
            boxEl.setSelected(false);
            el.setChecked(false);
        }
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
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public boolean isFirstSelected() {
        return isChecked;
    }

    @Override
    public List getContainers() {
        return null;
    }

    @Override
    public Component getSecond() {
        return check;
    }

    @Override
    public boolean isSecondSelected() {
        return isChecked;
    }

    @Override
    public boolean isDesc() {
        return isChecked;
    }

    @Override
    public void setChecked(boolean setter) {
        isChecked = setter;
    }

    @Override
    public void setDesc(boolean setter) {
        isChecked = setter;
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
    public List getContainerLabels() {
        return null;
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
}
