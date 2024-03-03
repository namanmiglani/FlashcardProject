package persistance;

import model.*;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testInvalidFile() {
        try {
            FlashcardSet fs = new FlashcardSet("testSet");
            JsonWriter writer = new JsonWriter(".data/my\0:file.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void emptyFlashcardSet() {
        try {
            FlashcardSet fs = new FlashcardSet("testSet");
            JsonWriter writer = new JsonWriter("./data/testEmptyWriterFlashcard.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyWriterFlashcard.json");
            fs = reader.read();
            assertEquals("testSet", fs.getName());
            assertEquals(0, fs.getSetOfFlashcards().size());

        } catch (IOException e) {
            fail("unexpected");
        }
    }

    @Test
    void nonemptyFlashcardSet() {
        try {
            FlashcardSet fs = new FlashcardSet("testSet");
            fs.addFlashcardToSet(new Flashcard("q", "a"));
            JsonWriter writer = new JsonWriter("./data/testWriterFlashcard.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFlashcard.json");
            fs = reader.read();
            assertEquals("testSet", fs.getName());
            assertEquals(1, fs.getSetOfFlashcards().size());

        } catch (IOException e) {
            fail("unexpected");
        }
    }

}
