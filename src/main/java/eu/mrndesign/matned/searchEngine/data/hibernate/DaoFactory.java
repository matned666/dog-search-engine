package eu.mrndesign.matned.searchEngine.data.hibernate;

import eu.mrndesign.matned.searchEngine.data.hibernate.dog.DogDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.product.ProductDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.user.UserDao;

public class DaoFactory {

    private static final String PRODUCT = "Product";
    private static final String USER = "User";

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
