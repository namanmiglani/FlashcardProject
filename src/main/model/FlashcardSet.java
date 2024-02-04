package model;

import java.util.ArrayList;

public class FlashcardSet {
    private ArrayList<Flashcard> setOfFlashcards;
    private String name;

    public FlashcardSet(String name) {
        setOfFlashcards = new ArrayList<>();
        this.name = name;
    }

    public void addFlashcardToSet(Flashcard flashcardToAdd) {
        setOfFlashcards.add(flashcardToAdd);
    }

    public ArrayList viewFlashcardSet() {
        return setOfFlashcards;
    }

    public void deleteFlashCard() {

    }


}
