package eu.mrndesign.matned.searchEngine;

import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;

import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {


        DogDao dogDao = new DogDao();

        List<Dog> dogs = dogDao.find();
        for (Dog el :
                dogs) {
            System.out.println(el);
        }

    }
}
