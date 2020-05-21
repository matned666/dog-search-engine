package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import java.awt.Component;
import java.util.List;

@Data
@ToString
public class AdvancedOptionsNumber implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private JTextField first;
    private JTextField last;


    public AdvancedOptionsNumber(SearchType searchType, String fieldName) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        first = new JTextField("0");
        last = new JTextField("999999");
    }

    @Override
    public Component getSecond() {
        return last;
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
        return false;
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
