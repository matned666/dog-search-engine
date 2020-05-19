package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

import lombok.Data;

import java.util.List;

@Data
public class SearchEngineView implements SearchEngineContract.View {

    private boolean areAdvancedOptionsOn;
    private SearchEngineScreen screen;


    public SearchEngineView() {
    }

    public SearchEngineView(SearchEngineScreen screen) {
        this.screen = screen;
        areAdvancedOptionsOn = false;
    }


    @Override
    public void onSearch(String list) {
        screen.getResultLabel().setText(list);
    }

    @Override
    public void onNoResult() {

    }

    @Override
    public void onBack() {

    }

    @Override
    public void onAdvancedOptionsClick(SearchEngineScreen searchEngineScreen) {
        if(!areAdvancedOptionsOn) {
            advancedOptionsTurnON();
        }else{
            advancedOptionsTurnOFF();
        }
    }

    private void advancedOptionsTurnON() {
        screen.getResultLabel().setBounds(20, 240, 660, 370);
        screen.getScroller().setBounds(20, 240, 660, 400);
        screen.getResultTexted().setBounds(30, 200, 100, 40);
        areAdvancedOptionsOn = true;
    }

    private void advancedOptionsTurnOFF() {
        screen.getResultLabel().setBounds(20, 140, 660, 470);
        screen.getScroller().setBounds(20, 140, 660, 500);
        screen.getResultTexted().setBounds(30, 100, 100, 40);
        areAdvancedOptionsOn = false;
    }


}
