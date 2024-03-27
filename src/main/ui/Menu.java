package ui;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;



// Represents the main menu shown to user
public class Menu extends JFrame {
    private JButton newSet;
    private JButton loadSet;
    private JLabel title;
    private FlashcardViewer viewer;
    private String setTitle;

    // MODIFIES: this
    // EFFECTS: creates main menu with new set button, load button, and an image
    public Menu() {
        addToPane(getContentPane());

        pack();
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: adds buttons and image to main menu
    public void addToPane(Container pane) {
        pane.setLayout(null);
        title = new JLabel("Flashcard App");

        newSet = new JButton("New Flashcard Set");
        newSetFuntion(newSet);
        //newSet.setLayout(new BorderLayout());
        newSet.setBounds(375, 450, 140, 50);
        //newSet.setAlignmentY(450);

        loadSet = new JButton("Load Set");
        //loadSet.setLayout(new BorderLayout());
        loadFunction(loadSet);
        //loadSet.setAlignmentX(405);
        loadSet.setBounds(505, 450, 140, 50);

        ImageIcon imageIcon = new ImageIcon("./data/book.png");
        JLabel image = new JLabel();
        image.setIcon(imageIcon);

        image.setVisible(true);
        image.setBounds(385, 75, 300,300);


        pane.add(newSet);
        pane.add(loadSet);
        pane.add(image);

    }

    // MODIFIES: this
    // EFFECTS: creates new flashcard set for the user to interact with
    public void newSetFuntion(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle = JOptionPane.showInputDialog(null, "Enter name", null, JOptionPane.OK_CANCEL_OPTION);
                setVisible(false);
                viewer = new FlashcardViewer(setTitle);
                viewer.setVisible(true);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads previous flashcard set from JSON data
    public void loadFunction(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                viewer = new FlashcardViewer(setTitle);
                viewer.load();
                viewer.setVisible(true);
            }
        });
    }
}
