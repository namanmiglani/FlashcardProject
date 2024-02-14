package model;

import java.util.ArrayList;

// Represents a flashcard set having a name, and a list of flashcards
public class FlashcardSet {
    private ArrayList<Flashcard> setOfFlashcards;
    private String name;

    // EFFECTS: creates new flashcard set with an empty set of flashcards and a name
    public FlashcardSet(String name) {
        setOfFlashcards = new ArrayList<>();
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds a flashcard to the flashcard set
    public void addFlashcardToSet(Flashcard flashcardToAdd) {
        setOfFlashcards.add(flashcardToAdd);
    }


    public ArrayList<Flashcard> getSetOfFlashcards() {
        return setOfFlashcards;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns all the flashcards in a given set
    public String viewFlashcardSet() {
        ArrayList<String> stringList = new ArrayList<>();
        for (Flashcard flashcard: setOfFlashcards) {
            stringList.add(flashcard.getQuestion());
        }
        String questions = "";
        int questionNumber = 1;
        for (int i = 0; i < stringList.size(); i++) {
            questions += "Flashcard " + questionNumber + ":\n" + stringList.get(i) + "\n";
            questionNumber++;
        }
        return questions;
    }

    // MODIFIES: this
    // EFFECTS: deletes a flashcard from the flashcard set
    public void deleteFlashCard(int number) {
        number--;
        setOfFlashcards.remove(number);
    }

    // EFFECTS: gets a flashcard's overview from the given set based on number given
    public String getFlashCardOverview(int number) {
        number--;
        return setOfFlashcards.get(number).flashCardOverview();
    }

    // EFFECTS: gets a flashcard's from the given set based on number given
    public Flashcard getFlashCard(int number) {
        number--;
        return setOfFlashcards.get(number);
    }



}


