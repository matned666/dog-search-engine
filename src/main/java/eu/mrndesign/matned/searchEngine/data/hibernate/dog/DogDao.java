package eu.mrndesign.matned.searchEngine.data.hibernate.dog;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.*;

import static eu.mrndesign.matned.searchEngine.data.hibernate.dog.DogDaoStatics.*;
import static eu.mrndesign.matned.searchEngine.data.hibernate.dog.DogDaoStatics.DOG;
import static eu.mrndesign.matned.searchEngine.data.statics.Data.*;

public class DogDao implements DaoInterface<EntityDog> {

    private String item;
    private AdvancedSearchInterpreterInterface advancedInterpreter;

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

    @Override
    public List<EntityDog> find(String item,
                                AdvancedSearchInterpreterInterface advancedInterpreter,
                                OrderByInterpreterInterface orderInterpreter,
                                SelectInterpreterInterface selectInterpreter,
                                Integer pageNo) {
        this.item = item;
        int firstResult = MAX_RESULTS_ON_SCREEN * pageNo;
        this.advancedInterpreter = advancedInterpreter;
        selectList = new LinkedList(selectInterpreter.getFieldNameList());
        initializeCriteria();
        String orderBy = orderInterpreter.orderBy() != null ? orderInterpreter.orderBy() : DOG_ID;
        List<EntityDog> result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EntityDog> criteriaQuery = cb.createQuery(EntityDog.class);
            Root<EntityDog> rootTable = criteriaQuery.from(EntityDog.class);
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
                NUMBER_+ _i_ +DOG_ID+ _i_,
                VARCHAR_+ _i_ +DOG_NAME+ _i_,
                CHECKBOX_+ _i_ +DOG_GENDER+ _i_ +MALE+ _i_ +FEMALE,
                NUMBER_+ _i_ +DOG_AGE+ _i_,
                NUMBER_+ _i_ +DOG_WEIGHT+ _i_,
                ENUM_+ _i_ +DOG_RACE+ _i_ + SHEPPARD + _i_ + TERRIER + _i_ + GOLDEN_RETRIEVER + _i_ + BASSET + _i_ + GREYHOUND + _i_ + CHIHUAHUA + _i_ + MOPS + _i_ + HUSKY + _i_ + DOG + _i_ + SPANIEL,
                BOOLEAN_+ _i_ +IS_DOG_PURE_RACE,
                VARCHAR_+ _i_ +OWNER_NAME+ _i_,
                VARCHAR_+ _i_ +OWNER_LAST_NAME+ _i_);
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

    private void whereStatement(CriteriaBuilder cb, CriteriaQuery<EntityDog> criteriaQuery, Root<EntityDog> rootTable) {
        criteriaQuery.select(rootTable)
                .where(
                        cb.and(
                                cb.and(
                                        cb.like(rootTable.get(DOG_NAME), dogName),
                                        cb.like(rootTable.get(OWNER_NAME), ownerName),
                                        cb.like(rootTable.get(OWNER_LAST_NAME), ownerLastName)
                                ),
                                cb.between(rootTable.get(DOG_ID), dogId1, dogId2),
                                cb.or(
                                        cb.equal(rootTable.get(DOG_GENDER), dogGender1),
                                        cb.equal(rootTable.get(DOG_GENDER), dogGender2)
                                ),
                                cb.between(rootTable.get(DOG_RACE), dogRace1, dogRace2),
                                cb.between(rootTable.get(DOG_AGE), dogAge1, dogAge2),
                                cb.or(
                                        cb.equal(rootTable.get(IS_DOG_PURE_RACE), isPureRace1 ? 1 : 0),
                                        cb.equal(rootTable.get(IS_DOG_PURE_RACE), isPureRace2 ? 1 : 0)
                                ),
                                cb.between(rootTable.get(DOG_WEIGHT), dogWeight1, dogWeight2)
                        )
                );
    }

    private void changeSelect(List<EntityDog> result) {
        for (EntityDog el : result) {
            if (!selectList.contains(DOG_ID)) el.setDogId(null);
            if (!selectList.contains(DOG_NAME)) el.setDogName(null);
            if (!selectList.contains(DOG_GENDER)) el.setDogGender(null);
            if (!selectList.contains(DOG_RACE)) el.setDogRace(null);
            if (!selectList.contains(DOG_AGE)) el.setDogAge(null);
            if (!selectList.contains(DOG_WEIGHT)) el.setDogWeight(null);
            if (!selectList.contains(IS_DOG_PURE_RACE)) el.setIsDogPureRace(null);
            if (!selectList.contains(OWNER_NAME)) el.setOwnerName(null);
            if (!selectList.contains(OWNER_LAST_NAME)) el.setOwnerLastName(null);
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
