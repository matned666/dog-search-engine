package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
@ToString
public class Select implements OptionsInterface{
    String fieldName;
    JCheckBox check;
    boolean isChecked;


    public Select(String fieldName) {
        this.fieldName = fieldName;
        check = new JCheckBox();
        isChecked = false;
        isChecked = false;
        this.check.addChangeListener(e -> {
            if (isChecked) isChecked = true;
            else isChecked = false;
        });
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
