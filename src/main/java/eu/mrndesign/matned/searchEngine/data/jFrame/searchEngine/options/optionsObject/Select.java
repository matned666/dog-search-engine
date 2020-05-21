package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import lombok.Data;
import lombok.ToString;

import javax.swing.*;

@Data
@ToString
public class Select{
    String fieldName;
    JCheckBox check;


    public Select(String fieldName) {
        this.fieldName = fieldName;
        check = new JCheckBox();
    }

}
