package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OrderBy;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class OrderByInterpreter implements OrderByInterpreterInterface{

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

    @Override
    public String orderBy() {
        getCheckedOption();
        return fieldName;
    }

    @Override
    public boolean isDesc() {
        return isDesc;
    }

    private void initialize() {
        System.out.println(option);
        for (OptionsInterface element : options) {
            if (element.isFirstSelected()) fieldNames.add(element.getFieldName());
        }
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
