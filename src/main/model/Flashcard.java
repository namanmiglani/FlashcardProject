package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents a flashcard having a question, an answer, and a statistic for how many times it has been answered
// correctly, and incorrectly
public class Flashcard implements Writable {
    private String question;
    private String answer;
    private int correctAttempts;
    private int incorrectAttempts;


    // EFFECTS: Creates new flashcard with a question, an answer, and 0 attempts taken on answering it
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.correctAttempts = 0;
        this.incorrectAttempts = 0;
    }

    // EFFECTS: Creates new flashcard with a question, an answer, and attempts taken on answering it
    public Flashcard(String question, String answer, int correctAttempts, int incorrectAttempts) {
        this.question = question;
        this.answer = answer;
        this.correctAttempts = correctAttempts;
        this.incorrectAttempts = incorrectAttempts;
    }


    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public int getIncorrectAttempts() {
        return incorrectAttempts;
    }


    // MODIFIES: this
    // EFFECTS: adds one to the amount of times you have gotten a flashcard's answer correct
    public void answeredCorrect() {
        correctAttempts++;
    }

    // MODIFIES: this
    // EFFECTS: adds one to the amount of times you have gotten a flashcard's answer incorrect
    public void answeredIncorrect() {
        incorrectAttempts++;
    }


    // EFFECTS: creates a string overview of a flashcard with the question, answer, and the statistics on how many times
    // it has been answered correctly, and incorrectly
    public String flashCardOverview() {
        String overview = "";
        overview += "\n";
        overview += "The question is: " + this.question + "\n";
        overview += "The answer is: " + this.answer + "\n";
        overview += "You have answered this correctly: " + this.correctAttempts + " times\n";
        overview += "You have answered this incorrectly: " + this.incorrectAttempts + " times";
        return overview;
    }

    // EFFECTS: returns flashcard as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answer);
        json.put("correctAttempts", correctAttempts);
        json.put("incorrectAttempts", incorrectAttempts);
        return json;
    }

}
