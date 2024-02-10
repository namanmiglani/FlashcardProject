package ui;

import model.Flashcard;
import model.FlashcardSet;

import java.util.Scanner;

public class FlashcardApp {

    private FlashcardSet mathSet;
    private FlashcardSet biologySet;

    public FlashcardApp() {
        runFlashcardApp();
    }

    private void runFlashcardApp() {
        boolean runApp = true;
        String command = null;

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

    private void init() {
        mathSet = new FlashcardSet("MATH 101 Set");
        biologySet = new FlashcardSet("BIOL 112 Set");
    }

    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("\tf -> Create a new flashcard, and add it to a set");
        System.out.println("\tv -> View all flashcards in a set");
        System.out.println("\td -> Delete a flashcard from a set");
        System.out.println("\to -> View flashcard overview");
        System.out.println("\tq -> Quit");
    }

    private void processCommand(String command) {
        if (command.equals("f")) {
            createNewFlashcard();
        } else if (command.equals("v")) {
            viewFlashcards();
        } else if (command.equals("d")) {
            deleteFlashcard();
        } else if (command.equals("o")) {
            produceOverview();
        }
    }


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

    public void viewFlashcards() {
        System.out.println("Select a set:");
        FlashcardSet selectedSet = selectSet();

        System.out.println(selectedSet.viewFlashcardSet());
    }

    public void deleteFlashcard() {
        System.out.println("Select a set:");
        FlashcardSet selectedSet = selectSet();
        System.out.println(selectedSet.viewFlashcardSet());
        System.out.println("Which flashcard number would you like to delete");
        Scanner s = new Scanner(System.in);
        int toDelete = s.nextInt();
        selectedSet.deleteFlashCard(toDelete);
        System.out.println("Done! Here is the updated flashcard set:");
        System.out.println(selectedSet.viewFlashcardSet());
    }

    public void produceOverview() {
        System.out.println("Select a set:");
        FlashcardSet selectedSet = selectSet();
        System.out.println(selectedSet.viewFlashcardSet());
        System.out.println("Which flashcard number would you like to view");
        Scanner s = new Scanner(System.in);
        int toView = s.nextInt();
        System.out.println(selectedSet.getFlashCardOverview(toView));
        System.out.println("Would you like to update this:");
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

    public FlashcardSet selectSet() {
        String selection = "";

        while (! (selection.equals("m") || (selection.equals("b")))) {
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

    public Boolean update() {
        String selection = "";

        while (! (selection.equals("yes") || (selection.equals("no")))) {
            Scanner s = new Scanner(System.in);
            System.out.println("Select from:");
            System.out.println("\tyes");
            System.out.println("\tno");
            selection = s.nextLine();
        }

        if (selection.equals("yes")) {
            return true;
        } else {
            return false;
        }

    }

}



