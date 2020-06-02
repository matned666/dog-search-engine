package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.hibernate.DaoFactory;
import eu.mrndesign.matned.searchEngine.data.hibernate.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection.EntityDBCollection;
import eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection.DBCollectionDao;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.*;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

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
        DBCollectionDao dao = new DBCollectionDao();
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

    private void getListOfOptions(String entity) {
        dao = new DaoFactory().dao(entity);
        optionsList = new LinkedList<String>(dao.listOfFields());
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

}
