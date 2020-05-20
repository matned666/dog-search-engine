package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.interpreter.AdvancedSearchOptions;
import eu.mrndesign.matned.searchEngine.data.interpreter.DataInterpreter;
import eu.mrndesign.matned.searchEngine.data.interpreter.SearchType;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SearchEngineAdvancedBaseOptions extends BaseOptionsLabel implements Options {

    private static final int COLUMNS = 5;


    private int width;
    private int height;
    private JLabel label;
    private String choice;

    List<AdvancedSearchOptions> fields;

    public SearchEngineAdvancedBaseOptions(String choice, JLabel label) {
        this.choice = choice;
        this.label = label;


    }

    @Override
    public JLabel label() {
        int counter = 0;
        DataInterpreter interpreter = new DataInterpreter();
        fields = new LinkedList<>();
        fields.addAll(interpreter.getListOptions(choice));
        height = (fields.size()/2)+2;
        width = COLUMNS;
        label.setLayout(new GridLayout(width, height));
        for (AdvancedSearchOptions el : fields) {
            if (el.getSearchType() == SearchType.VARCHAR) {
                label.add(new JTextField(el.getFieldName()));
                label.add(new Checkbox());
            }
        }
        return label;
    }
}
