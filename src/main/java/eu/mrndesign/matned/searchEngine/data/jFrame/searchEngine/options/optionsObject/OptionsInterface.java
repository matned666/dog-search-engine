package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import java.awt.*;
import java.util.List;

public interface OptionsInterface {

        String getFieldName();
        Component getFirst();
        Component getSecond();
        Integer getFirstNumber();
        Integer getSecondNumber();
        List getContainers();
        List getContainerLabels();
        boolean isFirstSelected();
        boolean isSecondSelected();
        List<Boolean> getContainersChecks();
        String getEnumChoice();
        boolean isChecked();

//        Component getCheck();
}
