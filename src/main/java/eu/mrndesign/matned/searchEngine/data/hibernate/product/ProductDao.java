package eu.mrndesign.matned.searchEngine.data.hibernate.product;

import eu.mrndesign.matned.searchEngine.data.hibernate.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.searchEngine.data.hibernate.product.ProductDaoStatics.*;
import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

public class ProductDao implements DaoInterface<EntityProduct> {

    private String item;
    private AdvancedSearchInterpreterInterface advancedInterpreter;

    private String prodName;
    private int id1;
    private int id2;
    private int value1;
    private int value2;
    private int detailsId1;
    private int detailsId2;

    private List<String> selectList;

    public ProductDao() {
    }


    @Override
    public List<EntityProduct> find(String item,
                                    AdvancedSearchInterpreterInterface advancedInterpreter,
                                    OrderByInterpreterInterface orderInterpreter,
                                    SelectInterpreterInterface selectInterpreter,
                                    Integer pageNo) {
        this.item = item;
        this.advancedInterpreter = advancedInterpreter;
        int firstResult = MAX_RESULTS_ON_SCREEN * pageNo;
        selectList = new LinkedList(selectInterpreter.getFieldNameList());
        initialize();
        String orderBy = orderInterpreter.orderBy() != null ? orderInterpreter.orderBy() : PRODUCT_ID;
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EntityProduct> criteriaQuery = cb.createQuery(EntityProduct.class);
            Root<EntityProduct> rootTable = criteriaQuery.from(EntityProduct.class);
            whereStatement(cb, criteriaQuery, rootTable);
            criteriaQuery.orderBy(orderInterpreter.isDesc() ? cb.desc(rootTable.get(orderBy)) : cb.asc(rootTable.get(orderBy)));
            result.addAll(session.createQuery(criteriaQuery)
                    .setFirstResult(firstResult)
                    .setMaxResults(MAX_RESULTS_ON_SCREEN)
                    .list());
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        changeSelect(result);
        return result;
    }

    @Override
    public List<String> listOfFields() {
        return Arrays.asList(
                NUMBER_+ _i_ +PRODUCT_ID+ _i_,
                VARCHAR_+ _i_ +PRODUCT_NAME+ _i_,
                NUMBER_+ _i_ +PRODUCT_VALUE+ _i_,
                NUMBER_+ _i_ +PRODUCT_DETAILS_ID+ _i_);
    }

            /*
    PRIVATE METHODS
    -------------------------------
    Data list indexes:
    productName 0,1 - constant forced by item
    productId 2,3
    productValue 4,5
    productDetailsId 6,7

     */

    private void whereStatement(CriteriaBuilder cb, CriteriaQuery<EntityProduct> criteriaQuery, Root<EntityProduct> rootTable) {
        criteriaQuery.select(rootTable)
                .where(
                        cb.and(
                                cb.like(rootTable.get(PRODUCT_NAME), prodName),
                                cb.between(rootTable.get(PRODUCT_ID), id1,id2),
                                cb.between(rootTable.get(PRODUCT_VALUE), value1,value2),
                                cb.between(rootTable.get(PRODUCT_DETAILS_ID), detailsId1,detailsId2)
                        )
                );
    }

    private void changeSelect(List<EntityProduct> result) {
        for (EntityProduct el : result) {
            if (!selectList.contains(PRODUCT_ID)) el.setProductId(null);
            if (!selectList.contains(PRODUCT_NAME)) el.setProductName(null);
            if (!selectList.contains(PRODUCT_VALUE)) el.setProductValue(null);
            if (!selectList.contains(PRODUCT_DETAILS_ID)) el.setProductDetailsId(null);
        }
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
