package eu.mrndesign.matned.searchEngine;

import eu.mrndesign.matned.searchEngine.data.hibernate.dao.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;
import eu.mrndesign.matned.searchEngine.data.jFrame.Frame;

import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

//        Frame frame = new Frame();
//        frame.initialize();
        int Sooo = 56;
//        Dog.class.getField("dogRace").getType().toString().equals("class eu.mrndesign.matned.searchEngine.data.hibernate.entity.enums.DogRace")
//        Dog.class.getField("dogId").getType().toString().equals("int")
//        Dog.class.getField("dogName").getType().toString().equals("class java.lang.String")

        DogDao dogDao = new DogDao();
        List<Dog> dogs = dogDao.find();

        for (Dog el :
                dogs) {
            System.out.println(el);
        }

    }
}
