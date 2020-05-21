package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.enums.DogRace;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.OptionsInterpreter;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;


public class DogDao implements DaoInterface<Dog>{

    public static final int MAX_RESULTS_ON_SCREEN = 100;

    private int firstResult;
    private int lastResult;
    private int dogId1;
    private String dogName1;
    private String dogGender1;
    private String dogGender2;
    private int dogAge1;
    private DogRace dogRace1;
    private DogRace dogRace2;
    private boolean isPureRace1;
    private boolean isPureRace2;
    private int dogWeight1;
    private String ownerName1;
    private String ownerLastName1;
    private int dogId2;
    private String dogName2;
    private int dogAge2;
    private int dogWeight2;
    private String ownerName2;
    private String ownerLastName2;

    private List<String> list;
    private List<Path> selections;

    public DogDao() {
    }

    public DogDao(String item, List<String> list) {
        this.list = list;
        initializeCriteria();
        dogName1 = item;

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
            System.out.println(list);
            for (String el : list) {
                selections.add(rootTable.get(el));
            }
            criteriaQuery.multiselect(list.stream()
                    .map(f -> rootTable.get(f))
                    .collect(Collectors.toList()));
            criteriaQuery.select(rootTable)
                    .where(
                            cb.like(rootTable.get("dogName"), dogName1)
//                            cb.and(
//                                    cb.between(rootTable.get("dogId"), dogId1, dogId2),
//                                    cb.between(rootTable.get("dogName"), dogName1, dogName2)
//                                    cb.between(rootTable.get("dogGender"), dogGender2, dogGender1),
//                                    cb.between(rootTable.get("dogAge"), dogAge1, dogAge2),
//                                    cb.between(rootTable.get("dogRace"), dogRace1, dogRace2),
//                                    cb.between(rootTable.get("isDogPureRace"), isPureRace1? 1 : 0,isPureRace2? 1 : 0),
//                                    cb.between(rootTable.get("dogWeight"), dogWeight1, dogWeight2),
//                                    cb.between(rootTable.get("ownerName"), ownerName1, ownerName2),
//                                    cb.between(rootTable.get("ownerLastName"), ownerLastName1, ownerLastName2)
//                            )
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
        return Arrays.asList("NUMBER::Id::","VARCHAR::Name::","CHECKBOX::Gender::M::F", "NUMBER::Age::", "VARCHAR::Race::", "NUMBER::Weight::", "VARCHAR::Owner name::", "VARCHAR::Owner surname::");
    }

    private void initializeCriteria() {
        firstResult = 0;
        lastResult = firstResult + MAX_RESULTS_ON_SCREEN;
        dogId1 = 0;
        dogId2 = 999999;
        dogName1 = "a";
        dogName2 = "zzzzzzzzz";
        dogGender1 = "Male";
        dogGender2 = "Female";
        dogAge1 = 0;
        dogAge2 = 99;
        dogRace1 = DogRace.AA;
        dogRace2 = DogRace.ZZ;
        isPureRace2 = true;
        isPureRace1 = false;
        dogWeight1 = 0;
        dogWeight2 = 99;
        ownerName1 = "a";
        ownerName2 = "zzzzzzzzz";
        ownerLastName1 = "a";
        ownerLastName2 = "zzzzzzzzz";
    }


}
