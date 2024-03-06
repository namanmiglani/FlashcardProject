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
    void notExistantFileTest() {
        JsonReader reader = new JsonReader("./data/nonExistantFileTest.json");
        try {
            FlashcardSet fs = reader.read();
            fail("expected error");
        } catch (IOException e) {
            //
        }
    }

    @Test
    void testReaderEmptyFlashcardSetTest() {
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
    void notEmptyFlashcardSetTest() {
        try {
            FlashcardSet fs = new FlashcardSet("testSet");
            fs.addFlashcardToSet(new Flashcard("q", "a"));
            JsonWriter writer = new JsonWriter("./data/testReaderFlashcardSet.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderFlashcardSet.json");
            fs = reader.read();
            assertEquals("testSet", fs.getName());
            assertEquals(1, fs.getSetOfFlashcards().size());

        } catch (IOException e) {
            fail("unexpected");
        }
    }


}
