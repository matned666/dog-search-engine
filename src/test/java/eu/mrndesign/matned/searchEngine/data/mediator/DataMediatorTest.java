package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection.DBCollectionDao;
import eu.mrndesign.matned.searchEngine.data.hibernate.dbCollection.EntityDBCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DataMediatorTest {

    @Mock private DBCollectionDao dbCollectionDao;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void gettingDBCollection(){
        Mockito.when(dbCollectionDao.find(null,null,null,null,null)).thenReturn(Collections.singletonList(
                EntityDBCollection.builder().dbId(1).dbName("Factory").build()));
//TODO
    }

}