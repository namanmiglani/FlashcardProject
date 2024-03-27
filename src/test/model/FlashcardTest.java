package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    Flashcard testFlashcard;
    Flashcard testFlashcardTwo;
    Flashcard testFlashcardDuplicate;

    @BeforeEach
    void runBefore(){
        testFlashcard = new Flashcard("question?" , "answer");
        testFlashcardTwo = new Flashcard("question?" , "answer", 1 , 1);
        testFlashcardDuplicate = new Flashcard("question?" , "answer", 5 , 5);
    }

    @Test
    void testConstructor() {
        assertEquals("question?", testFlashcard.getQuestion());
        assertEquals("answer", testFlashcard.getAnswer());
        assertEquals(0, testFlashcard.getCorrectAttempts());
        assertEquals(0, testFlashcard.getIncorrectAttempts());
    }

    @Test
    void testConstructorTwo() {
        assertEquals("question?", testFlashcardTwo.getQuestion());
        assertEquals("answer", testFlashcardTwo.getAnswer());
        assertEquals(1, testFlashcardTwo.getCorrectAttempts());
        assertEquals(1, testFlashcardTwo.getIncorrectAttempts());
    }

    @Test
    void testAnsweredCorrectAndIncorrect() {
        assertEquals(0, testFlashcard.getCorrectAttempts());
        assertEquals(0, testFlashcard.getIncorrectAttempts());
        testFlashcard.answeredCorrect();
        assertEquals(1, testFlashcard.getCorrectAttempts());
        testFlashcard.answeredCorrect();
        assertEquals(2, testFlashcard.getCorrectAttempts());
        testFlashcard.answeredIncorrect();
        assertEquals(1, testFlashcard.getIncorrectAttempts());
        testFlashcard.answeredIncorrect();
        assertEquals(2, testFlashcard.getIncorrectAttempts());
    }

    @Test
    void testFlashcardOverview() {
        assertTrue(testFlashcard.flashCardOverview().contains("\n"));
        assertTrue(testFlashcard.flashCardOverview().contains("The question is: " + "question?" + "\n"));
        assertTrue(testFlashcard.flashCardOverview().contains("The answer is: " + "answer" + "\n"));
        assertTrue(testFlashcard.flashCardOverview().contains("You have answered this correctly: " + 0 + " times\n"));
        assertTrue(testFlashcard.flashCardOverview().contains("You have answered this incorrectly: " + 0 + " times"));
    }

    @Test
    void testToJson() {
        JSONObject testJson = testFlashcard.toJson();
        JSONObject testJsonTwo = testFlashcardTwo.toJson();
        assertEquals("question?", testJson.get("question"));
        assertEquals("answer", testJson.get("answer"));
        assertEquals(1, testJsonTwo.get("correctAttempts"));
        assertEquals(1, testJsonTwo.get("incorrectAttempts"));
    }

    @Test
    void testEqualsHashcodeToString() {
        assertTrue(testFlashcardTwo.equals(testFlashcardDuplicate));
        assertTrue(testFlashcardTwo.equals(testFlashcardTwo));
        assertTrue(new Flashcard("q", "a").equals(new Flashcard("q", "a")));
        assertFalse(new Flashcard("q", "a").equals(new Flashcard("b", "a")));
        assertFalse(new Flashcard("q", "a").equals(new FlashcardSet("q")));
        assertTrue(testFlashcardTwo.hashCode() == testFlashcardDuplicate.hashCode());
        assertEquals("question?", testFlashcard.toString());
    }
}
