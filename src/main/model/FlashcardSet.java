package model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


// Represents a flashcard set having a name, and a list of flashcards
public class FlashcardSet implements Writable {
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

    // REQUIRES: flashcard set has at least one flashcard added to it
    // EFFECTS: returns all the flashcards in a given set, including the question, and a flashcard number based of the
    // order of when it was added to the set
    public String viewFlashcardSet() {
        ArrayList<String> stringList = new ArrayList<>();
        for (Flashcard flashcard: setOfFlashcards) {
            stringList.add(flashcard.getQuestion());
        }
        String questions = "\n";
        int questionNumber = 1;
        for (int i = 0; i < stringList.size(); i++) {
            questions += "Flashcard " + questionNumber + ":\n" + stringList.get(i) + "\n";
            questionNumber++;
        }
        return questions;
    }

    // REQUIRES: flashcard set has at least one flashcard added to it
    // MODIFIES: this
    // EFFECTS: deletes a flashcard from the flashcard set based of its flashcard number which does not follow
    // zero-based indexing and is based of when it was added to the set
    public void deleteFlashCard(int number) {
        number--;
        setOfFlashcards.remove(number);
    }

    // REQUIRES: flashcard set has at least one flashcard added to it
    // EFFECTS: gets a flashcard's overview from the given flashcard set based of its flashcard number which does not
    // follow zero-based indexing and is based of when it was added to the set
    public String getFlashCardOverview(int number) {
        number--;
        return setOfFlashcards.get(number).flashCardOverview();
    }

    // REQUIRES: flashcard set has at least one flashcard added to it
    // EFFECTS: gets a flashcard from the given flashcard set based of its flashcard number which does not follow
    // zero-based indexing and is based of when it was added to the set
    public Flashcard getFlashCard(int number) {
        number--;
        return setOfFlashcards.get(number);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("flashcards", flashcardsToJson());
        return json;
    }

    public JSONArray flashcardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard f : setOfFlashcards) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}


