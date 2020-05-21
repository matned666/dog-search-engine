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
public class AdvancedOptionsEnum implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private JComboBox enumList;




    public AdvancedOptionsEnum(SearchType searchType, String fieldName, List<String> enumList) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        List<String> enums = new LinkedList<>();
        enums.add("ALL");
        enums.addAll(enumList);
        String[] e = new String[enums.size()];
        enums.toArray(e);
        this.enumList = new JComboBox(e);
        this.enumList.setSelectedIndex(0);
    }

    @Override
    public Component getFirst() {
        return enumList;
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
        return new LinkedList();
    }

    @Override
    public List getContainerLabels() {
        return new LinkedList();
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
        return new LinkedList();
    }
}
