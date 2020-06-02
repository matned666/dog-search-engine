package eu.mrndesign.matned.searchEngine.data.hibernate;

import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.*;

import java.util.List;

public interface DaoInterface<E> {

    List<E> find(String item,
                 AdvancedSearchInterpreterInterface advancedInterpreter,
                 OrderByInterpreterInterface orderInterpreter,
                 SelectInterpreterInterface selectInterpreter,
                 Integer pageNo);
    List<String> listOfFields();
}
