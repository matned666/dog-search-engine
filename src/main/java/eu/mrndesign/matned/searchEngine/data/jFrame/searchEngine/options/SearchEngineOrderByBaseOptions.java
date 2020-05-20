package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import javax.swing.*;

public class SearchEngineOrderByBaseOptions extends BaseOptionsLabel implements Options {

    private int width;
    private int height;
    private String choice;

    public SearchEngineOrderByBaseOptions(String choice) {
        this.choice = choice;
    }


    @Override
    public JLabel label() {
        return null;
    }
}
