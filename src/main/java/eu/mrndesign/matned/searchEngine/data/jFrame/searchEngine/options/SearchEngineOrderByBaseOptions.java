package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.Select;
import eu.mrndesign.matned.searchEngine.data.mediator.AdvancedSearchOption;
import eu.mrndesign.matned.searchEngine.data.mediator.DataMediator;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class SearchEngineOrderByBaseOptions implements Options {

    private JPanel panel;
    private List<AdvancedSearchOption> fields;


    public SearchEngineOrderByBaseOptions(SearchEngineScreen screen) {
        this.panel = screen.getOrderByOptions();
        fields = new LinkedList<>(new DataMediator(screen.getChoice()).getListedOptions());

    }


    @Override
    public void make() {
        for (AdvancedSearchOption el : fields) {
            panel.add(new JLabel(el.getFieldName()));
            panel.add(new JCheckBox());
            panel.add(new JLabel("DESC:"));
            panel.add(new JCheckBox());
            panel.add(new JLabel(""));
            panel.add(new JLabel(""));
        }

    }

    @Override
    public List<OptionsInterface> getOptions() {
        return null;
    }
}
