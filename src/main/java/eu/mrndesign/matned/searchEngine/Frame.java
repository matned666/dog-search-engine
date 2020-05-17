package eu.mrndesign.matned.searchEngine;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import eu.mrndesign.matned.searchEngine.fileOperations.ActualSession;
import eu.mrndesign.matned.searchEngine.fileOperations.FileOps;
import eu.mrndesign.matned.searchEngine.fileOperations.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.List;

public class Frame {

    private JFrame frame;
    private JLabel imageLabel;
    private JButton acceptButton;
    private JTextField inputRegexTextField;
    private JLabel resultLabel;
    private String result = "Search result:\n\n";
    private String regex;
    private SearchEngine searchEngine;
    private List<String> resourceList;
    private JLabel resultTexted;
    private JButton historyButton;
    private ActualSession newSession;

    public Frame(List resourceList) {
        this.resourceList = resourceList;
        newSession = new ActualSession();
    }

    public void initialize() {
        frame = new JFrame();
        resultTexted = new JLabel();
        resultTexted.setBounds(30, 100, 100, 40);
        JLabel sign = new JLabel();
        sign.setBounds(500, 700, 150, 40);
        sign.setText("Made by Mateusz Niedbał");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(720, 800);
        frame.setResizable(false);
        frame.add(acceptButton());
        frame.add(inputRegexTextField());
        frame.add(resultLabel());
        frame.add(imageLabel());
        frame.add(resultTexted);
        frame.add(historyButton());
        frame.add(sign);
        resultTexted.setText("<html><p><tr><td valign=\"top\">" + result + "</td></tr></p></html>");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");
                try {
                    newSession.setSessionEndTime(new LocalTime());
                    newSession.setSessionEndDate(new LocalDate());
                    FileOps.writeStringToFile(newSession.toString() + FileOps.readStringFromFile());
                } catch (FileNotFoundException ex) {
                    System.out.println("No file found");
                }
            }
        });
    }

    private JTextField inputRegexTextField() {
        inputRegexTextField = new JTextField();
        inputRegexTextField.setText("");
        inputRegexTextField.setBounds(100, 70, 500, 20);
        inputRegexTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 10) {
                    resultLabel.setText("");
                    regex = inputRegexTextField.getText();
                    resultLabel.setText("<html>" + searchEngine() + "</html>");
                    newSession.getSessionSearchesList().add(new Search(regex, searchEngine.isSearchedItemInResource(regex), searchEngine.getTitlesList().size()));
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        return inputRegexTextField;
    }

    public JButton acceptButton() {
        acceptButton = new JButton();
        acceptButton.setBounds(300, 100, 100, 20);
        acceptButton.setFont(new Font("Arial", Font.PLAIN, 15));
        acceptButton.setText("Search");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resultLabel.setText("");
                regex = inputRegexTextField.getText();
                resultLabel.setText("<html>" + searchEngine() + "</html>");
                newSession.getSessionSearchesList().add(new Search(regex, searchEngine.isSearchedItemInResource(regex), searchEngine.getContentList().size()));
                System.out.println(searchEngine.getTitlesList().size());
            }
        });
        return acceptButton;
    }

    public JButton historyButton() {
        historyButton = new JButton();
        historyButton.setBounds(420, 100, 180, 20);
        historyButton.setFont(new Font("Arial", Font.PLAIN, 15));
        historyButton.setText("View session history");
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resultLabel.setText("");
                try {
                    resultLabel.setText("<html>" + newSession + FileOps.readStringFromFile() + "</html>");
                } catch (FileNotFoundException e) {
                    resultLabel.setText("No file found !!!");
                }
            }
        });
        return historyButton;
    }

    private JScrollPane resultLabel() {
        resultLabel = new JLabel();
        resultLabel.setBounds(20, 140, 660, 470);
        resultLabel.setVerticalAlignment(1);
        resultLabel.setText("");
        JScrollPane scroller = new JScrollPane(resultLabel);
        scroller.setBounds(20, 140, 660, 500);
        return scroller;
    }

    private JLabel imageLabel() {
        imageLabel = new JLabel(new ImageIcon("src\\main\\resources\\guugle.png"));
        imageLabel.setBounds(100, 30, 500, 40);
        return imageLabel;
    }

    public String searchEngine() {
        String temp = resultLabel.getText();
        searchEngine = new SearchEngine(this.resourceList);
        searchEngine.isSearchedItemInResource(regex);
        if (searchEngine.getTitlesList().size() <= 0) {
            temp = "No entries for '" + regex + "'";
        } else {
            for (int i = 0; i < searchEngine.getTitlesList().size(); i++) {
                temp += searchEngine.getTitlesList().get(i) + "\n    - " + searchEngine.getContentList().get(i);
            }
        }
        return temp;
    }

}
