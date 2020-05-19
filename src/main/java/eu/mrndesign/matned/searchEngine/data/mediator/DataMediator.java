package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DBCollectionDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DataMediator implements DataMediatorInterface {

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
    public List getResultList(String item, SearchEngineScreen searchEngineScreen) {
        return null;
    }
}
