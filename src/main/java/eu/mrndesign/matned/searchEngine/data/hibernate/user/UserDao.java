package eu.mrndesign.matned.searchEngine.data.hibernate.user;

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

import static eu.mrndesign.matned.searchEngine.data.hibernate.user.UserDaoStatics.*;
import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

public class UserDao implements DaoInterface<EntityUser> {

    private String item;
    private AdvancedSearchInterpreterInterface advancedInterpreter;

    private String firstName;
    private String lastName;
    private String email;
    private String gender1;
    private String gender2;
    private String ipAddress;
    private int id1;
    private int id2;

    private List<String> selectList;

    public UserDao() {
    }

    @Override
    public List<EntityUser> find(String item,
                                 AdvancedSearchInterpreterInterface advancedInterpreter,
                                 OrderByInterpreterInterface orderInterpreter,
                                 SelectInterpreterInterface selectInterpreter,
                                 Integer pageNo) {
        this.item = item;
        this.advancedInterpreter = advancedInterpreter;
        int firstResult = MAX_RESULTS_ON_SCREEN * pageNo;
        selectList = new LinkedList(selectInterpreter.getFieldNameList());
        initialize();
        String orderBy = orderInterpreter.orderBy() != null ? orderInterpreter.orderBy() : USER_ID;
        List<EntityUser> result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EntityUser> criteriaQuery = cb.createQuery(EntityUser.class);
            Root<EntityUser> rootTable = criteriaQuery.from(EntityUser.class);
            whereStatement(cb, criteriaQuery, rootTable);
            criteriaQuery.orderBy(orderInterpreter.isDesc() ? cb.desc(rootTable.get(orderBy)) : cb.asc(rootTable.get(orderBy)));
            result.addAll(session.createQuery(criteriaQuery)
                    .setFirstResult(firstResult)
                    .setMaxResults(MAX_RESULTS_ON_SCREEN)
                    .list());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        changeSelect(result);
        return result;
    }

    @Override
    public List<String> listOfFields() {
        return Arrays.asList(
                NUMBER_+ _i_ +USER_ID+ _i_,
                VARCHAR_+ _i_ +FIRST_NAME+ _i_,
                VARCHAR_+ _i_ +LAST_NAME+ _i_,
                CHECKBOX_+ _i_ +GENDER+ _i_ +MALE+ _i_ +FEMALE,
                VARCHAR_+ _i_ +EMAIL+ _i_,
                VARCHAR_+ _i_ +IP_ADDRESS+ _i_);
    }

        /*
    PRIVATE METHODS
    -------------------------------
    Data list indexes:
    name 0,1
    lastName 2,3
    email 4,5
    ipAddress 6,7
    userId 8,9
    gender 10,11

     */

    private void whereStatement(CriteriaBuilder cb, CriteriaQuery<EntityUser> criteriaQuery, Root<EntityUser> rootTable) {
        criteriaQuery.select(rootTable)
                .where(
                        cb.and(
                                cb.like(rootTable.get(FIRST_NAME), firstName),
                                cb.like(rootTable.get(LAST_NAME), lastName),
                                cb.like(rootTable.get(EMAIL), email),
                                cb.like(rootTable.get(IP_ADDRESS), ipAddress)
                        ),
                        cb.between(rootTable.get(USER_ID), id1, id2),
                        cb.or(
                                cb.equal(rootTable.get(GENDER), gender1),
                                cb.equal(rootTable.get(GENDER), gender2)

                        )
                );
    }

    private void changeSelect(List<EntityUser> result) {
        for (EntityUser el : result) {
            if (!selectList.contains(USER_ID)) el.setUserId(null);
            if (!selectList.contains(FIRST_NAME)) el.setFirstName(null);
            if (!selectList.contains(LAST_NAME)) el.setLastName(null);
            if (!selectList.contains(GENDER)) el.setGender(null);
            if (!selectList.contains(EMAIL)) el.setEmail(null);
            if (!selectList.contains(IP_ADDRESS)) el.setIpAddress(null);
        }
    }

    private void initialize() {
        id1 = advancedInterpreter.getIntegers().get(8);
        id2 = advancedInterpreter.getIntegers().get(9);
        firstName = item;
        lastName = LIKE_ALL_SIGN;
        email = LIKE_ALL_SIGN;
        ipAddress = LIKE_ALL_SIGN;
        getAdvancedData();
    }

    private void getAdvancedData() {
        getName();
        getLastName();
        getEmail();
        getIpAddress();
        getGender();
    }

    private void getName() {
        if (advancedInterpreter.getChecksList().get(0)) {
            firstName = item;
            lastName = LIKE_ALL_SIGN;
            email = LIKE_ALL_SIGN;
            ipAddress = LIKE_ALL_SIGN;
        }
    }

    private void getLastName() {
        if (advancedInterpreter.getChecksList().get(2)) {
            firstName = LIKE_ALL_SIGN;
            lastName = item;
            email = LIKE_ALL_SIGN;
            ipAddress = LIKE_ALL_SIGN;
        }
    }

    private void getEmail() {
        if (advancedInterpreter.getChecksList().get(4)) {
            firstName = LIKE_ALL_SIGN;
            lastName = LIKE_ALL_SIGN;
            email = item;
            ipAddress = LIKE_ALL_SIGN;
        }
    }

    private void getIpAddress() {
        if (advancedInterpreter.getChecksList().get(6)) {
            firstName = LIKE_ALL_SIGN;
            lastName = LIKE_ALL_SIGN;
            email = LIKE_ALL_SIGN;
            ipAddress = item;
        }
    }

    private void getGender() {
        gender1 = MALE;
        gender2 = FEMALE;
        if (advancedInterpreter.getChecksList().get(10) || advancedInterpreter.getChecksList().get(11)) {
         if (advancedInterpreter.getChecksList().get(10) && !advancedInterpreter.getChecksList().get(11)) {
             gender1 = MALE;
             gender2 = MALE;
         }
         if (!advancedInterpreter.getChecksList().get(10) && advancedInterpreter.getChecksList().get(11)) {
             gender1 = FEMALE;
             gender2 = FEMALE;
         }
     }
    }

}
