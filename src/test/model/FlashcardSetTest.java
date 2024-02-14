package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardSetTest {

    FlashcardSet testFlashcardSet;
    Flashcard testFlashcardOne;
    Flashcard testFlashcardTwo;

    @BeforeEach
    void runBefore(){
        testFlashcardSet = new FlashcardSet("Test set");
        testFlashcardOne = new Flashcard("test question", "test answer");
        testFlashcardTwo = new Flashcard("test question two", "test answer two");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testFlashcardSet.getSetOfFlashcards().size());
        assertEquals("Test set", testFlashcardSet.getName());
    }

    @Test
    void testAddFlashcardToSet() {
        assertEquals(0, testFlashcardSet.getSetOfFlashcards().size());
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        assertEquals(1, testFlashcardSet.getSetOfFlashcards().size());
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        assertEquals(2, testFlashcardSet.getSetOfFlashcards().size());
        assertEquals(testFlashcardOne, testFlashcardSet.getSetOfFlashcards().get(0));
        assertEquals(testFlashcardTwo, testFlashcardSet.getSetOfFlashcards().get(1));
    }

    @Test
    void testViewFlashcards() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        assertEquals("Flashcard 1:\n" + "test question\n" + "Flashcard 2:\n" + "test question two\n",
                testFlashcardSet.viewFlashcardSet());
    }

    @Test
    void testGetFlashcardOverview() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        assertEquals(testFlashcardOne.flashCardOverview(), testFlashcardSet.getFlashCardOverview(1));
        assertEquals(testFlashcardTwo.flashCardOverview(), testFlashcardSet.getFlashCardOverview(2));
    }

    @Test
    void testGetFlashcard() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        assertEquals(testFlashcardOne, testFlashcardSet.getFlashCard(1));
        assertEquals(testFlashcardTwo, testFlashcardSet.getFlashCard(2));
    }

}
