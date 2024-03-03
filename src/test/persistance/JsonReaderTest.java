package persistance;

import model.*;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testNonExistantFile() {
        JsonReader reader = new JsonReader("./data/nonExistant.json");
        try {
            FlashcardSet fs = reader.read();
            fail("expected error");
        } catch (IOException e) {
            //
        }
    }

    @Test
    void testReaderEmptyFlashcardSet() {
        try {
            FlashcardSet fs = new FlashcardSet("testSet");
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyFlashcardSet.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader =  new JsonReader("./data/testReaderEmptyFlashcardSet.json");
            fs = reader.read();
            assertEquals("testSet", fs.getName());
            assertEquals(0, fs.getSetOfFlashcards().size());
        } catch (IOException e) {
            fail("expected error");
        }
    }

    @Test
    void nonreaderFlashcardSet() {
        try {
            FlashcardSet fs = new FlashcardSet("testSet");
            fs.addFlashcardToSet(new Flashcard("q", "a"));
            JsonWriter writer = new JsonWriter("./data/testReaderFlashcard.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderFlashcard.json");
            fs = reader.read();
            assertEquals("testSet", fs.getName());
            assertEquals(1, fs.getSetOfFlashcards().size());

        } catch (IOException e) {
            fail("unexpected");
        }
    }


}
