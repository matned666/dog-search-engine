package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import lombok.Data;
import lombok.ToString;

import javax.swing.*;

@Data
@ToString
public class OrderBy {

    String fieldName;
    JCheckBox order;
    JCheckBox desc;

    public OrderBy(String fieldName) {
        this.fieldName = fieldName;
        order = new JCheckBox();
        desc = new JCheckBox();
    }
}
