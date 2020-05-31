package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
@ToString
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
            isChecked = !isChecked;
            isDesc = false;
            for (OptionsInterface el1 : options) {
                if (el1 instanceof AdvancedOptionsVarchar){
                    JCheckBox boxEl = (JCheckBox) el1.getFirst();
                    if (boxEl.isSelected() && !boxEl.equals(this.order))
                        boxEl.setSelected(false);
                }
            }
        });
       this.desc.addActionListener(e -> {
            isChecked = !isChecked;
           isDesc = true;
           for (OptionsInterface el1 : options) {
                if (el1 instanceof AdvancedOptionsVarchar){
                    JCheckBox boxEl = (JCheckBox) el1.getFirst();
                    if (boxEl.isSelected() && !boxEl.equals(this.desc))
                        boxEl.setSelected(false);
                }
            }
        });
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
    public List<Boolean> getContainersChecks() {
        return null;
    }

    @Override
    public String getEnumChoice() {
        return null;
    }
}
