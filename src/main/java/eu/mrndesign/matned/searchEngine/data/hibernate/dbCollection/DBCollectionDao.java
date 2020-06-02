package eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class DBCollectionDao implements DaoInterface<EntityDBCollection> {

    private static final String ID = "Id";
    private static final String NAME = "Name";

    @Override
    public List<EntityDBCollection> find(String item,
                                         AdvancedSearchInterpreterInterface advancedInterpreter,
                                         OrderByInterpreterInterface orderInterpreter,
                                         SelectInterpreterInterface selectInterpreter,
                                         Integer pageNo) {
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EntityDBCollection> criteriaQuery = cb.createQuery(EntityDBCollection.class);
            Root<EntityDBCollection> rootTable = criteriaQuery.from(EntityDBCollection.class);
            criteriaQuery.select(rootTable);
            result.addAll(session.createQuery(criteriaQuery).list());
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> listOfFields() {
        return Arrays.asList(
                NUMBER_+_i_+ ID,
                VARCHAR_+_i_+ NAME
        );
    }

}
