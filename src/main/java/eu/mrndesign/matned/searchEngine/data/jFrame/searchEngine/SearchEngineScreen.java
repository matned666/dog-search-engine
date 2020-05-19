package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

import eu.mrndesign.matned.searchEngine.data.jFrame.BaseSwingScreen;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Data
public class SearchEngineScreen extends BaseSwingScreen implements SearchEngineScreenInterface {

    private JLabel imageLabel;
    private JButton acceptButton;
    private JButton backButton;
    private JButton advancedOptionsButton;
    private JTextField inputTextField;
    private JLabel resultLabel;
    private String result = "Search result:\n\n";
    private JLabel resultTexted;
    private JScrollPane scroller;

    private String choice;

    private ScreenListener listener;
    private final SearchEngineContract.Presenter presenter;
    private final SearchEngineContract.View view;


    public SearchEngineScreen(ScreenListener listener, String choice) {
        this.listener = listener;
        view = new SearchEngineView(this);
        presenter =  new SearchEnginePresenter(this, view);
        this.choice = choice;
        initialize();
    }

    @Override
    public void onSearchButtonPress() {

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
        frame.add(backButton());
        frame.add(advancedOptionsButton());
        frame.add(inputRegexTextField());
        frame.add(resultLabel());
        frame.add(imageLabel());
        frame.add(resultTexted);
        frame.add(sign);
        resultTexted.setText("<html><p><tr><td valign=\"top\">" + result + "</td></tr></p></html>");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");

            }
        });
    }

    private JTextField inputRegexTextField() {
        inputTextField = new JTextField();
        inputTextField.setText("");
        inputTextField.setBounds(100, 70, 500, 20);
        inputTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 10) {
                    presenter.search();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        return inputTextField;
    }

    public JButton acceptButton() {
        acceptButton = new JButton();
        acceptButton.setBounds(320, 100, 100, 20);
        acceptButton.setFont(new Font("Arial", Font.PLAIN, 15));
        acceptButton.setText("Search");
        acceptButton.addActionListener(actionEvent -> {
            presenter.search();
        });
        return acceptButton;
    }


    public JButton backButton() {
        backButton = new JButton();
        backButton.setBounds(500, 100, 100, 20);
        backButton.setFont(new Font("Arial", Font.PLAIN, 15));
        backButton.setText("Back");
        backButton.addActionListener(actionEvent -> {
            listener.onBackToDatabaseChoice_WelcomeScreen();
        });
        return backButton;
    }

    public JButton advancedOptionsButton() {
        advancedOptionsButton = new JButton();
        advancedOptionsButton.setBounds(100, 100, 200, 20);
        advancedOptionsButton.setFont(new Font("Arial", Font.PLAIN, 15));
        advancedOptionsButton.setText("Advanced options VVV");
        advancedOptionsButton.addActionListener(actionEvent -> {
           view.onAdvancedOptionsClick(this);
        });
        return advancedOptionsButton;
    }

    private JScrollPane resultLabel() {
        resultLabel = new JLabel();
        resultLabel.setBounds(20, 140, 660, 470);
        resultLabel.setVerticalAlignment(1);
        resultLabel.setText("");
        scroller = new JScrollPane(resultLabel);
        scroller.setBounds(20, 140, 660, 500);
        return scroller;
    }

    private JLabel imageLabel() {
        imageLabel = new JLabel(new ImageIcon("src\\main\\resources\\guugle.png"));
        imageLabel.setBounds(100, 30, 500, 40);
        return imageLabel;
    }


}
