package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        assertEquals("\n" + "Flashcard 1:\n" + "test question\n" + "Flashcard 2:\n" + "test question two\n",
                testFlashcardSet.viewFlashcardSet());
    }

    @Test
    void testDeleteFlashcard() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        assertEquals(2, testFlashcardSet.getSetOfFlashcards().size());
        assertEquals(testFlashcardOne, testFlashcardSet.getSetOfFlashcards().get(0));
        assertEquals(testFlashcardTwo, testFlashcardSet.getSetOfFlashcards().get(1));
        testFlashcardSet.deleteFlashCard(1);
        assertEquals(1, testFlashcardSet.getSetOfFlashcards().size());
        assertEquals(testFlashcardTwo, testFlashcardSet.getSetOfFlashcards().get(0));
    }

    @Test
    void testDeleteFlashcardTwo() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        assertEquals(2, testFlashcardSet.getSetOfFlashcards().size());
        assertEquals(testFlashcardOne, testFlashcardSet.getSetOfFlashcards().get(0));
        assertEquals(testFlashcardTwo, testFlashcardSet.getSetOfFlashcards().get(1));
        testFlashcardSet.deleteFlashCard(testFlashcardTwo);
        assertEquals(1, testFlashcardSet.getSetOfFlashcards().size());
        assertEquals(testFlashcardOne, testFlashcardSet.getSetOfFlashcards().get(0));
        testFlashcardSet.deleteFlashCard(testFlashcardTwo);
        assertEquals(1, testFlashcardSet.getSetOfFlashcards().size());
        assertEquals(testFlashcardOne, testFlashcardSet.getSetOfFlashcards().get(0));
    }

    @Test
    void testGetFlashcardOverview() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        assertTrue(testFlashcardSet.getFlashCardOverview(1).contains("\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(1).contains("The question is: " + "test question" + "\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(1).contains("The answer is: " + "test answer" + "\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(1).contains("You have answered this correctly: " + 0 + " times\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(1).contains("You have answered this incorrectly: " + 0 + " times"));

        assertTrue(testFlashcardSet.getFlashCardOverview(2).contains("\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(2).contains("The question is: " + "test question two" + "\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(2).contains("The answer is: " + "test answer two" + "\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(2).contains("You have answered this correctly: " + 0 + " times\n"));
        assertTrue(testFlashcardSet.getFlashCardOverview(2).contains("You have answered this incorrectly: " + 0 + " times"));
    }

    @Test
    void testGetFlashcard() {
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        assertEquals(testFlashcardOne, testFlashcardSet.getFlashCard(2));
        assertEquals(testFlashcardTwo, testFlashcardSet.getFlashCard(1));
    }

    @Test
    void testToJson() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        JSONObject testJson = testFlashcardSet.toJson();
        assertEquals("Test set", testJson.get("name"));
        JSONArray myArray = (JSONArray) testJson.get("flashcards");
        assertEquals("{\"correctAttempts\":0,\"incorrectAttempts\":0,\"question\":\"test question\",\"answer\":\"test answer\"}", myArray.get(0).toString());
    }

    @Test
    void testFlashcardsToJson() {
        testFlashcardSet.addFlashcardToSet(testFlashcardOne);
        testFlashcardSet.addFlashcardToSet(testFlashcardTwo);
        JSONArray myArray = testFlashcardSet.flashcardsToJson();
        assertEquals("{\"correctAttempts\":0,\"incorrectAttempts\":0,\"question\":\"test question\",\"answer\":\"test answer\"}", myArray.get(0).toString());
        assertEquals("{\"correctAttempts\":0,\"incorrectAttempts\":0,\"question\":\"test question two\",\"answer\":\"test answer two\"}", myArray.get(1).toString());
    }

}
