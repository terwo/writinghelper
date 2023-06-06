package persistence;

import model.Tags;
import model.IdiomEntry;
import model.IdiomCollection;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            IdiomCollection ic = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyIdiomCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyIdiomCollection.json");
        try {
            IdiomCollection ic = reader.read();
            assertEquals(0, ic.getCollection().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralIdiomCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralIdiomCollection.json");
        try {
            IdiomCollection ic = reader.read();
            List<IdiomEntry> collection = ic.getCollection();
            assertEquals(2, collection.size());
            Tags myTag = new Tags();
            myTag.addTag("happiness");
            checkIdiomEntry("笑来門福",
                    "good fortune and happiness will come to the home of those who smile",
                    "しょうもんらいふく", "From browsing the internet", myTag,
                    collection.get(0));
            Tags myTag2 = new Tags();
            myTag.addTag("bravery");
            checkIdiomEntry("忠勇無双", "of peerless loyalty and bravery", "ちゅうゆうむそう",
                    "", myTag2, collection.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    
    
}
