package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main interaction screen shown to users with the buttons at the bottom
public class FlashcardViewer extends JFrame {
    private JSplitPane splitPane;
    private JButton addFlashcard;
    private JButton deleteFlashcard;
    private JButton update;
    private FlashcardGUI setup;
    private JLabel label;
    private JsonReader reader;
    private JsonWriter writer;
    private static final String location = "./data/flashcard_set.json";


    // MODIFIES: this
    // EFFECTS: creates main user window with split pane of flashcards, it's overview, and the buttons on the bottom
    public FlashcardViewer(String title) {

        setTitle(title);
        setSize(1000,600);
        setup = new FlashcardGUI();

        reader = new JsonReader(location);
        writer = new JsonWriter(location);
        JSplitPane pane = setup.getSplitPane();
        pane.setBorder(null);

        createFlashcardButton();

        label = new JLabel();
        label.setLayout(new GridLayout(3, 3));
        label.add(addFlashcard);
        label.add(deleteFlashcard);
        label.add(update);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, label);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);

        getContentPane().add(splitPane);
        save();
    }


    // MODIFIES: this
    // EFFECTS: creates the buttons the user will use, and determines there function
    public void createFlashcardButton() {
        addFlashcard = new JButton("Add Flashcard");
        addFlashcard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setup.addFlashcard();
            }
        });
        deleteFlashcard = new JButton("Delete Flashcard");
        deleteFlashcard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setup.deleteFlashcard();
            }
        });
        update = new JButton("Update Flashcard");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setup.update();
            }
        });

    }


    // MODIFIES: this
    // EFFECTS: loads all data from a file which was previously saved to redisplay
    public void load() {
        try {
            FlashcardSet saved = reader.read();
            setTitle(saved.getName());
            for (Flashcard f : saved.getSetOfFlashcards()) {
                setup.addFlashcard(f);
            }
        } catch (IOException e) {
            //
        }
    }


    // MODIFIES: this
    // EFFECTS: saves all data to a JSON file
    public void save() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int selection = JOptionPane.showOptionDialog(null, "Would you like to save this set?",
                        null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                process(selection);
                }
            });

    }


    // EFFECTS: processes the user input when they are asked to save a set
    public void process(int selection) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (selection == JOptionPane.YES_OPTION) {
            FlashcardSet toSave = setup.getFs();
            try {
                writer.open();
                writer.write(toSave);
                writer.close();
                System.out.println("Saved");
            } catch (FileNotFoundException e) {
                System.out.println("error");
            }
        } else if (selection == JOptionPane.CANCEL_OPTION) {
            setVisible(true);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        }
    }
}
