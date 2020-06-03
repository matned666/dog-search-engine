package eu.mrndesign.matned.searchEngine.data.mediator;

import eu.mrndesign.matned.searchEngine.data.hibernate.DaoInterface;
import eu.mrndesign.matned.searchEngine.data.hibernate.product.EntityProduct;
import eu.mrndesign.matned.searchEngine.data.hibernate.product.ProductDao;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.*;
import eu.mrndesign.matned.searchEngine.data.mediator.interpreter.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class DataMediatorTest {

    @Mock private DaoInterface<EntityProduct> prodDao;
    @Mock private static SearchEngineScreen screen;
    @Mock private DataMediator mediator;

    private AdvancedSearchInterpreterInterface aSII;
    private OrderByInterpreterInterface oBII;
    private static SelectInterpreterInterface sII;
    private static List<OptionsInterface> advanced;
    private static List<OptionsInterface> order;
    private static List<OptionsInterface> selects;
    private static List<EntityProduct> prods;

    @BeforeAll
    static void init(){
        prods = Arrays.asList(
               EntityProduct.builder()
                        .productId(1)
                        .productName("Morphine")
                        .productValue(150)
                        .productDetailsId(666)
                        .build()
        );
        order = Arrays.asList(
                new OrderBy("productName"),
                new OrderBy("productId"),
                new OrderBy("productValue"),
                new OrderBy("productDetailsId")
        );
        selects = Arrays.asList(
                new Select("productName"),
                new Select("productId"),
                new Select("productValue"),
                new Select("productDetailsId")
        );
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        screen = mock(SearchEngineScreen.class);
        prodDao = mock(ProductDao.class);
        advanced = Arrays.asList(
                new AdvancedOptionsVarchar(SearchType.VARCHAR, "productName"),
                new AdvancedOptionsNumber(SearchType.NUMBER, "productId", screen),
                new AdvancedOptionsNumber(SearchType.NUMBER, "productValue", screen),
                new AdvancedOptionsNumber(SearchType.NUMBER, "productDetailsId", screen)
        );
        oBII = new OrderByInterpreter(order);
        aSII = new AdvancedSearchInterpreter(advanced);
        sII = new SelectInterpreter(selects);
    }


    @Test
    void getResultList() {
        mediator = mock(DataMediator.class);
        mediator.setEntityChoice("Product");
        when(prodDao.find("", aSII, oBII, sII, 0)).thenReturn(prods);
        assertEquals(mediator.getResultList("", advanced, order, selects, 0), prods);
    }

    @Test
    void produceDao() {
        //TODO
    }

}