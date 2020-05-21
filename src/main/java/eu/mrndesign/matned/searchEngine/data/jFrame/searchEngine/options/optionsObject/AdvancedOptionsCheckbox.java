package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
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
            containers.add(new Checkbox());
            checks.add(false);
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
        return checks;
    }
}
