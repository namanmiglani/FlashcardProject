package ui;

import model.Flashcard;
import model.FlashcardSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Flashcard application
public class FlashcardApp {
    private static final String JSON_STORE = "./data/flashcardSet.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private FlashcardSet mathSet;
    private FlashcardSet biologySet;

    // EFFECTS: runs the flashcard app
    public FlashcardApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFlashcardApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input to determine actions
    private void runFlashcardApp() {
        boolean runApp = true;
        String command;

        init();

        while (runApp) {
            displayMenu();
            Scanner s = new Scanner(System.in);
            command = s.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                runApp = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Bye");
    }

    // MODIFIES: this
    // EFFECTS: initializes two flashcard sets
    private void init() {
        mathSet = new FlashcardSet("Math 101 Set");
        biologySet = new FlashcardSet("Biology 112 Set");
    }

    // EFFECTS: prompts user with options
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tf -> Create a new flashcard, and add it to a set");
        System.out.println("\tv -> View all flashcards in a set");
        System.out.println("\td -> Delete a flashcard from a set");
        System.out.println("\to -> View flashcard overview");
        System.out.println("\ts -> Save work to a file");
        System.out.println("\tl -> Load work from a file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes input, and does task based of user input
    private void processCommand(String command) {
        if (command.equals("f")) {
            createNewFlashcard();
        } else if (command.equals("v")) {
            viewFlashcards();
        } else if (command.equals("d")) {
            deleteFlashcard();
        } else if (command.equals("o")) {
            produceOverview();
        } else if (command.equals("s")) {
            saveSet();
        } else if (command.equals("l")) {
            loadSet();
        }
    }


    // MODIFIES: this
    // EFFECTS: allows user to add a new flashcard to a set
    private void createNewFlashcard() {
        System.out.println("What set would you like to add the question to");
        FlashcardSet selectedSet = selectSet();
        Scanner s = new Scanner(System.in);
        System.out.println("What is this flashcard's question?");
        String question = s.nextLine();
        System.out.println("What is the question's answer?");
        String answer = s.nextLine();

        Flashcard myFlashcard = new Flashcard(question, answer);
        selectedSet.addFlashcardToSet(myFlashcard);
        System.out.println("Flashcard has been added to set");
    }

    // EFFECTS: allows user to view all flashcards in a set, or if the set is empty they are informed
    public void viewFlashcards() {
        FlashcardSet selectedSet = selectSet();
        if (selectedSet.getSetOfFlashcards().isEmpty()) {
            System.out.println("The set is empty\n");
        } else {
            System.out.println(selectedSet.viewFlashcardSet());
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to delete a new flashcard from a set, or if the set is empty they are informed
    public void deleteFlashcard() {
        FlashcardSet selectedSet = selectSet();
        if (selectedSet.getSetOfFlashcards().isEmpty()) {
            System.out.println("The set is empty\n");
        } else {
            System.out.println(selectedSet.viewFlashcardSet());
            System.out.println("Which flashcard number would you like to delete");
            Scanner s = new Scanner(System.in);
            int toDelete = s.nextInt();
            selectedSet.deleteFlashCard(toDelete);
            if (selectedSet.getSetOfFlashcards().isEmpty()) {
                System.out.println("The set is empty\n");
            } else {
                System.out.println("Done! Here is the updated flashcard set:\n");
                System.out.println(selectedSet.viewFlashcardSet());
            }
        }
    }


    // EFFECTS: allows user to view overview of a set with all the flashcards in it, or if the set is empty they are
    // informed
    public void produceOverview() {
        FlashcardSet selectedSet = selectSet();
        if (selectedSet.getSetOfFlashcards().isEmpty()) {
            System.out.println("The set is empty\n");
        } else {
            int toView = getValidCard(selectedSet);
            System.out.println(selectedSet.getFlashCardOverview(toView));
            System.out.println("\nWould you like to update this:");
            if (update()) {
                System.out.println("did you get the question correct?");
                if (update()) {
                    selectedSet.getFlashCard(toView).answeredCorrect();
                    System.out.println(selectedSet.getFlashCardOverview(toView));
                } else {
                    selectedSet.getFlashCard(toView).answeredIncorrect();
                    System.out.println(selectedSet.getFlashCardOverview(toView));
                }
            }
        }
    }

    // EFFECTS: prompts user to select a flashcard set that has been created
    public FlashcardSet selectSet() {
        String selection = "";

        while (!(selection.equals("m") || (selection.equals("b")))) {
            Scanner s = new Scanner(System.in);
            System.out.println("Select from:");
            System.out.println("\tm -> Math 100 Set");
            System.out.println("\tb -> Biol 112 Set");
            selection = s.nextLine();
        }

        if (selection.equals("m")) {
            return mathSet;
        } else {
            return biologySet;
        }

    }

    // EFFECTS: prompts user to answer a yes, or no question
    public Boolean update() {
        String selection = "";

        while (!(selection.equals("yes") || (selection.equals("no")))) {
            Scanner s = new Scanner(System.in);
            System.out.println("Select from:");
            System.out.println("\tyes");
            System.out.println("\tno");
            selection = s.nextLine();
        }

        return selection.equals("yes");

    }

    // EFFECTS: prompts user to select a flashcard in the set, based on the flashcard number
    public Integer getValidCard(FlashcardSet myFlashcardSet) {
        Scanner s = new Scanner(System.in);
        boolean inBounds = false;
        int toView = 0;
        while (!inBounds) {
            System.out.println(myFlashcardSet.viewFlashcardSet());
            System.out.println("Which flashcard number would you like to view");
            toView = s.nextInt();
            if (myFlashcardSet.getSetOfFlashcards().size() + 1 > toView && toView != 0) {
                inBounds = true;
            }
        }
        return toView;
    }

    private void saveSet() {
        FlashcardSet fs = selectSet();
        try {
            jsonWriter.open();
            jsonWriter.write(fs);
            jsonWriter.close();
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("error");
        }
    }

    private void loadSet() {
        FlashcardSet fs = selectSet();
        try {
            if (fs.equals(mathSet)) {
                mathSet = jsonReader.read();
                System.out.println("Loaded");
            } else {
                biologySet = jsonReader.read();
                System.out.println("Loaded");
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}


