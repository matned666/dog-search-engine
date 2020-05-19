package eu.mrndesign.matned.searchEngine.data.jFrame;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreenInterface;
import eu.mrndesign.matned.searchEngine.data.jFrame.welcomeScreen.WelcomeScreen;
import eu.mrndesign.matned.searchEngine.data.jFrame.welcomeScreen.WelcomeScreenInterface;
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
    public void onChooseDatabase_WelcomeScreen() {

    }

    @Override
    public void onBackToDatabaseChoice_WelcomeScreen() {

    }
}
