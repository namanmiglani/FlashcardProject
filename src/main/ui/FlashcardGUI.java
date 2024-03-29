package ui;

import model.Flashcard;
import model.FlashcardSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the split pane shown to users with the flashcards on one side, and the selected cards overview on the
// other side
public class FlashcardGUI {
    private FlashcardSet fs;
    private JSplitPane splitPane;
    private JList flashcards;
    private DefaultListModel flashcardsLeftPane;
    private JList flashcardsInfoL;
    private DefaultListModel flashcardInfo;
    private ListSelectionModel listSelectionModel;

    // MODIFIES: this
    // EFFECTS: creates new flashcard set which is displayed with an empty set of flashcards on the left side of a split
    // pane
    public FlashcardGUI() {
        fs = new FlashcardSet("Flashcard App");
        flashcardsLeftPane = new DefaultListModel();

        flashcards = new JList(flashcardsLeftPane);

        flashcards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = flashcards.getSelectionModel();
        listSelectionModel.addListSelectionListener(new MovesHandler());

        JScrollPane scrollPane = new JScrollPane(flashcards);

        flashcardInfo = new DefaultListModel<>();
        flashcardsInfoL = new JList<>(flashcardInfo);
        flashcardsInfoL.setSelectionMode((listSelectionModel.SINGLE_SELECTION));

        JScrollPane infoScroll = new JScrollPane(flashcardsInfoL);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, infoScroll);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);
    }

    // MODIFIES: this
    // EFFECTS: changes the flashcard overview everytime a new flashcard of the set is selected
    class MovesHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (flashcards.getSelectedValue() != null) {
                Flashcard selected = (Flashcard) flashcardsLeftPane.getElementAt(flashcards.getSelectedIndex());

                flashcardInfo.removeAllElements();
                flashcardInfo.addElement("The answer is: " + selected.getAnswer());
                flashcardInfo.addElement("You have answered correctly " + selected.getCorrectAttempts() + " times");
                flashcardInfo.addElement("You have answered incorrectly " + selected.getIncorrectAttempts() + " times");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to give the question and answer for a flashcard which is added to the set
    public void addFlashcard() {
        String question = JOptionPane.showInputDialog(null, "Please enter the question", null,
                JOptionPane.OK_CANCEL_OPTION);
        String answer = JOptionPane.showInputDialog(null, "Please enter the answer", null,
                JOptionPane.OK_CANCEL_OPTION);
        Flashcard f = new Flashcard(question, answer);
        if (f.getQuestion() != null || f.getAnswer() != null) {
            fs.addFlashcardToSet(f);
            flashcardsLeftPane.addElement(f);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds flashcard to the set
    public void addFlashcard(Flashcard f) {
        if (flashcardsLeftPane.size() == 0) {
            fs.addFlashcardToSet(f);
            flashcardsLeftPane.addElement(f);
            flashcards.setSelectedIndex(0);
        } else {
            fs.addFlashcardToSet(f);
            flashcardsLeftPane.addElement(f);
        }
    }

    // REQUIRES: there is at least one flashcard in the set
    // MODIFIES: this
    // EFFECTS: deletes selected flashcard from the set
    public void deleteFlashcard() {
        Flashcard selected = (Flashcard) flashcardsLeftPane.getElementAt(flashcards.getSelectedIndex());
        int index = flashcards.getSelectedIndex();
        int size = flashcardsLeftPane.size();

        int selection = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION) {
            fs.deleteFlashCard(selected);
            flashcardInfo.removeAllElements();
            if (size != 1) {
                if (index < size - 1) {
                    index++;
                    flashcards.setSelectedIndex(index);
                    flashcardsLeftPane.removeElement(selected);
                } else if (index + 1 == size) {
                    index--;
                    flashcards.setSelectedIndex(index);
                    flashcardsLeftPane.removeElement(selected);
                }
            } else {
                flashcardsLeftPane.clear();
            }
        }
    }

    // REQUIRES: there is at least one flashcard in the set
    // MODIFIES: this
    // EFFECTS: changes the flashcard overview updating the attempts
    public void update() {
        Flashcard selected = (Flashcard) flashcardsLeftPane.getElementAt(flashcards.getSelectedIndex());
        int index = flashcards.getSelectedIndex();
        int selection = JOptionPane.showConfirmDialog(null, "Did you get the question right",
                null, JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION) {
            selected.answeredCorrect();
            refresh();
        } else if (selection == JOptionPane.NO_OPTION) {
            selected.answeredIncorrect();
            refresh();
        }
    }


    // EFFECTS: displays the change of updating the attempts
    public void refresh() {
        int index = flashcards.getSelectedIndex();
        int size = flashcardsLeftPane.size();
        if (size != 1) {
            if (index < size - 1) {
                index++;
                flashcards.setSelectedIndex(index);
                index--;
                flashcards.setSelectedIndex(index);
            } else if (index + 1 == size) {
                index--;
                flashcards.setSelectedIndex(index);
                index++;
                flashcards.setSelectedIndex(index);
            }
        } else {
            Flashcard selected = (Flashcard) flashcardsLeftPane.getElementAt(flashcards.getSelectedIndex());
            flashcardInfo.removeAllElements();
            flashcardInfo.addElement("The answer is: " + selected.getAnswer());
            flashcardInfo.addElement("You have answered correctly " + selected.getCorrectAttempts() + " times");
            flashcardInfo.addElement("You have answered incorrectly " + selected.getIncorrectAttempts() + " times");
        }
    }

    public FlashcardSet getFs() {
        return fs;
    }

    public JList getFlashcards() {
        return flashcards;
    }

    public DefaultListModel getFlashcardsLeftPane() {
        return flashcardsLeftPane;
    }

    public JList getFlashcardsInfoL() {
        return flashcardsInfoL;
    }

    public DefaultListModel getFlashcardInfo() {
        return flashcardInfo;
    }

    public ListSelectionModel getListSelectionModel() {
        return listSelectionModel;
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }


}
