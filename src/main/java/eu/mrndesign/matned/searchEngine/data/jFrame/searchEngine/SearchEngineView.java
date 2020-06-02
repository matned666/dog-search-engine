package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

import lombok.Data;

@Data
public class SearchEngineView implements SearchEngineContract.View {

    private boolean areAdvancedOptionsOn;
    private boolean orderByOptions;
    private boolean showFieldsOptions;
    private SearchEngineScreen screen;


    public SearchEngineView(SearchEngineScreen screen) {
        this.screen = screen;
        areAdvancedOptionsOn = false;
        orderByOptions = false;
        showFieldsOptions = false;
    }


    @Override
    public void onSearch(String list, int size) {
        screen.getResultLabel().setText(list);
    }

    @Override
    public void onAdvancedOptionsClick() {
        if(!areAdvancedOptionsOn) {
            resultsGoDown();
            advancedOptionsTurnON();
        }else{
            resultsGoUp();
            advancedOptionsTurnOFF();
        }
    }

    @Override
    public void onOrderByOptionsClick() {
        if(!orderByOptions) {
            resultsGoDown();
            orderByOptionsTurnON();
        }else{
            resultsGoUp();
            orderByOptionsTurnOFF();
        }
    }

    @Override
    public void onSelectOptionsClick() {
        if(!showFieldsOptions) {
            resultsGoDown();
            showFieldsOptionsTurnON();
        }else{
            resultsGoUp();
            showFieldsOptionsTurnOFF();
        }
    }

    private void advancedOptionsTurnON() {
        orderByOptionsTurnOFF();
        showFieldsOptionsTurnOFF();
        screen.getAdvancedSearchOptions().setVisible(true);
        screen.getScrollerAdvancedSearchOptions().setVisible(true);
        areAdvancedOptionsOn = true;
    }

    private void orderByOptionsTurnON() {
        advancedOptionsTurnOFF();
        showFieldsOptionsTurnOFF();
        screen.getOrderByOptions().setVisible(true);
        screen.getScrollerOrderByOptions().setVisible(true);
        orderByOptions = true;
    }

    private void showFieldsOptionsTurnON() {
        advancedOptionsTurnOFF();
        orderByOptionsTurnOFF();
        screen.getSelectOptions().setVisible(true);
        screen.getScrollerSelectOptions().setVisible(true);
        showFieldsOptions = true;
    }

    private void advancedOptionsTurnOFF() {
        screen.getAdvancedSearchOptions().setVisible(false);
        screen.getScrollerAdvancedSearchOptions().setVisible(false);
        areAdvancedOptionsOn = false;
    }

     private void orderByOptionsTurnOFF() {
        screen.getOrderByOptions().setVisible(false);
        screen.getScrollerOrderByOptions().setVisible(false);
        orderByOptions = false;
    }

     private void showFieldsOptionsTurnOFF() {
        screen.getSelectOptions().setVisible(false);
        screen.getScrollerSelectOptions().setVisible(false);
        showFieldsOptions = false;
    }

    private void resultsGoDown() {
        screen.getResultLabel().setBounds(20, 280, 660, 330);
        screen.getScroller().setBounds(20, 280, 660, 360);
        screen.getResultTexted().setBounds(30, 240, 100, 40);

    }

    private void resultsGoUp() {
        screen.getResultLabel().setBounds(20, 140, 660, 470);
        screen.getScroller().setBounds(20, 140, 660, 500);
        screen.getResultTexted().setBounds(30, 100, 100, 40);
    }


}
