// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Reader that reads a flashcard set from JSON data stored in a file
public class JsonReader {

    private String source;

    // EFFECTS: constructs the reader with will read JSON data from a file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads JSON data and returns the flashcard set it reads
    // throws an IOException if error occurs in reading data from file
    public FlashcardSet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlashcardSet(jsonObject);
    }

    // EFFECTS: reads source file and returns it as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses flashcard set from JSON object and returns it
    private FlashcardSet parseFlashcardSet(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        FlashcardSet fs = new FlashcardSet(name);
        addFlashcards(fs, jsonObject);
        return fs;
    }

    // MODIFIES: fs
    // EFFECTS: parses flashcards from JSON object and adds it to flashcard set
    private void addFlashcards(FlashcardSet fs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashcards");
        for (Object json : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) json;
            addFlashcard(fs, nextFlashcard);
        }
    }

    // MODIFIES: fs
    // EFFECTS: parses flashcard from JSON object and adds it to flashcard set
    private void addFlashcard(FlashcardSet fs, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        Flashcard flashcard = new Flashcard(question, answer);
        fs.addFlashcardToSet(flashcard);
    }



    
}
