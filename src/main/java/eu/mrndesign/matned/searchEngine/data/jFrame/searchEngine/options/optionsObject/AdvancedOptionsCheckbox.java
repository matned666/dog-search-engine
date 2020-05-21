package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.interpreter.SearchType;
import lombok.Data;
import lombok.ToString;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString
public class AdvancedOptionsCheckbox implements OptionsInterface{

    private SearchType searchType;
    private String fieldName;
    private List<String> containersLabels;
    private List<Checkbox> containers;

    public AdvancedOptionsCheckbox(SearchType searchType, String fieldName, List labels) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        containers = new LinkedList<>();
        containersLabels = new LinkedList<>();
        containersLabels.addAll(labels);
        initializeLists();
    }

    private void initializeLists() {
        for (String ignored : containersLabels) {
            containers.add(new Checkbox());
        }
    }


    @Override
    public Component getFirst() {
        return null;
    }

    @Override
    public Component getSecond() {
        return null;
    }

    @Override
    public List getContainerLabels() {
        return containersLabels;
    }
}
