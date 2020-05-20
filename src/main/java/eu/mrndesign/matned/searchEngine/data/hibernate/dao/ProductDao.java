package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProductDao implements DaoInterface<Product> {

    private String prodName;

    public ProductDao(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public List<Product> find() {
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
            Root<Product> rootTable = criteriaQuery.from(Product.class);
            criteriaQuery.select(rootTable)
                    .where(
                            cb.like(rootTable.get("productName"), prodName)

                    )
            ;
            result.addAll(session.createQuery(criteriaQuery)
                    .list());
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        System.out.println("List size: "+result.size());
        return result;
    }

    @Override
    public List<String> listOfFields() {
        return Arrays.asList("NUMBER::Id","VARCHAR::Name","NUMBER::Value","NUMBER::Project details id");
    }
}
