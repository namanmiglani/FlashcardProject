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

    public void deleteFlashCard(int number) {
        number--;
        setOfFlashcards.remove(number);
    }

    public String getFlashCardOverview(int number) {
        number--;
        return setOfFlashcards.get(number).flashCardOverview();
    }

    public Flashcard getFlashCard(int number) {
        number--;
        return setOfFlashcards.get(number);
    }


}


