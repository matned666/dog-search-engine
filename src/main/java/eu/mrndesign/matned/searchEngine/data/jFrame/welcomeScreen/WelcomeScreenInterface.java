package eu.mrndesign.matned.searchEngine.data.jFrame.welcomeScreen;

public interface WelcomeScreenInterface {

    void onChooseDatabase();

    interface ScreenListener {
        void onChooseDatabase_WelcomeScreen(String choice);
    }
}
