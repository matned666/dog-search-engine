package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.mediator.AdvancedSearchOption;
import eu.mrndesign.matned.searchEngine.data.mediator.DataMediator;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.*;
import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Data
public class SearchEngineAdvancedBaseOptions implements Options {

    private JPanel panel;
    private String choice;
    private SearchEngineScreen screen;

    private DataMediator interpreter;

    private List<AdvancedSearchOption> fields;
    private List<OptionsInterface> options;
    private  int counter;

    public SearchEngineAdvancedBaseOptions(SearchEngineScreen screen) {
        this.screen = screen;
        this.choice = screen.getChoice();
        this.panel = screen.getAdvancedSearchOptions();
        interpreter = new DataMediator(screen.getChoice());
        fields = new LinkedList<>(interpreter.getListedOptions());
        counter = 0;
    }

    @Override
    public void make() {
        options = new LinkedList<>();
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
                case ENUM: {
                    enumCase(el);
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
            counter++;
        }
        addListenersToAllFields();
    }

    private void enumCase(AdvancedSearchOption el) {
        options.add(new AdvancedOptionsEnum(SearchType.ENUM,el.getFieldName(),el.getOptionsList()));
        screen.getAdvancedSearchOptions().add(new JLabel("Search in: "+el.getFieldName() + ": "));
        screen.getAdvancedSearchOptions().add(options.get(counter).getFirst());
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
    }

    private void varcharCase(AdvancedSearchOption el) {
        options.add(new AdvancedOptionsVarchar(SearchType.VARCHAR,el.getFieldName()));
        screen.getAdvancedSearchOptions().add(new JLabel("Search in: "+el.getFieldName() + ": "));
        screen.getAdvancedSearchOptions().add(options.get(counter).getFirst());
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        JCheckBox box = (JCheckBox) options.get(counter).getFirst();

    }

    private void numberCase(AdvancedSearchOption el) {
        options.add(new AdvancedOptionsNumber(SearchType.NUMBER, el.getFieldName(), screen));
        screen.getAdvancedSearchOptions().add(new JLabel(el.getFieldName()+" between"));
        screen.getAdvancedSearchOptions().add(options.get(counter).getFirst());
        screen.getAdvancedSearchOptions().add(new JLabel("and"));
        screen.getAdvancedSearchOptions().add(options.get(counter).getSecond());
        screen.getAdvancedSearchOptions().add(new JLabel(""));
        screen.getAdvancedSearchOptions().add(new JLabel(""));
    }

    private void checkboxCase(AdvancedSearchOption el) {
        int count = 1;
        options.add(new AdvancedOptionsCheckbox(SearchType.CHECKBOX, el.getFieldName(), el.getOptionsList()));
        screen.getAdvancedSearchOptions().add(new JLabel(el.getFieldName()+" "));
        for (int i = 0; i < options.get(counter).getContainerLabels().size(); i++) {
            screen.getAdvancedSearchOptions().add(new JLabel(options.get(counter).getContainerLabels().get(i)+": "));
            screen.getAdvancedSearchOptions().add((Component) options.get(counter).getContainers().get(i));
            count+=2;
        }
        int counterVar = count * 6 + count;
        for (int i = 0; i < counterVar%counter+1; i++) {
            screen.getAdvancedSearchOptions().add(new JLabel(""));
        }
    }

    private void booleanCase(AdvancedSearchOption el) {
        options.add(new AdvancedOptionsBoolean(SearchType.BOOLEAN, el.getFieldName()));
        screen.getAdvancedSearchOptions().add(new JLabel(el.getFieldName()+" "));
        screen.getAdvancedSearchOptions().add(new JLabel("true: "));
        screen.getAdvancedSearchOptions().add(options.get(counter).getFirst());
        screen.getAdvancedSearchOptions().add(new JLabel("false: "));
        screen.getAdvancedSearchOptions().add(options.get(counter).getSecond());
        screen.getAdvancedSearchOptions().add(new JLabel(""));

    }

    private void addListenersToAllFields() {
        for (OptionsInterface el : options) {
            if (el.getSearchType() == SearchType.VARCHAR) {
                ((AdvancedOptionsVarchar) el).createListener(options);
            }
        }
    }
}
