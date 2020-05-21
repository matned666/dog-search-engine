package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

import eu.mrndesign.matned.searchEngine.data.jFrame.BaseSwingScreen;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.Options;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.SearchEngineAdvancedBaseOptions;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.SearchEngineOrderByBaseOptions;
import eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine.options.SearchEngineShowFieldsBaseOptions;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Data
public class SearchEngineScreen extends BaseSwingScreen implements SearchEngineScreenInterface {

    private static final int WIDTH = 660;
    //TODO - make statics for dimentions

    private Options searchOptions;
    private JLabel imageLabel;
    private JLabel infoLabel;
    private JButton acceptButton;
    private JButton backButton;
    private JButton fieldsButton;
    private JButton orderByButton;
    private JButton advancedOptionsButton;
    private JTextField inputTextField;
    private JLabel resultLabel;
    private String result = "Search result:\n\n";
    private JLabel resultTexted;
    private JPanel advancedSearchOptions;
    private JPanel orderByOptions;
    private JPanel selectOptions;
    private JScrollPane scroller;
    private JScrollPane scrollerAdvancedSearchOptions;
    private JScrollPane scrollerOrderByOptions;
    private JScrollPane scrollerSelectOptions;

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
        sign.setBounds(500, 680, 180, 40);
        sign.setText("Made by Mateusz Niedbal");
        frame.add(sign);
        JLabel link = new JLabel("<html><a href=\"http://www.mrndesign.eu\">http://www.mrndesign.eu</a></html>");
        link.setBounds(500, 700, 180, 40);
        link.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkPreparation(link);
        frame.add(link);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(720, 800);
        frame.setResizable(false);
        addAllComponents();
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

    private void addAllComponents() {
        frame.add(acceptButton());
        frame.add(backButton());
        frame.add(advancedOptionsButton());
        frame.add(inputRegexTextField());
        frame.add(orderByButton());
        frame.add(resultLabel());
        frame.add(advancedSearch());
        frame.add(orderBy());
        frame.add(select());
        frame.add(imageLabel());
        frame.add(infoLabel());
        frame.add(fieldsButton());
        frame.add(resultTexted);
    }

    private JScrollPane advancedSearch() {
        advancedSearchOptions = new JPanel();
        advancedSearchOptions.setLayout(new GridLayout(0,6));
        JLabel lab = new JLabel("Advanced search: ");
        advancedSearchOptions.add(lab);
        for (int i = 0; i < 5; i++) {
            advancedSearchOptions.add(new JLabel(""));
        }
        advancedSearchOptions.setBounds(20, 140, WIDTH, 200);
        scrollerAdvancedSearchOptions = new JScrollPane(advancedSearchOptions);
        scrollerAdvancedSearchOptions.setBounds(20, 140, WIDTH, 100);
        Options searchOptions = new SearchEngineAdvancedBaseOptions(this);
        searchOptions.make();
        advancedSearchOptions.setVisible(false);
        scrollerAdvancedSearchOptions.setVisible(false);
        return scrollerAdvancedSearchOptions;
    }

    private JLabel imageLabel() {
        imageLabel = new JLabel(new ImageIcon("src\\main\\resources\\guugle.png"));
        imageLabel.setBounds(100, 30, 500, 40);
        return imageLabel;
    }

    private JLabel infoLabel() {
        infoLabel = new JLabel("Table '"+choice+"'");
        infoLabel.setBounds(20, 650, WIDTH, 40);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        return infoLabel;
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
        acceptButton.setBounds(100, 100, 100, 20);
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        acceptButton.setFont(new Font("Arial", Font.PLAIN, 15));
        acceptButton.setText("Search");
        acceptButton.addActionListener(actionEvent -> presenter.search());
        return acceptButton;
    }


    public JButton advancedOptionsButton() {
        advancedOptionsButton = new JButton();
        advancedOptionsButton.setBounds(200, 100, 100, 20);
        advancedOptionsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        advancedOptionsButton.setFont(new Font("Arial", Font.PLAIN, 10));
        advancedOptionsButton.setText("Advanced");
        advancedOptionsButton.addActionListener(actionEvent -> {
            view.onAdvancedOptionsClick();
            if(advancedSearchOptions.isVisible()) advancedSearchOptions.setVisible(true);
            else advancedSearchOptions.setVisible(true);

        });
        return advancedOptionsButton;
    }

    public JButton fieldsButton() {
        fieldsButton = new JButton();
        fieldsButton.setBounds(300, 100, 100, 20);
        fieldsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fieldsButton.setFont(new Font("Arial", Font.PLAIN, 10));
        fieldsButton.setText("Fields show");
        fieldsButton.addActionListener(actionEvent -> {
            view.onSelectOptionsClick();
            if(selectOptions.isVisible()) selectOptions.setVisible(true);
            else selectOptions.setVisible(true);
        });
        return fieldsButton;
    }

    public JButton orderByButton() {
        orderByButton = new JButton();
        orderByButton.setBounds(400, 100, 100, 20);
        orderByButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        orderByButton.setFont(new Font("Arial", Font.PLAIN, 10));
        orderByButton.setText("Order by");
        orderByButton.addActionListener(actionEvent -> {
            view.onOrderByOptionsClick();
            if(orderByOptions.isVisible()) orderByOptions.setVisible(true);
            else orderByOptions.setVisible(true);
        });
        return orderByButton;
    }

    public JButton backButton() {
        backButton = new JButton();
        backButton.setBounds(500, 100, 100, 20);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFont(new Font("Arial", Font.PLAIN, 10));
        backButton.setText("Back");
        backButton.addActionListener(actionEvent -> listener.onBackToDatabaseChoice_WelcomeScreen());
        return backButton;
    }

    private JScrollPane resultLabel() {
        resultLabel = new JLabel();
        resultLabel.setBounds(20, 140, WIDTH, 470);
        resultLabel.setVerticalAlignment(1);
        resultLabel.setText("");
        scroller = new JScrollPane(resultLabel);
        scroller.setBounds(20, 140, WIDTH, 500);
        return scroller;
    }


    private JScrollPane orderBy() {
        orderByOptions = new JPanel();
        orderByOptions.setLayout(new GridLayout(0,6));
        JLabel lab = new JLabel("Order by: ");
        orderByOptions.add(lab);
        for (int i = 0; i < 5; i++) {
            orderByOptions.add(new JLabel(""));
        }
        orderByOptions.setBounds(20, 140, WIDTH, 200);
        scrollerOrderByOptions = new JScrollPane(orderByOptions);
        scrollerOrderByOptions.setBounds(20, 140, WIDTH, 100);
        Options searchOptions = new SearchEngineOrderByBaseOptions(this);
        searchOptions.make();
        orderByOptions.setVisible(false);
        scrollerOrderByOptions.setVisible(false);
        return scrollerOrderByOptions;
    }


    private JScrollPane select() {
        selectOptions = new JPanel();
        selectOptions.setLayout(new GridLayout(0,6));
        JLabel lab = new JLabel("Fields to be shown: ");
        selectOptions.add(lab);
        for (int i = 0; i < 5; i++) {
            selectOptions.add(new JLabel(""));
        }
        selectOptions.setBounds(20, 140, WIDTH, 200);
        scrollerSelectOptions = new JScrollPane(selectOptions);
        scrollerSelectOptions.setBounds(20, 140, WIDTH, 100);
        searchOptions = new SearchEngineShowFieldsBaseOptions(this);
        searchOptions.make();
        selectOptions.setVisible(false);
        scrollerSelectOptions.setVisible(false);
        return scrollerSelectOptions;
    }

    private void linkPreparation(JLabel link) {
        link.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://mrndesign.eu"));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }







}
