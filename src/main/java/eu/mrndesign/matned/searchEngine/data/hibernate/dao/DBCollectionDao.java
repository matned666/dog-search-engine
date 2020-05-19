package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.DBCollection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class DBCollectionDao {


    public List<DBCollection> find() {
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<DBCollection> criteriaQuery = cb.createQuery(DBCollection.class);
            Root<DBCollection> rootTable = criteriaQuery.from(DBCollection.class);
            criteriaQuery.select(rootTable);
            result.addAll(session.createQuery(criteriaQuery).list());
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return result;
    }

}
