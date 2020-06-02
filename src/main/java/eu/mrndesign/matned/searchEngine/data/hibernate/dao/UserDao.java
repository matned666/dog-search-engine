package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Product;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.User;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.enums.DogRace;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.OptionsInterpreter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserDao implements DaoInterface<User> {

    private static final int MAX_RESULTS_ON_SCREEN = 100;
    private int firstResult;
    private int lastResult;

    private String item;
    private OptionsInterpreter advancedInterpreter;
    private OptionsInterpreter orderInterpreter;

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

    public UserDao(String item, OptionsInterpreter advancedInterpreter, OptionsInterpreter orderInterpreter, OptionsInterpreter selectInterpreter, int pageNo) {
        this.item = item;
        this.advancedInterpreter = advancedInterpreter;
        this.orderInterpreter = orderInterpreter;
        firstResult = MAX_RESULTS_ON_SCREEN * pageNo;
        lastResult = firstResult + MAX_RESULTS_ON_SCREEN;
        selectList = new LinkedList(selectInterpreter.getFieldNameList());
        initialize();
    }

    @Override
    public List<User> find() {
        String orderBy = orderInterpreter.orderBy() != null ? orderInterpreter.orderBy() : "userId";
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> rootTable = criteriaQuery.from(User.class);
            criteriaQuery.select(rootTable)
                    .where(
                            cb.and(
                                    cb.like(rootTable.get("firstName"), firstName),
                                    cb.like(rootTable.get("lastName"), lastName),
                                    cb.like(rootTable.get("email"), email),
                                    cb.like(rootTable.get("ipAddress"), ipAddress)
                            ),
                            cb.between(rootTable.get("userId"), id1, id2),
                            cb.or(
                                    cb.equal(rootTable.get("gender"), gender1),
                                    cb.equal(rootTable.get("gender"), gender2)

                            )
                            );
            criteriaQuery.orderBy(orderInterpreter.isDesc() ? cb.desc(rootTable.get(orderBy)) : cb.asc(rootTable.get(orderBy)));
            result.addAll(session.createQuery(criteriaQuery)
                    .setFirstResult(firstResult)
                    .setMaxResults(lastResult)
                    .list());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        changeSelect(result); //TODO temporary until multi select works
        return result;
    }

    private void changeSelect(List<User> result) {
        for (User el : result) {
            if (!selectList.contains("userId")) el.setUserId(null);
            if (!selectList.contains("firstName")) el.setFirstName(null);
            if (!selectList.contains("lastName")) el.setLastName(null);
            if (!selectList.contains("gender")) el.setGender(null);
            if (!selectList.contains("email")) el.setEmail(null);
            if (!selectList.contains("ipAddress")) el.setIpAddress(null);
        }
    }

    @Override
    public List<String> listOfFields() {
        return Arrays.asList(
                "NUMBER::userId::",
                "VARCHAR::firstName::",
                "VARCHAR::lastName::",
                "CHECKBOX::gender::Male::Female",
                "VARCHAR::email::",
                "VARCHAR::ipAddress::");
    }

    private void initialize() {
        id1 = advancedInterpreter.getIntegers().get(8);
        id2 = advancedInterpreter.getIntegers().get(9);
        firstName = "%";
        lastName = "%";
        email = "%";
        ipAddress = "%";
        gender1 = "Male";
        gender2 = "Female";
        getAdvancedData();
    }

    private void getAdvancedData() {
           if (advancedInterpreter.getChecksList().get(10) || advancedInterpreter.getChecksList().get(11)) {
            if (advancedInterpreter.getChecksList().get(10) && !advancedInterpreter.getChecksList().get(11)) {
                gender1 = "Male";
                gender2 = "Male";
            }
            if (!advancedInterpreter.getChecksList().get(10) && advancedInterpreter.getChecksList().get(11)) {
                gender1 = "Female";
                gender2 = "Female";
            }
        }
    }

}
