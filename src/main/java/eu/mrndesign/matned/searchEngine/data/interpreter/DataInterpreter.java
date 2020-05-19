package eu.mrndesign.matned.searchEngine.data.interpreter;

import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DBCollectionDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.dao.ProductDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DataInterpreter  {

    private DaoInterface dao;

    public String[] getListOfBasesFromDatabase() {
        DBCollectionDao dao = new DBCollectionDao();
        List<DBCollection> list =  dao.find();
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).getDbName();
        }
        return array;
    }

    public List<String[]> getListOfFields(String entity) {
        if(entity.equals("Dog")) {
            dao = new DogDao();
            return dao.listOfFields();
        }else if(entity.equals("")){
            dao = new ProductDao();
            return dao.listOfFields();
        }else return null;
    }

    public List getResultList(String item, String entityChoice) {
        switch (entityChoice) {
//            case "Dog": {
//                dao = new DogDao(item);
//                return dao.find();
//            }
//            case "Product": {
//                dao = new ProductDao();
//                return dao.find();
//            }
            default: {
                dao = new DogDao(item);
                return dao.find();
            }
        }

    }
}
