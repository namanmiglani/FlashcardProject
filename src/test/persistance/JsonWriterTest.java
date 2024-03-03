package persistance;

import model.*;

import org.junit.jupiter.api.Test;
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

}
