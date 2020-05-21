package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import java.awt.*;
import java.util.List;

public interface OptionsInterface {

        String getFieldName();
        Component getFirst();
        Component getSecond();
        List getContainers();
        List getContainerLabels();
        boolean isFirstSelected();
        boolean isSecondSelected();
        List<Boolean> getContainersChecks();

//        Component getCheck();
}
