package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OrderBy;

import java.util.LinkedList;
import java.util.List;

public class OrderByInterpreter implements OptionsInterpreter {

    private List<OptionsInterface> options;
    private OrderBy option;
    private List<String> fieldNames;

    public OrderByInterpreter(List<OptionsInterface> options) {
        this.options = options;
        fieldNames = new LinkedList<>();
        initialize();
    }

    private void initialize() {
        for (OptionsInterface element : options) {
            if (element.isFirstSelected()) fieldNames.add(element.getFieldName());
        }
//        TODO - check and repair
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
        for (OptionsInterface el: options) {
            if(el.isChecked()){
                option = (OrderBy) el;
            }
        }
        return option;
    }

    @Override
    public List<Boolean> getOptionsChecksList() {
        return null;
    }
}
