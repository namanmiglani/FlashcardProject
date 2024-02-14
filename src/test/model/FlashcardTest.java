package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    Flashcard testFlashcard;

    @BeforeEach
    void runBefore(){
        testFlashcard = new Flashcard("question?" , "answer");
    }

    @Test
    void testConstructor() {
        assertEquals("question?", testFlashcard.getQuestion());
        assertEquals("answer", testFlashcard.getAnswer());
        assertEquals(0, testFlashcard.getCorrectAttempts());
        assertEquals(0, testFlashcard.getIncorrectAttempts());
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
        String overview = "";
        overview += "The question is: " + "question?" + "\n";
        overview += "The answer is: " + "answer" + "\n";
        overview += "You have answered this correctly: " + 0 + " times\n";
        overview += "You have answered this incorrectly: " + 0 + " times\n";

        assertEquals(overview, testFlashcard.flashCardOverview());
    }
}
