package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.interpreter.AdvancedSearchOption;
import eu.mrndesign.matned.searchEngine.data.interpreter.DataInterpreter;
import eu.mrndesign.matned.searchEngine.data.interpreter.SearchType;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;
import lombok.Data;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

@Data
public class SearchEngineAdvancedBaseOptions implements Options {

    private JPanel panel;
    private String choice;
    private SearchEngineScreen screen;

    private DataInterpreter interpreter;

    private List<AdvancedSearchOption> fields;

    public SearchEngineAdvancedBaseOptions(SearchEngineScreen screen) {
        this.screen = screen;
        this.choice = screen.getChoice();
        this.panel = screen.getAdvancedSearchOptions();
        interpreter = new DataInterpreter(screen.getChoice());
        fields = new LinkedList<>(interpreter.getListedOptions());
    }

    @Override
    public void make() {
        fields.sort((a, b) -> new AdvancedSearchOptionComparator().compare(a, b));
        for (AdvancedSearchOption el : fields) {
            switch (el.getSearchType()) {
                case VARCHAR: {
                    varcharCase(el);
                    break;
                }
                case NUMBER: {
                    numberCase(el);
                    break;
                }
                case CHECKBOX: {
                    checkboxCase(el);
                    break;
                }
                case BOOLEAN: {
                    booleanCase(el);
                    break;
                }
            }
        }
    }

    private void varcharCase(AdvancedSearchOption el) {
        screen.getAdvancedSearchOptions().add(new JLabel("Search in: "+el.getFieldName() + ": "));
        screen.getAdvancedSearchOptions().add(new JCheckBox());
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
    }

    private void numberCase(AdvancedSearchOption el) {
        screen.getAdvancedSearchOptions().add(new JLabel(el.getFieldName()+" between"));
        screen.getAdvancedSearchOptions().add(new JTextField());
        screen.getAdvancedSearchOptions().add(new JLabel("and"));
        screen.getAdvancedSearchOptions().add(new JTextField());
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
    }

    private void checkboxCase(AdvancedSearchOption el) {
        screen.getAdvancedSearchOptions().add(new JLabel(el.getFieldName()+" "));

        for (String element: el.getOptionsList()) {
            screen.getAdvancedSearchOptions().add(new JLabel(element+": "));
            screen.getAdvancedSearchOptions().add(new JCheckBox());
        }
        screen.getAdvancedSearchOptions().add(new JLabel(""));


    }

    private void booleanCase(AdvancedSearchOption el) {
        screen.getAdvancedSearchOptions().add(new JLabel(el.getFieldName()+" "));
        screen.getAdvancedSearchOptions().add(new JLabel("true: "));
        screen.getAdvancedSearchOptions().add(new JCheckBox());
        screen.getAdvancedSearchOptions().add(new JLabel("false: "));
        screen.getAdvancedSearchOptions().add(new JCheckBox());
        screen.getAdvancedSearchOptions().add(new JLabel(""));

    }
}
