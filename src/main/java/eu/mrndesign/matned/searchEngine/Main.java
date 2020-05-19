package eu.mrndesign.matned.searchEngine;


import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;
import eu.mrndesign.matned.searchEngine.data.jFrame.ScreenManager;

import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        ScreenManager manager = new ScreenManager();
        manager.start();

//        DogDao dogDao = new DogDao();
//
//        List<Dog> dogs = dogDao.find();
//        for (Dog el :
//                dogs) {
//            System.out.println(el);
//        }


    }
}
