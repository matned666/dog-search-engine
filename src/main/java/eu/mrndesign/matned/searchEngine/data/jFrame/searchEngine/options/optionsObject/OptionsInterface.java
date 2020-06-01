package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;

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
        SearchType getSearchType();
        List<Boolean> getContainersChecks();
        String getEnumChoice();
        boolean isChecked();
        void createListeners(List<OptionsInterface> options);
        boolean isDesc();
        void setChecked(boolean setter);
        void setDesc(boolean setter);


//        Component getCheck();
}
