package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Product;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.OptionsInterpreter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private String item;
    private OptionsInterpreter advancedInterpreter;
    private OptionsInterpreter orderInterpreter;
    private OptionsInterpreter selectInterpreter;
    private String orderBy;

    private String prodName;
    private int id1;
    private int id2;
    private int value1;
    private int value2;
    private int detailsId1;
    private int detailsId2;

    public ProductDao() {
    }

    public ProductDao(String item, OptionsInterpreter advancedInterpreter, OptionsInterpreter orderInterpreter, OptionsInterpreter selectInterpreter) {
        this.item = item;
        this.advancedInterpreter = advancedInterpreter;
        this.orderInterpreter = orderInterpreter;
        this.selectInterpreter = selectInterpreter;
        initialize();
    }

    @Override
    public List<Product> find() {
        orderBy = orderInterpreter.orderBy() != null ?orderInterpreter.orderBy() : "productId";
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
            Root<Product> rootTable = criteriaQuery.from(Product.class);
            criteriaQuery.select(rootTable)
                    .where(
                            cb.and(
                                    cb.like(rootTable.get("productName"), prodName),
                                    cb.between(rootTable.get("productId"), id1,id2),
                                    cb.between(rootTable.get("productValue"), value1,value2),
                                    cb.between(rootTable.get("productDetailsId"), detailsId1,detailsId2)
                            )
                    );
            criteriaQuery.orderBy(orderInterpreter.isDesc() ? cb.desc(rootTable.get(orderBy)) : cb.asc(rootTable.get(orderBy)));
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
        return Arrays.asList("NUMBER::productId::","VARCHAR::productName::","NUMBER::productValue::","NUMBER::productDetailsId::");
    }

    private void initialize() {
        prodName = item;
        id1 = advancedInterpreter.getIntegers().get(2);
        id2 = advancedInterpreter.getIntegers().get(3);
        value1 = advancedInterpreter.getIntegers().get(4);
        value2 = advancedInterpreter.getIntegers().get(5);
        detailsId1 = advancedInterpreter.getIntegers().get(6);
        detailsId2 = advancedInterpreter.getIntegers().get(7);
    }
}
