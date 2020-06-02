package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.hibernate.dao.*;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.AdvancedSearchInterpreter;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.OptionsInterpreter;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.OrderByInterpreter;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.SelectInterpreter;

import java.util.LinkedList;
import java.util.List;

public class DataMediator implements Mediator {

    private String entityChoice;
    private DaoInterface dao;
    private List<String> optionsList;

    public DataMediator() {
    }

    public DataMediator(String entityChoice) {
        this.entityChoice = entityChoice;
    }

    @Override
    public String[] getListOfBasesFromDatabase() {
        DBCollectionDao dao = new DBCollectionDao();
        List<DBCollection> list =  dao.find();
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).getDbName();
        }
        return array;
    }

    @Override
    public List<AdvancedSearchOption> getListedOptions() {
        getListOfOptions(entityChoice);
        List<AdvancedSearchOption> list = new LinkedList<>();

        for (String s : optionsList) {
            list.add(new AdvancedSearchOption(s));
        }
            return list;

    }

    private void getListOfOptions(String entity) {
        switch (entity) {
            case "Product": {
                dao = new ProductDao();
                break;
            }
            case "User": {
                dao = new UserDao();
                break;
            }
            case "Dog": {
                dao = new DogDao();
                break;
            }
        }
        optionsList = new LinkedList<String>(dao.listOfFields());
    }

    @Override
    public List getResultList(String item, List<OptionsInterface> advanced, List<OptionsInterface> order, List<OptionsInterface> selects, int pageNumber) {
        OptionsInterpreter advancedInterpreter = new AdvancedSearchInterpreter(advanced);
        OptionsInterpreter orderInterpreter = new OrderByInterpreter(order);
        OptionsInterpreter selectInterpreter = new SelectInterpreter(selects);
        if (item.trim().equals("")) item = "%";
        switch (entityChoice) {
            case "Product": {
                dao = new ProductDao(item, advancedInterpreter, orderInterpreter, selectInterpreter,pageNumber);
                return dao.find();
            }
            case "User": {
                dao = new UserDao(item, advancedInterpreter, orderInterpreter, selectInterpreter,pageNumber);
                return dao.find();
            }
            default: {
                dao = new DogDao(item, advancedInterpreter, orderInterpreter, selectInterpreter, pageNumber);
                return dao.find();
            }
        }

    }


}
