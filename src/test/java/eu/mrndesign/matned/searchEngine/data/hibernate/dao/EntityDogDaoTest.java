package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.HibernateUtil;
import eu.mrndesign.matned.searchEngine.data.hibernate.dog.EntityDog;
import eu.mrndesign.matned.searchEngine.data.hibernate.dog.DogDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class EntityDogDaoTest<DBConnection> {


    @InjectMocks private HibernateUtil hibernateUtil;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
    @Mock private DogDao dao;
    @Mock private EntityDog entityDog;
    @Mock private Assertions Assert;
    private ResultSet testList;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testMockDBConnection() throws Exception {
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeQuery(Mockito.any())).thenReturn((ResultSet) testList);
        assertEquals(dao.find(null,null,null,null,0).size(), testList.getFetchSize());
        Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
        //TODO
    }

    @Test
    void testingMockito(){
    }

}