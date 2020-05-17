package eu.mrndesign.matned.searchEngine.data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory ourSessionFactory;

    static {

        try {
            System.out.println("Konfiguruje hibernate");
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    public static SessionFactory getOurSessionFactory() {
        return ourSessionFactory;
    }
}
