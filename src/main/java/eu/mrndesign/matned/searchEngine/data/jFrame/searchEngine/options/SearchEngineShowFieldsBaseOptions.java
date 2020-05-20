package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import javax.swing.*;

public class SearchEngineShowFieldsBaseOptions extends BaseOptionsLabel implements Options {

    private int width;
    private int height;
    private String choice;

    public SearchEngineShowFieldsBaseOptions(String choice) {
        this.choice = choice;
    }


    @Override
    public JLabel label() {
        return null;
    }
}
