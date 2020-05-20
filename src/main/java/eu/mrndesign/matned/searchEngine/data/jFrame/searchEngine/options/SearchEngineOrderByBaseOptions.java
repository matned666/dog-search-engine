package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.interpreter.AdvancedSearchOption;
import eu.mrndesign.matned.searchEngine.data.interpreter.DataInterpreter;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.SearchEngineScreen;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class SearchEngineOrderByBaseOptions implements Options {

    private JPanel panel;
    private List<AdvancedSearchOption> fields;


    public SearchEngineOrderByBaseOptions(SearchEngineScreen screen) {
        this.panel = screen.getOrderByOptions();
        fields = new LinkedList<>(new DataInterpreter(screen.getChoice()).getListedOptions());

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
}
