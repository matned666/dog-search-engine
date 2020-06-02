package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

import java.util.List;

public class SearchEngineContract {

    public interface View {

        void onSearch(String list, int size);
        void onAdvancedOptionsClick();
        void onOrderByOptionsClick();
        void onSelectOptionsClick();
    }

    public interface Presenter {

        void search();
        void changeSite();

    }

}
