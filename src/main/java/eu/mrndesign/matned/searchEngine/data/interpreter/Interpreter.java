package eu.mrndesign.matned.searchEngine.data.interpreter;

import java.util.List;

public interface Interpreter {

    List getResultList(String item, String entityChoice);
    String[] getListOfBasesFromDatabase();
    List<String[]> interpretListOfFields(String entity);

}
