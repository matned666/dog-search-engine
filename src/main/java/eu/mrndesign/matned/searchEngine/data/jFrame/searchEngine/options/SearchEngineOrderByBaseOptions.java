package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.*;
import eu.mrndesign.matned.searchEngine.data.mediator.AdvancedSearchOption;
import eu.mrndesign.matned.searchEngine.data.mediator.DataMediator;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class SearchEngineOrderByBaseOptions implements Options {

    private JPanel panel;
    private List<AdvancedSearchOption> fields;
    private List<OptionsInterface> options;

    int counter;


    public SearchEngineOrderByBaseOptions(SearchEngineScreen screen) {
        this.panel = screen.getOrderByOptions();
        fields = new LinkedList<>(new DataMediator(screen.getChoice()).getListedOptions());
        this.options = new LinkedList<>();
    }


    @Override
    public void make() {
        counter = 0;
        for (AdvancedSearchOption el : fields) {
            options.add(new OrderBy(el.getFieldName()));
            panel.add(new JLabel(el.getFieldName()));
            panel.add(options.get(counter).getFirst());
            panel.add(new JLabel("DESC:"));
            panel.add(options.get(counter).getSecond());
            panel.add(new JLabel(""));
            panel.add(new JLabel(""));
            counter++;
        }
        addListenersToAllFields();
    }

    private void addListenersToAllFields() {
        for (OptionsInterface el : options) {
            if (el instanceof AdvancedOptionsVarchar) {
                ((AdvancedOptionsVarchar) el).createListener(options);
            }
        }
    }

    @Override
    public List<OptionsInterface> getOptions() {
        return null;
    }
}
