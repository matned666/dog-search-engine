package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

import java.util.List;

public class SearchEngineContract {

    public interface View {

        void onSearch(String list);
        void onNoResult();
        void onBack();
        void onAdvancedOptionsClick(SearchEngineScreen searchEngineScreen);

    }

    public interface Presenter {

        void search();
        void changeTermsOfSearch(String value);

    }

}
