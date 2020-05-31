package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;

import java.util.LinkedList;
import java.util.List;

public class SelectInterpreter implements OptionsInterpreter {

    private List<OptionsInterface> options;

    private List<String> fieldNames;

    public SelectInterpreter(List<OptionsInterface> options) {
        this.options = options;
        fieldNames = new LinkedList<>();
        initialize();
    }

    private void initialize() {
        for (OptionsInterface element : options) {
            if (element.isFirstSelected()) fieldNames.add(element.getFieldName());
        }
    }

    @Override
    public List<String> getFieldNameList() {
        return fieldNames;
    }

    @Override
    public List<Integer> getIntegers() {
        return null;
    }

    @Override
    public List<Boolean> getChecksList() {
        return null;
    }

    @Override
    public List<String> getOptionsList() {
        return null;
    }

    @Override
    public OptionsInterface getCheckedOption() {
        return null;
    }

    @Override
    public List<Boolean> getOptionsChecksList() {
        return null;
    }
}
