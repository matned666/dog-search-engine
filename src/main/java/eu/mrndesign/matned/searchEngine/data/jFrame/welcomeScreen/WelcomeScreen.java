package eu.mrndesign.matned.searchEngine.data.jFrame.welcomeScreen;

import eu.mrndesign.matned.searchEngine.data.jFrame.BaseSwingScreen;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreenInterface;

public class WelcomeScreen extends BaseSwingScreen implements SearchEngineScreenInterface {


    private final WelcomeScreen.ScreenListener listener;

    public WelcomeScreen(ScreenListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSearchButtonPress() {

    }
}
