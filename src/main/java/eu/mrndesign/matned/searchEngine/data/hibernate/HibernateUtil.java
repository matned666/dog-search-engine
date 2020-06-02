package eu.mrndesign.matned.searchEngine.data.hibernate;

import eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection.EntityDBCollection;
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
    private static List<EntityDBCollection> entityDbCollection;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }


    private static void collectAllDBEntities() {
            entityDbCollection = new LinkedList<>();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            try(Session session = sessionFactory.openSession()) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<EntityDBCollection> criteriaQuery = cb.createQuery(EntityDBCollection.class);
                Root<EntityDBCollection> rootTable = criteriaQuery.from(EntityDBCollection.class);
                criteriaQuery.select(rootTable);
                entityDbCollection.addAll(session.createQuery(criteriaQuery).list());
            }
            catch (HibernateException e){
                e.printStackTrace();
            }

        }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
