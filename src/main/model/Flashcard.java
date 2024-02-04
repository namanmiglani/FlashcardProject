package model;

public class Flashcard {
    private String question;
    private String answer;
    private int correctAttempts;
    private int incorrectAttempts;

    // EFFECTS: Creates new flashcard with a question, an answer, and 0 attempts
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.correctAttempts = 0;
        this. incorrectAttempts = 0;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public void setCorrectAttempts(int correctAttempts) {
        this.correctAttempts = correctAttempts;
    }

    public int getIncorrectAttempts() {
        return incorrectAttempts;
    }

    public void setIncorrectAttempts(int incorrectAttempts) {
        this.incorrectAttempts = incorrectAttempts;
    }

    public void answeredCorrect() {
        correctAttempts++;
    }

    public void answeredIncorrect() {
        incorrectAttempts++;
    }


}
