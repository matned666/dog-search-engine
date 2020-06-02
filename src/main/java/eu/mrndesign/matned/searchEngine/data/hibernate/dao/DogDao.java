package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Dog;
import eu.mrndesign.matned.searchEngine.data.hibernate.entity.enums.DogRace;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.OptionsInterpreter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.*;

import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

public class DogDao implements DaoInterface<Dog> {

    public static final String ALL_RACES_SHOW = "ALL";
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";

    private int firstResult;
    private int lastResult;

    private String item;
    private OptionsInterpreter advancedInterpreter;
    private OptionsInterpreter orderInterpreter;

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


    public DogDao() {
    }

    public DogDao(String item, OptionsInterpreter advancedInterpreter, OptionsInterpreter orderInterpreter, OptionsInterpreter selectInterpreter, int pageNo) {
        this.item = item;
        this.advancedInterpreter = advancedInterpreter;
        this.orderInterpreter = orderInterpreter;
        firstResult = MAX_RESULTS_ON_SCREEN * pageNo;
        lastResult = firstResult + MAX_RESULTS_ON_SCREEN;
        selectList = new LinkedList(selectInterpreter.getFieldNameList());
        initializeCriteria();
    }

    @Override
    public List<Dog> find() {
        String orderBy = orderInterpreter.orderBy() != null ? orderInterpreter.orderBy() : "dogId";
        List<Dog> result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Dog> criteriaQuery = cb.createQuery(Dog.class);
            Root<Dog> rootTable = criteriaQuery.from(Dog.class);
            whereStatement(cb, criteriaQuery, rootTable);
            criteriaQuery.orderBy(orderInterpreter.isDesc() ? cb.desc(rootTable.get(orderBy)) : cb.asc(rootTable.get(orderBy)));
            result.addAll(session.createQuery(criteriaQuery)
                    .setFirstResult(firstResult)
                    .setMaxResults(lastResult)
                    .list());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        changeSelect(result);
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



    /*
    PRIVATE METHODS
    -------------------------------
    Data list indexes:
    dogName 0,1
    ownerName 2,3
    ownerLastName 4,5
    dogRace 6,7
    dogId 8,9
    dogAge 10,11
    dogWeight 12,13
    dogGender 14,15
    isPureRace 16,17

     */

    private void whereStatement(CriteriaBuilder cb, CriteriaQuery<Dog> criteriaQuery, Root<Dog> rootTable) {
        criteriaQuery.select(rootTable)
//                    .multiselect(selectList.stream().map(rootTable::get).collect(Collectors.toList()))
                //TODO how to create multiple constructors in entity for custom multiselect query ????
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
                                cb.between(rootTable.get("dogRace"), dogRace1, dogRace2),
                                cb.between(rootTable.get("dogAge"), dogAge1, dogAge2),
                                cb.or(
                                        cb.equal(rootTable.get("isDogPureRace"), isPureRace1 ? 1 : 0),
                                        cb.equal(rootTable.get("isDogPureRace"), isPureRace2 ? 1 : 0)

                                ),
                                cb.between(rootTable.get("dogWeight"), dogWeight1, dogWeight2)
                        )
                );
    }

    private void changeSelect(List<Dog> result) {
        for (Dog el : result) {
            if (!selectList.contains("dogId")) el.setDogId(null);
            if (!selectList.contains("dogName")) el.setDogName(null);
            if (!selectList.contains("dogGender")) el.setDogGender(null);
            if (!selectList.contains("dogRace")) el.setDogRace(null);
            if (!selectList.contains("dogAge")) el.setDogAge(null);
            if (!selectList.contains("dogWeight")) el.setDogWeight(null);
            if (!selectList.contains("isDogPureRace")) el.setIsDogPureRace(null);
            if (!selectList.contains("ownerName")) el.setOwnerName(null);
            if (!selectList.contains("ownerLastName")) el.setOwnerLastName(null);
        }
    }

    private void initializeCriteria() {
        dogName = item;
        dogGender1 = MALE;
        dogGender2 = FEMALE;
        isPureRace1 = true;
        isPureRace2 = false;
        dogId1 = advancedInterpreter.getIntegers().get(8);
        dogId2 = advancedInterpreter.getIntegers().get(9);
        dogAge1 = advancedInterpreter.getIntegers().get(10);
        dogAge2 = advancedInterpreter.getIntegers().get(11);
        dogWeight1 = advancedInterpreter.getIntegers().get(12);
        dogWeight2 = advancedInterpreter.getIntegers().get(13);
        ownerName = LIKE_ALL_SIGN;
        ownerLastName = LIKE_ALL_SIGN;
        dogRace1 = DogRace.AA;
        dogRace2 = DogRace.ZZ;
        getAdvancedData();
    }

    private void getAdvancedData() {
        getDogName();
        getOwnerNames();
        getDogRace();
        getPureRaceFlag();
        getGender();
    }

    private void getDogName() {
        if (advancedInterpreter.getChecksList().get(0)) {
            dogName = item;
            ownerName = LIKE_ALL_SIGN;
            ownerLastName = LIKE_ALL_SIGN;
        }
    }

    private void getOwnerNames() {
        if (advancedInterpreter.getChecksList().get(2)) {
            dogName = LIKE_ALL_SIGN;
            ownerName = item;
            ownerLastName = LIKE_ALL_SIGN;
        }
        if (advancedInterpreter.getChecksList().get(4)) {
            dogName = LIKE_ALL_SIGN;
            ownerName = LIKE_ALL_SIGN;
            ownerLastName = item;
        }
    }

    private void getDogRace() {
        if (!advancedInterpreter.getOptionsList().get(6).equals(ALL_RACES_SHOW)) {
            dogRace1 = DogRace.valueOf(advancedInterpreter.getOptionsList().get(6));
            dogRace2 = DogRace.valueOf(advancedInterpreter.getOptionsList().get(6));
        }
    }

    private void getPureRaceFlag() {
        if (advancedInterpreter.getChecksList().get(16) || advancedInterpreter.getChecksList().get(17)) {
            if (advancedInterpreter.getChecksList().get(16) && !advancedInterpreter.getChecksList().get(17)) {
                isPureRace1 = true;
                isPureRace2 = true;
            }
            if (!advancedInterpreter.getChecksList().get(16) && advancedInterpreter.getChecksList().get(17)) {
                isPureRace1 = false;
                isPureRace2 = false;
            }
        }
    }

    private void getGender() {
        if (advancedInterpreter.getChecksList().get(14) || advancedInterpreter.getChecksList().get(15)) {
            if (advancedInterpreter.getChecksList().get(14) && !advancedInterpreter.getChecksList().get(15)) {
                dogGender1 = MALE;
                dogGender2 = MALE;
            }
            if (!advancedInterpreter.getChecksList().get(14) && advancedInterpreter.getChecksList().get(15)) {
                dogGender1 = FEMALE;
                dogGender2 = FEMALE;
            }
        }
    }


}
