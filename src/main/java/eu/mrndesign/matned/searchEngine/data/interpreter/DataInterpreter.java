package eu.mrndesign.matned.searchEngine.data.interpreter;

import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DBCollectionDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.ProductDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

public class DataInterpreter implements Interpreter  {

    private String entityChoice;
    private DaoInterface dao;

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
    public List<AdvancedSearchOptions> getListOptions(String entity) {
        try{
            List<AdvancedSearchOptions> list = new LinkedList();
            String arr[] = entity.split("::");
            for (String el: arr){
                list.add(new AdvancedSearchOptions(el));
            }
            return list;
        }catch (Exception e){
            return new LinkedList<>();
        }
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
