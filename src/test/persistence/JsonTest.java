package persistence;

import model.Tags;
import model.IdiomEntry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// from JsonSerializationDemo
public class JsonTest {
    protected void checkIdiomEntry(String idiom, String meaning, String read, String comments,
                                   Tags tags, IdiomEntry ie) {
        assertEquals(idiom, ie.getIdiom());
        assertEquals(meaning, ie.getMeaning());
        assertEquals(read, ie.getReading());
        assertEquals(comments, ie.getComment());
        for (String next : tags.getTagStrings()) {
            assertTrue(ie.getTags().getTagStrings().contains(next));
        }
    }
}
