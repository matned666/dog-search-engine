package eu.mrndesign.matned.searchEngine.data.jFrame;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;
import eu.mrndesign.matned.searchEngine.data.jFrame.welcomeScreen.WelcomeScreen;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ScreenManager implements
        WelcomeScreen.ScreenListener,
        SearchEngineScreen.ScreenListener{

    private SearchEngineScreen searchEngineScreen;
    private WelcomeScreen welcomeScreen;


    public void start() {
        welcomeScreen = new WelcomeScreen(this);
        welcomeScreen.show();
    }


    @Override
    public void onChooseDatabase_WelcomeScreen(String choice) {
        welcomeScreen.hide();
        searchEngineScreen = new SearchEngineScreen(this, choice);
        searchEngineScreen.show();
    }

    @Override
    public void onBackToDatabaseChoice_WelcomeScreen() {
        searchEngineScreen.hide();
        start();
    }
}
