package eu.mrndesign.matned.searchEngine.data.statics;

import org.junit.jupiter.api.Test;
import static eu.mrndesign.matned.searchEngine.data.statics.Check.*;


import static org.junit.jupiter.api.Assertions.*;

class CheckTest {

    @Test
    void onCorrectNumberCheck(){
        assertTrue(isNumeric("23"));
    }

    @Test
    void onWrongNumberFormatCheck_1(){
        assertFalse(isNumeric("23d"));
    }

    @Test
    void onWrongNumberFormatCheck_2(){
        assertFalse(isNumeric("23+"));
    }

    @Test
    void onNullDataCheck(){
        assertFalse(isNumeric(null));
    }



}