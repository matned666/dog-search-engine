package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.optionsObject;

import eu.mrndesign.matned.searchEngine.data.mediator.SearchType;
import eu.mrndesign.matned.searchEngine.data.statics.Check;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;


@ToString
public class AdvancedOptionsNumber implements OptionsInterface{


    private static final int DEFAULT_NUM_FIRST = 0;
    private static final int DEFAULT_NUM_LAST = 999999;
    private SearchType searchType;
    private String fieldName;
    private JTextField first;
    private JTextField last;


    public AdvancedOptionsNumber(SearchType searchType, String fieldName) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        first = new JTextField(String.valueOf(DEFAULT_NUM_FIRST));
        last = new JTextField(String.valueOf(DEFAULT_NUM_LAST));
        keyListener(first, DEFAULT_NUM_FIRST);
        keyListener(last, DEFAULT_NUM_LAST);
    }


    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Component getFirst() {
        return first;
    }

    @Override
    public Component getSecond() { return last; }

    @Override
    public Integer getFirstNumber() {
        return Integer.parseInt(first.getText());
    }

    @Override
    public Integer getSecondNumber() {
        return Integer.parseInt(last.getText());
    }

    @Override
    public List getContainers() {
        return new LinkedList();
    }

    @Override
    public List getContainerLabels() {
        return new LinkedList();
    }

    @Override
    public boolean isFirstSelected() {
        return false;
    }

    @Override
    public boolean isSecondSelected() {
        return false;
    }

    @Override
    public List<Boolean> getContainersChecks() {
        return new LinkedList();
    }

    @Override
    public String getEnumChoice() {
        return null;
    }

    private void keyListener(JTextField textField, int defaultNum) {
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(!Check.isNumeric(textField.getText().trim())){
                    String text = StringUtils.chop(textField.getText());
                    textField.setText(text);
                }
                String text = textField.getText().trim();
                textField.setText(text);
            }
        });
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText() == null || textField.getText().trim().equals(""))
                    textField.setText(String.valueOf(defaultNum));
            }
        });
    }
}
