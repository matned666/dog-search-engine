package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

public class SearchEngineContract {

    public interface View {

        void onSearch();
        void onNoResult();
        SearchEngineScreen getSearchEngineScreen();
        void onAdvancedOptionsClick();

    }

    public interface Presenter {

        void search(String value);
        void onTyping(String value);

    }

}
