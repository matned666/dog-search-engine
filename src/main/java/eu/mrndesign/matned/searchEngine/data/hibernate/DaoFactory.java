package eu.mrndesign.matned.searchEngine.data.hibernate;

import eu.mrndesign.matned.searchEngine.data.hibernate.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.dog.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.product.ProductDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.user.UserDao;

import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

public class DaoFactory {

    public DaoFactory() {
    }

    public DaoInterface dao(String entity){
        DaoInterface dao;
        switch (entity) {
            case PRODUCT: {
                dao = new ProductDao();
                break;
            }
            case USER: {
                dao = new UserDao();
                break;
            }
            default: {
                dao = new DogDao();
                break;
            }
        }
        return dao;
    }

}
