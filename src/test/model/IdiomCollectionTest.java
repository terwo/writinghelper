package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the IdiomCollection class
public class IdiomCollectionTest {
    private IdiomCollection ic;

    @BeforeEach
    void setup() {
        ic = new IdiomCollection();
    }

    @Test
    void testConstructor() {
        assertTrue(ic.isEmpty());

    }


    @Test
    void addOneIdiomEntry() {
        assertTrue(ic.isEmpty());
        Tags tags = new Tags();
        tags.addTag("chance");
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only",
                "一期一会", "none", tags);
        List<IdiomEntry> myCollection = ic.addIdiomEntry(ieTest);
        assertEquals(1, myCollection.size());
        assertFalse(ic.isEmpty());
    }

    @Test
    void addOneMultipleEntries() {
        Tags tags = new Tags();
        tags.addTag("chance");
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only", "いちごいちえ",
                "none", tags);
        IdiomEntry ieTest2 = new IdiomEntry("1", "2",
                "3", "", tags);
        ic.addIdiomEntry(ieTest);
        List<IdiomEntry> myCollection = ic.addIdiomEntry(ieTest2);

        assertEquals(2, myCollection.size());
        assertEquals("一期一会", myCollection.get(0).getIdiom());
        assertEquals("1", myCollection.get(1).getIdiom());
        assertFalse(myCollection.isEmpty());

    }

    @Test
    void SearchByTagsValidTagNoneInTagsField() {
        Tags tags = new Tags();
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only", "いちごいちえ",
                "none yet", tags);
        ic.addIdiomEntry(ieTest);
        assertTrue(ic.searchByTags("chance").isEmpty());
    }

    @Test
    void SearchByTagsValidTagNoMatching() {
        Tags tags = new Tags();
        tags.addTag("chance");
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only", "いちごいちえ",
                "none", tags);
        ic.addIdiomEntry(ieTest);
        assertTrue(ic.searchByTags("sadness").isEmpty());


    }

    // referred to test class for Homework
    @Test
    void SearchByTagsValidTagOneMatching() {
        Tags tags = new Tags();
        tags.addTag("chance");
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only", "いちごいちえ",
                "none", tags);
        ic.addIdiomEntry(ieTest);
        List<String> myNames = ic.searchByTags("chance");
        assertEquals("一期一会", myNames.get(0));


    }

    @Test
    void SearchByTagsValidTagsMultipleMatching() {
        Tags tags = new Tags();
        tags.addTag("chance");
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only", "いちごいちえ",
                "none", tags);
        IdiomEntry ieTest2 = new IdiomEntry("1", "2","3",
                "", tags);
        ic.addIdiomEntry(ieTest);
        ic.addIdiomEntry(ieTest2);
        List<String> myNames = ic.searchByTags("chance");
        myNames.add("一期一会");
        myNames.add("1");
        assertEquals("一期一会", myNames.get(0));
        assertEquals("1", myNames.get(1));


    }

    @Test
    void MatchNameWithFullIdiomEntry() {
        Tags tags = new Tags();
        tags.addTag("chance");
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only", "いちごいちえ",
                "none", tags);
        IdiomEntry ieTest2 = new IdiomEntry("1", "2","3",
                "", tags);
        ic.addIdiomEntry(ieTest);
        ic.addIdiomEntry(ieTest2);
        assertEquals("1", ic.showDetails("1").getIdiom());
        assertEquals("2", ic.showDetails("1").getMeaning());
        assertEquals("3", ic.showDetails("1").getReading());
        assertEquals("", ic.showDetails("1").getComment());
        assertEquals("chance", ic.showDetails("1").getTags().getOneTag(0));
    }

    @Test
    void TryToShowDetailsButNothingToShow() {
        Tags tags = new Tags();
        tags.addTag("chance");
        IdiomEntry ieTest = new IdiomEntry("一期一会", "for this time only", "いちごいちえ",
                "none", tags);
        ic.addIdiomEntry(ieTest);
        assertNull(ic.showDetails("doggie"));
    }




}
