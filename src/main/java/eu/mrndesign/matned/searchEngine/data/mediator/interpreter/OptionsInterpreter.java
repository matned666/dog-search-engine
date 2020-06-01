package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;

import java.util.List;

public interface OptionsInterpreter {
    List<String> getFieldNameList();
    List<Integer> getIntegers();
    List<Boolean> getChecksList();
    List<String> getOptionsList();
//    OptionsInterface getCheckedOption();
//    List<Boolean> getOptionsChecksList();
    boolean isDesc();
    String orderBy();

}
