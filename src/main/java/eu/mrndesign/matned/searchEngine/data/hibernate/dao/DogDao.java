package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.enums.DogRace;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.OptionsInterpreter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

public class DogDao implements DaoInterface<Dog>{

    public static final int MAX_RESULTS_ON_SCREEN = 100;

    private String item;
    private OptionsInterpreter advancedInterpreter;
    private OptionsInterpreter orderInterpreter;
    private OptionsInterpreter selectInterpreter;

    private int firstResult;
    private int lastResult;
    private int dogId1;
    private int dogId2;
    private String dogName;
    private String dogGender1;
    private String dogGender2;
    private int dogAge1;
    private int dogAge2;
    private DogRace dogRace1;
    private DogRace dogRace2;
    private boolean isPureRace1;
    private boolean isPureRace2;
    private String ownerName;
    private String ownerLastName;
    private int dogWeight1;
    private int dogWeight2;

    private List<String> selectList;

    private List<String[]> listArr;
    private List<Path> selections;

    public DogDao() {
    }

    public DogDao(String item, OptionsInterpreter advancedInterpreter, OptionsInterpreter orderInterpreter, OptionsInterpreter selectInterpreter) {
        this.item = item;
        this.advancedInterpreter = advancedInterpreter;
        this.orderInterpreter = orderInterpreter;
        this.selectInterpreter = selectInterpreter;

        selectList = new LinkedList(selectInterpreter.getFieldNameList());
        selections = new LinkedList<>();
        listArr = new LinkedList<>();
        initializeCriteria();
    }

    public void saveOrUpdate(Dog dog){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(dog);
            transaction.commit();
        }
        catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Dog> find() {
        List result = new LinkedList<>();
        selections = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Dog> criteriaQuery = cb.createQuery(Dog.class);
            Root<Dog> rootTable = criteriaQuery.from(Dog.class);
            System.out.println(selectList);
            for (String el : selectList) {
                selections.add(rootTable.get(el));
            }
            criteriaQuery.multiselect(selectList.stream() //TODO - it doesn't work yet
                    .map(f -> rootTable.get(f))
                    .collect(Collectors.toList()));
            criteriaQuery.select(rootTable)
                    .where(
                            cb.and(
                                    cb.and(
                                            cb.like(rootTable.get("dogName"), dogName),
                                            cb.like(rootTable.get("ownerName"), ownerName),
                                            cb.like(rootTable.get("ownerLastName"), ownerLastName)
                                            ),
                                    cb.between(rootTable.get("dogId"), dogId1, dogId2),
                                    cb.or(
                                            cb.equal(rootTable.get("dogGender"), dogGender1),
                                            cb.equal(rootTable.get("dogGender"), dogGender2)

                                            ),
                                    cb.between(rootTable.get("dogRace"), dogRace1,dogRace2),
                                    cb.between(rootTable.get("dogAge"), dogAge1, dogAge2),
                                    cb.or(
                                            cb.equal(rootTable.get("isDogPureRace"), isPureRace1? 1 : 0),
                                            cb.equal(rootTable.get("isDogPureRace"), isPureRace2? 1 : 0)

                                            ),
                                    cb.between(rootTable.get("dogWeight"), dogWeight1, dogWeight2)
                            )
                    );
            result.addAll(session.createQuery(criteriaQuery)
//                    .setFirstResult(firstResult)
//                    .setMaxResults(lastResult)
                    .list());
        }
        catch (HibernateException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<String> listOfFields() {
        return Arrays.asList("NUMBER::dogId::",
                "VARCHAR::dogName::",
                "CHECKBOX::dogGender::M::F",
                "NUMBER::dogAge::",
                "ENUM::dogRace::SHEPPARD::TERRIER::GOLDEN_RETRIEVER::BASSET::GREYHOUND::CHIHUAHUA::MOPS::HUSKY::DOG::SPANIEL",
                "NUMBER::dogWeight::",
                "BOOLEAN::isDogPureRace::",
                "VARCHAR::ownerName::",
                "VARCHAR::ownerLastName::");
    }

    private void initializeCriteria() {
//        firstResult = 0; TODO
//        lastResult = firstResult + MAX_RESULTS_ON_SCREEN;
        dogName = "%";
        dogGender1 = "Male";
        dogGender2 = "Female";
        isPureRace1 = true;
        isPureRace2 = false;
        dogId1 = advancedInterpreter.getIntegers().get(8);
        dogId2 = advancedInterpreter.getIntegers().get(9);
        dogAge1 = advancedInterpreter.getIntegers().get(10);
        dogAge2 = advancedInterpreter.getIntegers().get(11);
        dogWeight1 = advancedInterpreter.getIntegers().get(12);
        dogWeight2 = advancedInterpreter.getIntegers().get(13);
        ownerName = "%";
        ownerLastName = "%";
        dogRace1 = DogRace.AA;
        dogRace2 = DogRace.ZZ;
        getAdvancedData();
    }

    private void getAdvancedData() {
        if (advancedInterpreter.getChecksList().get(0)) dogName = item;
        if (advancedInterpreter.getChecksList().get(2)) ownerName = item;
        if (advancedInterpreter.getChecksList().get(4)) ownerLastName = item;
        if (!advancedInterpreter.getOptionsList().get(6).equals("ALL")){
            dogRace1 = DogRace.valueOf(advancedInterpreter.getOptionsList().get(6));
            dogRace2 = DogRace.valueOf(advancedInterpreter.getOptionsList().get(6));
        }
        if(advancedInterpreter.getChecksList().get(16) || advancedInterpreter.getChecksList().get(17)) {
            if (advancedInterpreter.getChecksList().get(16) && !advancedInterpreter.getChecksList().get(17)) {
                isPureRace1 = true;
                isPureRace2 = true;
            }
           if (!advancedInterpreter.getChecksList().get(16) && advancedInterpreter.getChecksList().get(17)) {
                isPureRace1 = false;
                isPureRace2 = false;
            }
        }
        System.out.println("P1: "+advancedInterpreter.getChecksList().get(16)+"P2: "+advancedInterpreter.getChecksList().get(17));

        if (advancedInterpreter.getChecksList().get(14) || advancedInterpreter.getChecksList().get(15)) {
            if (advancedInterpreter.getChecksList().get(14) && !advancedInterpreter.getChecksList().get(15)) {
                dogGender1 = "Male";
                dogGender2 = "Male";
            }
            if (!advancedInterpreter.getChecksList().get(14) && advancedInterpreter.getChecksList().get(15)) {
                dogGender1 = "Female";
                dogGender2 = "Female";
            }
        }
    }


}
