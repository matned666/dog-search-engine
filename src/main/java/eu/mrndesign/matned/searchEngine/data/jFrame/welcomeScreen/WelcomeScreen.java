package eu.mrndesign.matned.searchEngine.data.jFrame.welcomeScreen;

import eu.mrndesign.matned.searchEngine.data.jFrame.BaseSwingScreen;
import eu.mrndesign.matned.searchEngine.data.mediator.DataMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WelcomeScreen extends BaseSwingScreen implements WelcomeScreenInterface {


    private final WelcomeScreen.ScreenListener listener;
    private JButton acceptButton;
    private JLabel imageLabel;
    private JComboBox<String> comboBox;
    private DataMediator dm;


    public WelcomeScreen(ScreenListener listener) {
        dm = new DataMediator();
        this.listener = listener;
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(720, 800);
        frame.setResizable(false);
        frame.add(acceptButton());
        frame.add(imageLabel());
        frame.add(comboBox());
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");

            }
        });


    }

    @Override
    public void onChooseDatabase() {

    }

    private JButton acceptButton() {
        acceptButton = new JButton();
        acceptButton.setBounds(160, 500, 400, 50);
        acceptButton.setFont(new Font("Arial", Font.PLAIN, 15));
        acceptButton.setText("Accept");
        acceptButton.addActionListener(actionEvent -> {
            onChooseDatabase();
        });
        return acceptButton;
    }

    private JLabel imageLabel() {
        imageLabel = new JLabel(new ImageIcon("src\\main\\resources\\guugle.png"));
        imageLabel.setBounds(100, 30, 500, 40);
        return imageLabel;
    }

    private JComboBox<String> comboBox(){
        comboBox = new JComboBox<>(dm.getListOfBasesFromDatabase());
        comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        comboBox.setBounds(160, 100, 400, 50);
        return comboBox;
    }
}
