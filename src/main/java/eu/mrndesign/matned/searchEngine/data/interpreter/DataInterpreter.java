package eu.mrndesign.matned.searchEngine.data.interpreter;

import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DBCollectionDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.ProductDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;

import java.util.LinkedList;
import java.util.List;

public class DataInterpreter implements Interpreter  {

    private String entityChoice;
    private DaoInterface dao;
    private List<String> optionsList;

    public DataInterpreter() {
    }

    public DataInterpreter(String entityChoice) {
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
            case "Dog": {
                dao = new DogDao();
                break;
            }
        }
        optionsList = new LinkedList<String>(dao.listOfFields());
    }

    @Override
    public List getResultList(String item) {
        if (item.trim().equals("")) item = "%";
        switch (entityChoice) {
            case "Product": {
                dao = new ProductDao(item);
                return dao.find();
            }
            default: {
                dao = new DogDao(item);
                return dao.find();
            }
        }

    }


}
