package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;

import java.util.LinkedList;
import java.util.List;



public class SelectInterpreter implements OptionsInterpreter {

    private final List<OptionsInterface> options;
    private final List<String> fieldNames;

    public SelectInterpreter(List<OptionsInterface> options) {
        this.options = options;
        fieldNames = new LinkedList<>();
        initialize();
    }

    private void initialize() {
        if (options.get(0).isChecked()){
            for (int i = 1; i < options.size(); i++)
                fieldNames.add(options.get(i).getFieldName());
            } else {
            for (OptionsInterface element : options) {
                if (element.isFirstSelected()) fieldNames.add(element.getFieldName());
            }
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
    public boolean isDesc() {
        return false;
    }

    @Override
    public String orderBy() {
        return null;
    }


}
