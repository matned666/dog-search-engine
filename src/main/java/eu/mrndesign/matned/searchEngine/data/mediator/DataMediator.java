package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.hibernate.DaoFactory;
import eu.mrndesign.matned.searchEngine.data.hibernate.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection.EntityDBCollection;
import eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection.DBCollectionDao;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

@Data
public class DataMediator implements Mediator {

    private String entityChoice;
    private DaoInterface dao;
    private List<String> optionsList;

    public DataMediator() {}

    public DataMediator(String entityChoice) {
        this.entityChoice = entityChoice;
    }

    @Override
    public String[] getListOfTablesFromDatabase() {
        produceDao(null);
        List<EntityDBCollection> list =  dao.find(null, null, null, null,0);
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).getDbName();
        }
        return array;
    }

    @Override
    public List<AdvancedSearchOptionInterface> getListedOptions() {
        getListOfOptions(entityChoice);
        List<AdvancedSearchOptionInterface> list = new LinkedList<>();
        for (String s : optionsList) {
            list.add(new AdvancedSearchOption(s));
        }
            return list;
    }

    @Override
    public List getResultList(String item,
                              List<OptionsInterface> advanced,
                              List<OptionsInterface> order,
                              List<OptionsInterface> selects,
                              int pageNumber) {
        AdvancedSearchInterpreterInterface advancedInterpreter = new AdvancedSearchInterpreter(advanced);
        OrderByInterpreterInterface orderInterpreter = new OrderByInterpreter(order);
        SelectInterpreterInterface selectInterpreter = new SelectInterpreter(selects);
        if (item.trim().equals(EMPTY)) item = LIKE_ALL_SIGN;
        dao = new DaoFactory().dao(entityChoice);
        return dao.find(item, advancedInterpreter, orderInterpreter, selectInterpreter,pageNumber);
    }

    void produceDao(String entity) {
        if (entity == null) dao = new DBCollectionDao();
        else dao = new DaoFactory().dao(entity);
    }

    public void setEntityChoice(String entityChoice) {
        this.entityChoice = entityChoice;
    }

    private void getListOfOptions(String entity) {
        produceDao(entity);
        optionsList = new LinkedList<String>(dao.listOfFields());
    }


}
