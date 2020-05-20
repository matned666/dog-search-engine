package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.interpreter.DataInterpreter;

import java.awt.*;
import java.util.List;

public class SearchEngineAdvancedOptions extends OptionsGrid implements Options {

    private static final int COLUMNS = 5;


    private int width;
    private int height;
    private String choice;

    List fields;

    public SearchEngineAdvancedOptions(String choice) {
        this.choice = choice;
        DataInterpreter interpreter = new DataInterpreter();
        fields = interpreter.getListOptions(choice);
        height = (fields.size()/2)+2;
        width = COLUMNS;
    }

    @Override
    public GridLayout grid(GridLayout grid) {
        grid = new GridLayout();

        return grid;
    }
}
