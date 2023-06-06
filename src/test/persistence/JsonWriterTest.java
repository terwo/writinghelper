package persistence;

import model.Tags;
import model.IdiomEntry;
import model.IdiomCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// from JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            IdiomCollection ic = new IdiomCollection();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyIdiomCollection() {
        try {
            IdiomCollection ic = new IdiomCollection();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyIdiomCollection.json");
            writer.open();
            writer.write(ic);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyIdiomCollection.json");
            ic = reader.read();
            assertEquals(0, ic.getCollection().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralIdiomCollection() {
        try {
            IdiomCollection ic = new IdiomCollection();
            Tags tagsHappiness = new Tags();
            tagsHappiness.addTag("happiness");
            Tags tagsBravery = new Tags();
            tagsHappiness.addTag("bravery");
            ic.addIdiomEntry(new IdiomEntry("笑門来福",
                    "good fortune and happiness will come to the home of those who smile",
                    "しょうもんらいふく", "From browsing the internet", tagsHappiness));
            ic.addIdiomEntry(new IdiomEntry("忠勇無双", "of peerless loyalty and bravery",
                    "ちゅうゆうむそう", "", tagsBravery));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralIdiomCollection.json");
            writer.open();
            writer.write(ic);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralIdiomCollection.json");
            ic = reader.read();
            List<IdiomEntry> myIdioms = ic.getCollection();
            assertEquals(2, myIdioms.size());
            checkIdiomEntry("笑門来福",
                    "good fortune and happiness will come to the home of those who smile",
                    "しょうもんらいふく", "From browsing the internet", tagsHappiness, myIdioms.get(0));
            checkIdiomEntry("忠勇無双", "of peerless loyalty and bravery",
                    "ちゅうゆうむそう", "", tagsBravery, myIdioms.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
