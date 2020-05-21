package eu.mrndesign.matned.searchEngine.data.mediator.interpreter;

import java.util.List;

public interface OptionsInterpreter {
    List<String> getFieldNameList();
    List<Boolean> getChecksList();
    List<String[]> getOptionsList();
    List<Boolean[]> getOptionsChecksList();
}