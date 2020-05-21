package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options;

import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.OptionsInterface;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject.Select;

import java.awt.*;
import java.util.List;

public interface Options {

    void make();
    List<OptionsInterface> getOptions();

}
