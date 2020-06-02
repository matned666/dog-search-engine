package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OrderBy;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class OrderByInterpreter implements OptionsInterpreter {

    private List<OptionsInterface> options;
    private OrderBy option;
    private List<String> fieldNames;
    private boolean isDesc;
    private String fieldName;

    public OrderByInterpreter(List<OptionsInterface> options) {
        this.options = options;
        fieldNames = new LinkedList<>();
        isDesc = false;
        initialize();
    }

    private void initialize() {
        System.out.println(option);
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
    public String orderBy() {
        getCheckedOption();
        return fieldName;
    }

    private void getCheckedOption() {
        for (OptionsInterface el: options) {
            if(el.isChecked()){
                if (el.isDesc()) isDesc = true;
                fieldName = el.getFieldName();
                break;

            }
        }
    }


}
