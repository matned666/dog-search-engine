package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import java.util.List;

public interface DaoInterface<E> {

    List<E> find();
    List<String> listOfFields();
}
