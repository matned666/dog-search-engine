package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;

import java.util.LinkedList;
import java.util.List;

public class SelectInterpreter implements OptionsInterpreter {

    List<OptionsInterface> options;

    List<String> fieldNames;
    List<Boolean> checks;

    public SelectInterpreter(List<OptionsInterface> options) {
        this.options = options;
        fieldNames = new LinkedList<>();
        checks = new LinkedList<>();
        initialize();
    }

    private void initialize() {
        for (OptionsInterface element : options) {
            if (element.isFirstSelected()) fieldNames.add(element.getFieldName());
        }
    }

    @Override
    public List getList() {
        return fieldNames;
    }
}
