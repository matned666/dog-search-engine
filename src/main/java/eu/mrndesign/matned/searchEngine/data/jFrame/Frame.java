package eu.mrndesign.matned.searchEngine.data.jFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame {

    private JFrame frame;
    private JLabel imageLabel;
    private JButton acceptButton;
    private JTextField inputRegexTextField;
    private JLabel resultLabel;
    private String result = "Search result:\n\n";
    private JLabel resultTexted;

    public Frame() {
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
        inputRegexTextField = new JTextField();
        inputRegexTextField.setText("");
        inputRegexTextField.setBounds(100, 70, 500, 20);
        inputRegexTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

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

            }
        });
        return acceptButton;
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


}
