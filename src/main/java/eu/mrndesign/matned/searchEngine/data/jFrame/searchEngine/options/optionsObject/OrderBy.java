package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
@ToString
public class OrderBy implements OptionsInterface {

    String fieldName;
    JCheckBox order;
    JCheckBox desc;

    public OrderBy(String fieldName) {
        this.fieldName = fieldName;
        order = new JCheckBox();
        desc = new JCheckBox();
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
