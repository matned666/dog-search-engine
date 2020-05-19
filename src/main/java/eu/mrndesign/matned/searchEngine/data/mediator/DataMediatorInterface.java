package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;

import java.util.List;

public interface DataMediatorInterface {

    String[] getListOfBasesFromDatabase();



    List getResultList(String item, SearchEngineScreen searchEngineScreen);

}
