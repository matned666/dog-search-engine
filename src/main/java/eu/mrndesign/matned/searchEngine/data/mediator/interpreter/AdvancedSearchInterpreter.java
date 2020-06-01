package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class AdvancedSearchInterpreter implements OptionsInterpreter {

    private List<OptionsInterface> options;

    boolean isDesc;
    private List<String> fieldNames;
    private List<Boolean> checks;
    private List<String> optionList;
    private List<Integer> integerList;
    private List<Boolean> optionCheckList;

    public AdvancedSearchInterpreter(List<OptionsInterface> options) {
        this.options = options;
        fieldNames = new LinkedList<>();
        checks = new LinkedList<>();
        optionList = new LinkedList<>();
        optionCheckList = new LinkedList<>();
        integerList = new LinkedList<>();
        initialize();
    }

    private void initialize() {
        for (OptionsInterface element : options) {
            fieldNames.add(element.getFieldName());
            fieldNames.add(element.getFieldName());
            checks.add(element.isFirstSelected());
            checks.add(element.isSecondSelected());
            integerList.add(element.getFirstNumber());
            integerList.add(element.getSecondNumber());
            optionList.add(element.getEnumChoice());
            optionList.add(element.getEnumChoice());
            optionCheckList.add(element.isFirstSelected());
            optionCheckList.add(element.isSecondSelected());
            System.out.println("adv: "+element.isChecked());
        }
    }

    @Override
    public List<String> getFieldNameList() {
        return fieldNames;
    }

    @Override
    public List<Integer> getIntegers() {
        return integerList;
    }

    @Override
    public List<Boolean> getChecksList() {
        return checks;
    }

    @Override
    public List<String> getOptionsList() {
        return optionList;
    }


    @Override
    public String orderBy() {
        return null;
    }


}
