package eu.mrndesign.matned.searchEngine.data.hibernate;

import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static List<DBCollection> dbCollection;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
            addAllEntities(configuration);
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private static void addAllEntities(Configuration configuration)  {
        collectAllDBEntities();
        for (DBCollection el :
                dbCollection) {
            try {
                configuration.addAnnotatedClass(Class.forName(el.getDbName()));
            } catch (ClassNotFoundException e) {
                System.err.println("Class: "+el.getDbName()+" not found !!");
            }
        }

    }


    private static void collectAllDBEntities() {
            dbCollection = new LinkedList<>();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            try(Session session = sessionFactory.openSession()) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<DBCollection> criteriaQuery = cb.createQuery(DBCollection.class);
                Root<DBCollection> rootTable = criteriaQuery.from(DBCollection.class);
                criteriaQuery.select(rootTable);
                dbCollection.addAll(session.createQuery(criteriaQuery).list());
            }
            catch (HibernateException e){
                e.printStackTrace();
            }

        }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
